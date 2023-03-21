package com.andrewdanilin.homework7

import android.app.Application
import androidx.room.Room
import com.andrewdanilin.homework7.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class MainApplication: Application() {

    lateinit var db: AppDatabase
    lateinit var messagesAPI: MessagesAPI

    override fun onCreate() {
        super.onCreate()

        instance = this

        val retrofit = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        messagesAPI = retrofit.create(MessagesAPI::class.java)


        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "messages"
        ).build()


        val tmpDir = File(cacheDir , "tmp")
        if (!tmpDir.exists()) {
            tmpDir.mkdir()
        }
    }


    companion object {
        lateinit var instance: MainApplication
            private set
    }
}