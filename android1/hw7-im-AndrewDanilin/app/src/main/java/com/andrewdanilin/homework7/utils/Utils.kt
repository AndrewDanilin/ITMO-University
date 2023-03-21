package com.andrewdanilin.homework7.utils

import java.io.*

class Utils {

    companion object {
        const val BASE_URL = "http://213.189.221.170:8008/"
        const val THUMB_URL = "http://213.189.221.170:8008/thumb/"
        const val IMG_URL = "http://213.189.221.170:8008/img/"

        const val LINK = "link"

        private const val JPEG = "jpg"
        private const val PNG = "png"

        const val JPEG_KEY = 0
        const val PNG_KEY = 1
        const val UNKNOWN_EXTENSION = -1

        const val DEFAULT_SENDER = "Andrew"
        const val DEFAULT_RECIPIENT = "1@channel"

        const val REQUEST_PERMISSION_ID = 77

        fun getPathToSaveImage(link: String, dir: File): File {
            var pathToFolder = File(dir, "tmp/")
            if (File(link).parent != null) {
                pathToFolder = File(pathToFolder, File(link).parent!!)
            }
            pathToFolder.mkdirs()
            return File(pathToFolder, File(link).name)
        }

        fun getFileExtension(fileName: String): Int {
            return when (fileName.substring(fileName.lastIndexOf('.') + 1)) {
                JPEG -> JPEG_KEY
                PNG -> PNG_KEY
                else -> UNKNOWN_EXTENSION
            }
        }
    }
}