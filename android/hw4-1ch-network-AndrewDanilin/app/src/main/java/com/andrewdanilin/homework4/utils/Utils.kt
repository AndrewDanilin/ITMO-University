package com.andrewdanilin.homework4.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileNotFoundException
import java.io.IOException
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
        private const val TIMEOUT = 3000

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
                null
            } catch (error: IOException) {
                null
            } catch (e: FileNotFoundException) {
                null
            } finally {
                urlConnection.disconnect()
            }
        }

        fun getResponseFromURL(url: URL): String? {
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = TIMEOUT
            return try {
                BufferedInputStream(urlConnection.inputStream)
                    .readBytes()
                    .toString(Charset.defaultCharset())
            } catch (error: SocketTimeoutException) {
                null
            } catch (error: IOException) {
                null
            } finally {
                urlConnection.disconnect()
            }
        }

        fun postMessage(chatMessageJSON: JSONObject): String? {
            val urlConnection = buildPostMessageUrl().openConnection() as HttpURLConnection
            try {
                urlConnection.connectTimeout = TIMEOUT
                urlConnection.requestMethod = "POST"
                urlConnection.addRequestProperty("Content-Type", "application/json")
                urlConnection.doOutput = true
                val jsonString = chatMessageJSON.toString()
                val outputStream = BufferedOutputStream(urlConnection.outputStream)
                val input = jsonString.toByteArray(Charset.defaultCharset())
                outputStream.write(input, 0, input.size)
                outputStream.flush()
                outputStream.close()
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                return inputStream.readBytes().toString(Charset.defaultCharset())
            } catch (error: SocketTimeoutException) {
                return null
            } catch (error: IOException) {
                return null
            } finally {
                urlConnection.disconnect()
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

        private fun buildPostMessageUrl(): URL =
            URL(getHostAddressBuilder().appendPath(ONE_CH).toString())

        private fun getHostAddressBuilder(): Uri.Builder {
            val uriBuilder = Uri.Builder()
            return uriBuilder.scheme(HTTP).encodedAuthority(HOST_ADDRESS)
        }

    }
}
