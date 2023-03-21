package com.andrewdanilin.homework6.services

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.os.Parcelable
import com.andrewdanilin.homework6.MainApplication
import com.andrewdanilin.homework6.R
import com.andrewdanilin.homework6.chatmessage.ChatMessage
import com.andrewdanilin.homework6.utils.Utils
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.FileOutputStream
import java.util.*

class ChatService: Service() {

    companion object {
        private const val MESSAGES_TO_DOWNLOAD = 50
        private const val NUMBER_OF_MESSAGES = 10_000
    }

    enum class RequestMessagesType {
        NEW_MESSAGES, OLD_MESSAGES, FIRST_MESSAGES
    }

    lateinit var scope: CoroutineScope

    val chatMessages: MutableList<ChatMessage?> = mutableListOf()
    private val setOfIds = TreeSet<Int>()

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

    suspend fun requestMessages(requestType: RequestMessagesType): ServiceRequestResult {
        return try {
            when(requestType) {
                RequestMessagesType.FIRST_MESSAGES -> getFirstMessages()
                RequestMessagesType.NEW_MESSAGES -> getNewMessages()
                RequestMessagesType.OLD_MESSAGES -> getOldMessages()
            }
        } catch (e: HttpException) {
            System.err.println(e.localizedMessage)
            handleResponseCode(e.code())
        }
    }

    private suspend fun getFirstMessages(): ServiceRequestResult {
        if (chatMessages.isEmpty()) {
            val messages = MainApplication.instance.messagesAPI.getMessages(
                MESSAGES_TO_DOWNLOAD,
                NUMBER_OF_MESSAGES,
                true
            )
            return addMessages(messages.reversed(), true)
        }
        return ServiceRequestResult(Result.SUCCESS, first = 0, second = chatMessages.size)
    }

    private suspend fun getNewMessages(): ServiceRequestResult {
        val messages = MainApplication.instance.messagesAPI.getMessages(
            MESSAGES_TO_DOWNLOAD,
            setOfIds.last(),
            false
        )
        return addMessages(messages, false)
    }

    private suspend fun getOldMessages(): ServiceRequestResult {
        val messages = MainApplication.instance.messagesAPI.getMessages(
            MESSAGES_TO_DOWNLOAD,
            setOfIds.first() - MESSAGES_TO_DOWNLOAD - 1,
            false
        )
        return addMessages(messages, true)
    }

    private fun addMessages(messages: List<ChatMessage>, toStart: Boolean): ServiceRequestResult {
        val newMessages = mutableListOf<ChatMessage?>()
        for(message in messages) {
            if (!setOfIds.contains(message.id)) {
                if (message.data.text != null && message.data.text!!.text.trim().isEmpty()) {
                    continue
                } else {
                    setOfIds.add(message.id)
                    newMessages.add(message)
                }
            }
        }
        removeNulls()
        val index = if (toStart) 0 else chatMessages.size
        val prevSize = chatMessages.size
        chatMessages.addAll(index, newMessages)
        return ServiceRequestResult(
            Result.SUCCESS,
            first = index,
            second = chatMessages.size - prevSize
        )
    }

    private fun handleResponseCode(responseCode: Int): ServiceRequestResult {
        return if (responseCode in 500..599) {
            ServiceRequestResult(
                Result.FAILURE,
                text = getString(R.string.something_went_wrong)
            )
        } else {
            ServiceRequestResult(
                Result.FAILURE,
                text = getString(R.string.failed_to_connect_to_server)
            )
        }
    }

    suspend fun sendTextMessage(text: String): ServiceRequestResult {
        try {
            val responseBody = MainApplication.instance.messagesAPI.sendTextMessage(
                RequestBody.create(
                    MediaType.parse("application/json"),
                    "{" +
                            "\"from\":\"${Utils.DEFAULT_SENDER}\"," +
                            " \"data\":{\"Text\":{\"text\":\"$text\"}}}"
                )
            )
            val idOfMessage = responseBody.string().toInt()
            val message = MainApplication.instance.messagesAPI.getMessages(
                1,
                idOfMessage - 1,
                false
            )
            return addMessages(message, false)
        } catch (exception: HttpException) {
            System.err.println(exception.localizedMessage)
            return handleResponseCode(exception.code())
        }
    }

    suspend fun sendImageMessage(contentUri: Uri): ServiceRequestResult {
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
                    "{\"from\":\"${Utils.DEFAULT_SENDER}\"}"
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
                1,
                idOfMessage - 1,
                false
            )
            return addMessages(message, false)
        } catch (exception: HttpException) {
            System.err.println(exception.localizedMessage)
            return when (exception.code()) {
                409 -> sendImageMessage(contentUri)
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

    fun addNullData(toStart: Boolean) {
        chatMessages.add(if (toStart) 0 else chatMessages.size, null)
    }

    private fun removeNulls() {
        if (chatMessages.size > 0) {
            removeNullData(false)
        }
        if (chatMessages.size > 0) {
            removeNullData(true)
        }
    }

    private fun removeNullData(fromStart: Boolean) {
        val index = if (fromStart) 0 else chatMessages.size - 1
        if (chatMessages[index] == null) {
            chatMessages.removeAt(index)
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

    fun getAllMessagesFromDB() {
        val messages = (application as MainApplication).db.chatMessageDao().getAll()
        messages.forEach {
            if (!setOfIds.contains(it.id)) {
                setOfIds.add(it.id)
                chatMessages.add(it)
            }
        }
    }

    fun saveAllMessagesToDB() {
        (application as MainApplication).db.chatMessageDao().insertAll(chatMessages)
    }
}