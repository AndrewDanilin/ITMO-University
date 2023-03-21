package com.andrewdanilin.homework7.services

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.os.Parcelable
import com.andrewdanilin.homework7.MainApplication
import com.andrewdanilin.homework7.MessagesAPI
import com.andrewdanilin.homework7.R
import com.andrewdanilin.homework7.channel.Channel
import com.andrewdanilin.homework7.chatmessage.ChatMessage
import com.andrewdanilin.homework7.utils.Utils
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class ChatService: Service() {

    companion object {
        private const val MESSAGES_TO_DOWNLOAD = 50
        private const val NUMBER_OF_MESSAGES = 10_000
    }

    enum class RequestMessagesType {
        NEW_MESSAGES, OLD_MESSAGES, FIRST_MESSAGES
    }

    lateinit var scope: CoroutineScope

    var savedChannelName: String? = null

    private val channelToIds = ConcurrentHashMap<Channel, TreeSet<Int>>()
    val channels: CopyOnWriteArrayList<Channel> = CopyOnWriteArrayList()
    val channelToMessages = ConcurrentHashMap<Channel, MutableList<ChatMessage?>>()

    var scrollViewState: Parcelable? = null

    override fun onCreate() {
        scope = CoroutineScope(Dispatchers.Main)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return ChatBinder()
    }

    inner class ChatBinder: Binder() {
        fun getChatService() = this@ChatService
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    suspend fun requestChannels(): ServiceRequestResult {
        return try {
            val namesOfChannels = MainApplication.instance.messagesAPI.getChannels()
            namesOfChannels.forEach {
                val channel = Channel(it)
                this.channels.add(channel)
                channelToIds[channel] = TreeSet<Int>()
                channelToMessages[channel] = mutableListOf()
            }
            ServiceRequestResult(Result.SUCCESS)
        } catch (e: HttpException) {
            System.err.println(e.localizedMessage)
            handleResponseCode(e.code())
        } catch (e: IOException) {
            System.err.println(e.localizedMessage)
            handleResponseCode(-1)
        }
    }

    suspend fun requestMessages(requestType: RequestMessagesType, channel: Channel): ServiceRequestResult {
        return try {
            when(requestType) {
                RequestMessagesType.FIRST_MESSAGES -> getFirstMessages(channel)
                RequestMessagesType.NEW_MESSAGES -> getNewMessages(channel)
                RequestMessagesType.OLD_MESSAGES -> getOldMessages(channel)
            }
        } catch (e: HttpException) {
            System.err.println(e.localizedMessage)
            handleResponseCode(e.code())
        }
    }

    private suspend fun getFirstMessages(channel: Channel): ServiceRequestResult {
        if (channelToMessages[channel]?.isEmpty() == true) {
            val messages = MainApplication.instance.messagesAPI.getMessages(
                channel.name,
                MESSAGES_TO_DOWNLOAD,
                NUMBER_OF_MESSAGES,
                true
            )
            return addMessages(messages.reversed(), true, channel)
        }
        return ServiceRequestResult(Result.SUCCESS, first = 0, second = channelToMessages[channel]?.size)
    }

    private suspend fun getNewMessages(channel: Channel): ServiceRequestResult {
        val messages = MainApplication.instance.messagesAPI.getMessages(
            channel.name,
            MESSAGES_TO_DOWNLOAD,
            channelToIds[channel]!!.last(),
            false
        )
        return addMessages(messages, false, channel)
    }

    private suspend fun getOldMessages(channel: Channel): ServiceRequestResult {
        val messages = MainApplication.instance.messagesAPI.getMessages(
            channel.name,
            MESSAGES_TO_DOWNLOAD,
            channelToIds[channel]!!.first() - MESSAGES_TO_DOWNLOAD - 1,
            false
        )
        return addMessages(messages, true, channel)
    }

    private fun addMessages(messages: List<ChatMessage>, toStart: Boolean, channel: Channel): ServiceRequestResult {
        val newMessages = mutableListOf<ChatMessage?>()
        for(message in messages) {
            if (channelToIds[channel]?.contains(message.id) != true) {
                if (message.data.text != null && message.data.text!!.text.trim().isEmpty()) {
                    continue
                } else {
                    channelToIds[channel]?.add(message.id)
                    newMessages.add(message)
                }
            }
        }
        removeNulls(channel)
        val index = if (toStart) 0 else channelToMessages[channel]!!.size
        val prevSize = channelToMessages[channel]!!.size
        channelToMessages[channel]?.addAll(index, newMessages)
        return ServiceRequestResult(
            Result.SUCCESS,
            first = index,
            second = channelToMessages[channel]!!.size - prevSize
        )
    }

    private fun handleResponseCode(responseCode: Int): ServiceRequestResult {
        return when (responseCode) {
            -1 -> {
                ServiceRequestResult(
                    Result.FAILURE,
                    text = getString(R.string.connection_error))
            }
            in 500..599 -> {
                ServiceRequestResult(
                    Result.FAILURE,
                    text = getString(R.string.something_went_wrong)
                )
            }
            else -> {
                ServiceRequestResult(
                    Result.FAILURE,
                    text = getString(R.string.failed_to_connect_to_server)
                )
            }
        }
    }

    suspend fun sendTextMessage(text: String, channel: Channel): ServiceRequestResult {
        try {
            val responseBody = MainApplication.instance.messagesAPI.sendTextMessage(
                RequestBody.create(
                    MediaType.parse("application/json"),
                    "{\"from\":\"${Utils.DEFAULT_SENDER}\", " +
                            "\"to\":\"${channel.name}\", " +
                            "\"data\":{\"Text\":{\"text\":\"$text\"}}}"
                )
            )

            val idOfMessage = responseBody.string().toInt()
            val message = MainApplication.instance.messagesAPI.getMessages(
                channel.name,
                1,
                idOfMessage - 1,
                false
            )
            return addMessages(message, false, channel)
        } catch (exception: HttpException) {
            System.err.println(exception.localizedMessage)
            return handleResponseCode(exception.code())
        }
    }

    suspend fun sendImageMessage(contentUri: Uri, channel: Channel): ServiceRequestResult {
        val bitmap = scope.async(Dispatchers.IO) {
            contentResolver.openInputStream(contentUri).use {
                BitmapFactory.decodeStream(it)
            }
        }

        val imageName = System.currentTimeMillis().toString() + ".png"

        val saveImageJob = withContext(Dispatchers.IO) {
            saveImageToTemporaryDir(imageName, bitmap.await())
        }

        val file = Utils.getPathToSaveImage(imageName, cacheDir)
        try {
            val responseBody = MainApplication.instance.messagesAPI.sendImageMessage(
                RequestBody.create(
                    MediaType.parse("application/json"),
                    "{\"from\":\"${Utils.DEFAULT_SENDER}\", \"to\":\"${channel.name}\"}"
                ),
                MultipartBody.Part.createFormData(
                    "pic",
                    file.name,
                    RequestBody.create(
                        MediaType.parse("image/png"),
                        file
                    )
                )
            )

            val idOfMessage = responseBody.string().toInt()
            val message = MainApplication.instance.messagesAPI.getMessages(
                channel.name,
                1,
                idOfMessage - 1,
                false
            )
            return addMessages(message, false, channel)
        } catch (exception: HttpException) {
            System.err.println(exception.localizedMessage)
            return when (exception.code()) {
                409 -> sendImageMessage(contentUri, channel)
                413 -> ServiceRequestResult(
                    Result.FAILURE,
                    text = getString(R.string.very_big_picture)
                )
                else -> ServiceRequestResult(
                    Result.FAILURE,
                    text = getString(R.string.failed_send_image)
                )
            }
        }
    }

    fun addNullData(toStart: Boolean, channel: Channel) {
        channelToMessages[channel]!!.add(if (toStart) 0 else channelToMessages[channel]!!.size, null)
    }

    private fun removeNulls(channel: Channel) {
        if (channelToMessages[channel]!!.size > 0) {
            removeNullData(false, channel)
        }
        if (channelToMessages[channel]!!.size > 0) {
            removeNullData(true, channel)
        }
    }

    private fun removeNullData(fromStart: Boolean, channel: Channel) {
        val index = if (fromStart) 0 else channelToMessages[channel]!!.size - 1
        if (channelToMessages[channel]!![index] == null) {
            channelToMessages[channel]!!.removeAt(index)
        }
    }

    private fun saveImageToTemporaryDir(link: String, bitmap: Bitmap) {
        val fileName = Utils.getPathToSaveImage(link, cacheDir)
        FileOutputStream(fileName).use {
            when (Utils.getFileExtension(fileName.name)) {
                Utils.JPEG_KEY -> bitmap.compress(Bitmap.CompressFormat.JPEG, 10, it)
                Utils.PNG_KEY -> bitmap.compress(Bitmap.CompressFormat.PNG, 10, it)
                Utils.UNKNOWN_EXTENSION -> return
            }
            it.flush()
        }
    }
}