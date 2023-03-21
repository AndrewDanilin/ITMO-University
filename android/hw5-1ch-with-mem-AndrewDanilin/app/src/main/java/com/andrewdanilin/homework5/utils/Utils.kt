package com.andrewdanilin.homework5.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.andrewdanilin.homework5.chatmessage.ChatMessage
import com.andrewdanilin.homework5.multipart.Field
import com.andrewdanilin.homework5.multipart.MultipartTool
import com.andrewdanilin.homework5.multipart.Type
import java.io.*
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.nio.charset.Charset


class Utils {
    companion object {
        private const val HTTP = "HTTP"
        private const val HOST_ADDRESS = "213.189.221.170:8008"
        private const val ONE_CH = "1ch"
        private const val LIMIT = "limit"
        private const val LAST_KNOWN_ID = "lastKnownId"
        private const val IMG = "img"
        private const val THUMB = "thumb"
        private const val TIMEOUT = 5000
        private const val MESSAGES_TO_DOWNLOAD = 20

        private const val JPEG = "jpg"
        private const val PNG = "png"
        const val JPEG_KEY = 0
        const val PNG_KEY = 1
        const val UNKNOWN_EXTENSION = -1

        const val DEFAULT_SENDER = "Andrew"
        const val DEFAULT_RECIPIENT = "1@channel"

        const val ID = "id"
        const val DATA = "data"
        const val FROM = "from"
        const val TO = "to"
        const val TIME = "time"
        const val TEXT = "text"
        const val TEXT_CAPITALIZED = "Text"
        const val LINK = "link"
        const val IMAGE = "Image"

        const val REQUEST_PERMISSION_ID = 77

        fun getBitmapFromUrl(url: URL): Bitmap? {
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = TIMEOUT
            return try {
                BitmapFactory.decodeStream(BufferedInputStream(urlConnection.inputStream))
            } catch (error: SocketTimeoutException) {
                return null
            } catch (error: IOException) {
                return null
            } catch (e: FileNotFoundException) {
                null
            } finally {
                urlConnection.disconnect()
            }
        }

        fun getResponseFromURL(url: URL): String? {
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = TIMEOUT
            try {
                return BufferedInputStream(urlConnection.inputStream)
                    .readBytes()
                    .toString(Charset.defaultCharset())
            } catch (error: SocketTimeoutException) {
                return null
            } catch (error: IOException) {
                return null
            } finally {
                urlConnection.disconnect()
            }
        }

        fun sendMessageWithImage(fields: List<Field>): Int {
            val httpURLConnection = buildPostMessageUrl().openConnection() as HttpURLConnection
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true
            httpURLConnection.requestMethod = "POST"
            val boundary = MultipartTool.generateBoundary()
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary)
            val outputStream = httpURLConnection.outputStream
            val tool = MultipartTool(outputStream, boundary)
            try {
                for (f in fields) {
                    when (f.type) {
                        Type.TEXT -> tool.appendField(
                            f.name,
                            f.data,
                            "text/plain; charset=utf-8"
                        )
                        Type.FILE -> tool.appendFile(f.name, File(f.data))
                        Type.JSON -> tool.appendJsonField(f.name, f.data)
                        else -> throw IllegalStateException(f.type.name + " is unknown")
                    }
                }
            } finally {
                tool.close()
                outputStream.close()
            }
            val code = httpURLConnection.responseCode
            val resp = getResponseFromConnection(httpURLConnection)
            println(code)
            println(resp)
            httpURLConnection.disconnect()
            return resp?.toInt() ?: -1
        }

        fun sendMessageWithText(text: String): Int {
            val httpURLConnection = buildPostMessageUrl().openConnection() as HttpURLConnection
            try {
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.addRequestProperty("Content-Type", "application/json")
                val chatMessageJSON = ChatMessage.buildJSONMessageToSend(
                    DEFAULT_SENDER,
                    DEFAULT_RECIPIENT,
                    text = text
                )
                val jsonString = chatMessageJSON.toString()
                BufferedOutputStream(httpURLConnection.outputStream).use {
                    val bytes = jsonString.toByteArray(Charset.defaultCharset())
                    it.write(bytes, 0, bytes.size)
                    it.flush()
                }
                val resp = getResponseFromConnection(httpURLConnection)
                return resp?.toInt() ?: -1
            } catch (e: IOException) {
                return -1
            } finally {
                httpURLConnection.disconnect()
            }
        }

        private fun getResponseFromConnection(connection: HttpURLConnection): String? {
            if (connection.responseCode < 200 || connection.responseCode >= 300) {
                return null;
            } else {
                val resp = BufferedReader(InputStreamReader(connection.inputStream))
                var str = ""
                try {
                    var line: String?
                    while (resp.readLine().also { line = it } != null) {
                        str += line.orEmpty()
                    }
                } finally {
                    resp.close()
                }
                return str
            }
        }

        fun buildDownloadImageUrl(link: String, highResolution: Boolean): URL = URL(
            getHostAddressBuilder()
                .appendPath(if (highResolution) IMG else THUMB)
                .appendPath(link)
                .build()
                .toString()
        )


        fun buildGetMessagesUrl(limit: Int = 20, lastKnownId: Int = 0): URL = URL(
            getHostAddressBuilder()
                .appendPath(ONE_CH)
                .appendQueryParameter(LIMIT, limit.toString())
                .appendQueryParameter(LAST_KNOWN_ID, lastKnownId.toString())
                .build()
                .toString()
        )

        fun getPathToSaveImage(link: String, highResolution: Boolean, dir: File): File {
            var pathToFolder = if (highResolution) {
                File(dir, "images/img/")
            } else {
                File(dir, "images/thumb/")
            }
            if (File(link).parent != null) {
                pathToFolder = File(pathToFolder, File(link).parent!!)
            }
            pathToFolder.mkdirs()
            val fileName = File(link).name
            pathToFolder = File(pathToFolder, fileName)
            return pathToFolder
        }

        fun getFileExtension(fileName: String): Int {
            return when (fileName.substring(fileName.lastIndexOf('.') + 1)) {
                JPEG -> JPEG_KEY
                PNG -> PNG_KEY
                else -> UNKNOWN_EXTENSION
            }
        }

        private fun buildPostMessageUrl(): URL =
            URL(getHostAddressBuilder().appendPath(ONE_CH).toString())

        private fun getHostAddressBuilder(): Uri.Builder {
            val uriBuilder = Uri.Builder()
            return uriBuilder.scheme(HTTP).encodedAuthority(HOST_ADDRESS)
        }
    }
}