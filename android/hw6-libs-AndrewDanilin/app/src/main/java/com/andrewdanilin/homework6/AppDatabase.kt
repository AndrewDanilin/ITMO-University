package com.andrewdanilin.homework6

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrewdanilin.homework6.chatmessage.ChatMessage
import com.andrewdanilin.homework6.chatmessage.ChatMessagesDao

@Database(entities = [ChatMessage::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessagesDao
}