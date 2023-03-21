package com.andrewdanilin.homework4.services

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.*
import com.andrewdanilin.homework4.chatmessage.ChatMessage
import com.andrewdanilin.homework4.extensions.iterator
import com.andrewdanilin.homework4.utils.Utils
import org.json.*

class ChatService: Service() {

    companion object {
        private const val MESSAGES_TO_DOWNLOAD = 20
    }

    val idToChatMessage: HashMap<Int, ChatMessage>  = HashMap()
    private val linkToLowResolutionImage: HashMap<String, Bitmap> = HashMap()
    private val linkToHighResolutionImage: HashMap<String, Bitmap> = HashMap()

    private lateinit var serviceThread: Thread
    var serviceHandler: Handler? = null
    var recyclerViewState: Parcelable? = null

    override fun onCreate() {
        super.onCreate()
        serviceThread = Thread {
            Looper.prepare()
            serviceHandler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }
        serviceThread.start()
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
        recyclerViewState = null
        serviceHandler = null
        serviceThread.interrupt()
    }

    fun requestMessages(
        numberOfMessagesToDownload: Int = MESSAGES_TO_DOWNLOAD,
        idOfLastMessage: Int
    ) : Pair<Int, Int> {
        val response = Utils.getResponseFromURL(Utils.buildGetMessagesUrl(
            numberOfMessagesToDownload, idOfLastMessage)) ?: return Pair(-1, -1)
        val messagesJSONArray = JSONArray(response)
        val prevSize = idToChatMessage.size
        for (messageJSON in messagesJSONArray) {
            val chatMessage = ChatMessage.fromJsonObject(messageJSON)
            if (chatMessage.text?.isEmpty() == true && chatMessage.link == null) {
                continue
            }
            if (!idToChatMessage.containsKey(chatMessage.id)) {
                idToChatMessage[chatMessage.id] = chatMessage
            }
        }
        return Pair(prevSize, idToChatMessage.size - prevSize)
    }

    fun getLastMessages(): Pair<Int, Int> {
        val prevSize = idToChatMessage.size
        var lastId = idToChatMessage.values.toList().last().id
        while (true) {
            val result = requestMessages(100, lastId)
            lastId += 100
            if (result.second == 0) {
                break
            }
        }
        return Pair(prevSize, idToChatMessage.size - prevSize)
    }

    fun getImage(link: String, highResolution: Boolean): Bitmap? {
        return when (highResolution) {
            true -> linkToHighResolutionImage[link]
            false -> linkToLowResolutionImage[link]
        }
    }

    fun downloadBigPicture(link: String) {
        if (!linkToHighResolutionImage.containsKey(link)) {
            val bitmap = downloadImageHighResolution(link)
            if (bitmap != null) {
                linkToHighResolutionImage[link] = bitmap
            }
        }
    }

    fun downloadSmallPicture(link: String) {
        if (!linkToLowResolutionImage.containsKey(link)) {
            val bitmap = downloadImageLowResolution(link)
            if (bitmap != null) {
                linkToLowResolutionImage[link] = bitmap
            }
        }
    }

    fun sendMessage(text: String): Boolean {
        val myChatMessageId = Utils.postMessage(
            ChatMessage.buildJSONMessageToSend(
                Utils.DEFAULT_SENDER,
                Utils.DEFAULT_RECIPIENT,
                text
            )
        )?.toInt() ?: return false
        val responseToExtraInformationJSON = JSONArray(Utils.getResponseFromURL(
            Utils.buildGetMessagesUrl(1, myChatMessageId - 1)
            )
        )
        val myChatMessageWithExtraInformation = ChatMessage.fromJsonObject(
            responseToExtraInformationJSON.getJSONObject(0)
        )
        idToChatMessage[myChatMessageId] = myChatMessageWithExtraInformation
        return true
    }

    private fun downloadImageHighResolution(link: String) : Bitmap? {
        return Utils.getBitmapFromUrl(Utils.buildDownloadImageUrl(link, true))
    }

    private fun downloadImageLowResolution(link: String) : Bitmap? {
        return Utils.getBitmapFromUrl(Utils.buildDownloadImageUrl(link, false))
    }
}
