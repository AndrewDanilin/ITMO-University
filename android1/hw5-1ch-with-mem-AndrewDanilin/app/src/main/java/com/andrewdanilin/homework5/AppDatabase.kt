package com.andrewdanilin.homework5

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrewdanilin.homework5.chatmessage.ChatMessage
import com.andrewdanilin.homework5.chatmessage.ChatMessagesDao

@Database(entities = [ChatMessage::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessagesDao
}