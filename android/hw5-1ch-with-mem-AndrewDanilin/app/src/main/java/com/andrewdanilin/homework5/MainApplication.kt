package com.andrewdanilin.homework5

import android.app.Application
import androidx.room.Room
import java.io.File

class MainApplication: Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        instance = this
        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "messages"
        ).build()


        val thumb = File(cacheDir , "images/thumb/")
        val img = File(cacheDir, "images/img/")
        if (!thumb.exists()) {
            thumb.mkdir()
        }
        if (!img.exists()) {
            img.mkdir()
        }
    }


    companion object {
        lateinit var instance: MainApplication
            private set
    }
}