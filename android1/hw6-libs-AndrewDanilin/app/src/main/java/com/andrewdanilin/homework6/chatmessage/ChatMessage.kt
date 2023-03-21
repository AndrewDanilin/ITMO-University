package com.andrewdanilin.homework6.chatmessage

import androidx.room.*
import com.squareup.moshi.Json

@Entity
data class ChatMessage(
    @PrimaryKey @field:Json(name = "id") val id: Int,
    @ColumnInfo @field:Json(name = "from") val from: String,
    @ColumnInfo @field:Json(name = "to") val to: String,
    @Embedded @field:Json(name = "data") var data: Data,
    @ColumnInfo @field:Json(name = "time") val time: Long
)

data class Data(
    @Embedded @field:Json(name = "Text") val text: Text?,
    @Embedded @field:Json(name = "Image") val image: Image?
)

data class Text(
    @ColumnInfo @field:Json(name = "text") val text: String
)

data class Image(
    @ColumnInfo @field:Json(name = "link") val link: String
)



@Dao
interface ChatMessagesDao {
    @Query("SELECT * from chatMessage")
    fun getAll(): List<ChatMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(chatMessages: MutableList<ChatMessage?>)
}
