package com.andrewdanilin.homework5.chatmessage

import androidx.room.*
import com.andrewdanilin.homework5.utils.Utils
import org.json.JSONObject

@Entity
data class ChatMessage(
    @PrimaryKey val id: Int,
    @ColumnInfo val from: String,
    @ColumnInfo val to: String,
    @ColumnInfo val text: String?,
    @ColumnInfo val link: String?,
    @ColumnInfo val time: Long,
) {

    fun toJsonObject(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(Utils.ID, id)
        jsonObject.put(Utils.FROM, from)
        jsonObject.put(Utils.TO, to)
        val dataJson = JSONObject()
        if (text != null) {
            val textJSON = JSONObject()
            textJSON.put(Utils.TEXT, text)
            dataJson.put(Utils.TEXT_CAPITALIZED, textJSON)
        }
        if (link != null) {
            val linkJSON = JSONObject()
            linkJSON.put(Utils.LINK, link)
            dataJson.put(Utils.IMAGE, linkJSON)
        }
        jsonObject.put(Utils.DATA, dataJson)
        jsonObject.put(Utils.TIME, time)
        return jsonObject
    }

    companion object {

        fun fromJsonObject(jsonObject: JSONObject): ChatMessage {
            return ChatMessage(
                jsonObject.getInt(Utils.ID),
                jsonObject.getString(Utils.FROM),
                jsonObject.getString(Utils.TO),
                jsonObject.getJSONObject(Utils.DATA).optJSONObject(Utils.TEXT_CAPITALIZED)?.getString(Utils.TEXT),
                jsonObject.getJSONObject(Utils.DATA).optJSONObject(Utils.IMAGE)?.getString(Utils.LINK),
                jsonObject.getLong(Utils.TIME)
            )
        }

        fun buildJSONMessageToSend(from: String, to: String, text: String? = null, link: String? = null): JSONObject {
            val jsonObject = JSONObject()
            jsonObject.put(Utils.FROM, from)
            jsonObject.put(Utils.TO, to)
            val dataObject = JSONObject()
            if (text != null) {
                val textObject = JSONObject()
                textObject.put(Utils.TEXT, text)
                dataObject.put(Utils.TEXT_CAPITALIZED, textObject)
            } else if (link != null) {
                val imageObject = JSONObject()
                imageObject.put(Utils.LINK, link)
                dataObject.put(Utils.IMAGE, imageObject)
            }
            jsonObject.put(Utils.DATA, dataObject)
            return jsonObject
        }
    }
}

@Dao
interface ChatMessagesDao {
    @Query("SELECT * from chatMessage")
    fun getAll(): List<ChatMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(chatMessages: List<ChatMessage>)
}
