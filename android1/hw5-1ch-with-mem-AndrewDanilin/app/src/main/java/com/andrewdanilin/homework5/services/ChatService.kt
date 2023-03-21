package com.andrewdanilin.homework5.services

import android.app.Service
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.MediaStore.MediaColumns
import com.andrewdanilin.homework5.MainApplication
import com.andrewdanilin.homework5.chatmessage.ChatMessage
import com.andrewdanilin.homework5.extensions.iterator
import com.andrewdanilin.homework5.multipart.Field
import com.andrewdanilin.homework5.multipart.Type
import com.andrewdanilin.homework5.utils.Utils
import org.json.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream


class ChatService: Service() {

    companion object {
        private const val MESSAGES_TO_DOWNLOAD = 20
        private val FAILURE_REQUEST_MESSAGES_RESULT = Pair(-1, -1)
    }

    val idToChatMessage: HashMap<Int, ChatMessage>  = HashMap()
    private val linkToLowResolutionImage: HashMap<String, Bitmap> = HashMap()
    private val linkToHighResolutionImage: HashMap<String, Bitmap> = HashMap()

    private var threadList: MutableList<HandlerThread> = mutableListOf()
    var handlerList: MutableList<Handler?> = mutableListOf()
    var recyclerViewState: Parcelable? = null

    override fun onCreate() {
        super.onCreate()
        IntRange(0, 2).forEach {
            threadList.add(it, HandlerThread("thread $it", Process.THREAD_PRIORITY_BACKGROUND))
        }
        threadList.forEach { it.start() }
        threadList.forEach { handlerList.add(Handler(it.looper)) }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getStorageHandler().postAtFrontOfQueue {
            getAllMessagesFromDB()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return ChatBinder()
    }

    inner class ChatBinder: Binder() {
        fun getChatService() = this@ChatService
    }

    override fun onLowMemory() {
        super.onLowMemory()
        linkToLowResolutionImage.clear()
        linkToHighResolutionImage.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerList.forEach { it?.looper?.quit() }
    }

    fun requestFirstMessages(): Pair<Int, Int> {
        if (idToChatMessage.isEmpty()) {
            return requestMessages(idOfLastMessage = 0)
        }
        return Pair(0, idToChatMessage.size)
    }

    fun requestMessagesFromId(idOfLastMessage: Int): Pair<Int, Int> {
        return requestMessages(idOfLastMessage = idOfLastMessage)
    }

    private fun requestMessages(
        numberOfMessagesToDownload: Int = MESSAGES_TO_DOWNLOAD,
        idOfLastMessage: Int
    ) : Pair<Int, Int> {

        val response = Utils.getResponseFromURL(Utils.buildGetMessagesUrl(
        numberOfMessagesToDownload, idOfLastMessage))?: return FAILURE_REQUEST_MESSAGES_RESULT
        val messagesJSONArray = JSONArray(response)
        val prevSize = idToChatMessage.size
        for (messageJSON in messagesJSONArray) {
            val chatMessage = ChatMessage.fromJsonObject(messageJSON)
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

    fun downloadPicture(link: String, big: Boolean) {
        val localStorage = if (big) linkToHighResolutionImage else linkToLowResolutionImage
        if (!localStorage.containsKey(link)) {
            val bitmapFromCache = getImageFromCache(link, big)
            if (bitmapFromCache != null) {
                localStorage[link] = bitmapFromCache
            } else {
                val bitmapFromWeb = downloadImage(link, big)
                if (bitmapFromWeb != null) {
                    localStorage[link] = bitmapFromWeb
                    getStorageHandler().post {
                        saveImageToCache(link, big)
                    }
                }
            }
        }
    }

    fun sendTextMessage(text: String) {
        val id = Utils.sendMessageWithText(text)
        requestMessages(numberOfMessagesToDownload = 1, idOfLastMessage = id - 1)
    }

    fun sendImageMessage(contentUri: Uri) {
        var bitmap: Bitmap?
        contentResolver.openInputStream(contentUri).use {
            bitmap = BitmapFactory.decodeStream(it)
        }
        val imageName = System.currentTimeMillis().toString() + ".png"
        linkToLowResolutionImage[imageName] = bitmap!!
        saveImageToCache(imageName, false)
        val fields: MutableList<Field> = mutableListOf()
        fields.add(Field(
            "msg",
            ChatMessage.buildJSONMessageToSend(
                Utils.DEFAULT_SENDER,
                Utils.DEFAULT_RECIPIENT,
                link = Utils.DEFAULT_SENDER + "/" + imageName
            ).toString().replace("\\/", "/"),
            Type.JSON
        ))
        fields.add(Field(
            "pic",
            Utils.getPathToSaveImage(imageName, false, cacheDir).toString(),
            Type.FILE
        ))
        val id = Utils.sendMessageWithImage(fields)
        if (id != -1) {
            requestMessages(numberOfMessagesToDownload = 1, idOfLastMessage = id - 1)
        }
    }

    fun saveAllData() {
        getStorageHandler().post {
            putAllMessagesToDB()
        }
    }

    private fun getAllMessagesFromDB() {
        val chatMessages = (application as MainApplication).db.chatMessageDao().getAll()
        for (chatMessage in chatMessages) {
            idToChatMessage[chatMessage.id] = chatMessage
        }
    }

    private fun saveImageToCache(link: String, highResolution: Boolean) {
        val fileName = Utils.getPathToSaveImage(link, highResolution, cacheDir)
        val bitmap = if (highResolution) linkToHighResolutionImage[link] else linkToLowResolutionImage[link]
        FileOutputStream(fileName).use {
            when (Utils.getFileExtension(fileName.name)) {
                Utils.JPEG_KEY -> bitmap?.compress(Bitmap.CompressFormat.JPEG, 10, it)
                Utils.PNG_KEY -> bitmap?.compress(Bitmap.CompressFormat.PNG, 10, it)
                Utils.UNKNOWN_EXTENSION -> return
            }
            it.flush()
        }
    }

    private fun getImageFromCache(link: String, highResolution: Boolean): Bitmap? {
        val fileName = Utils.getPathToSaveImage(link, highResolution, cacheDir)
        var bitmap: Bitmap?
        try {
            FileInputStream(fileName).use {
                bitmap = BitmapFactory.decodeStream(it)
            }
        } catch (error : FileNotFoundException) {
            bitmap = null
        }
        return bitmap
    }

    private fun putAllMessagesToDB() {
        (application as MainApplication).db.chatMessageDao()
            .insertAll(idToChatMessage.values.toList())
    }

    private fun downloadImage(link: String, highResolution: Boolean) : Bitmap? {
        return Utils.getBitmapFromUrl(Utils.buildDownloadImageUrl(link, highResolution))
    }

    private fun getServiceHandler(): Handler {
        return handlerList[0]!!
    }

    private fun getStorageHandler(): Handler {
        return handlerList[1]!!
    }
}