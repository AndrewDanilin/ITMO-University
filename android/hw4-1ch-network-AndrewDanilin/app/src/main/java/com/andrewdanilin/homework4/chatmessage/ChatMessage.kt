package com.andrewdanilin.homework4.chatmessage

import android.graphics.Bitmap
import com.andrewdanilin.homework4.utils.Utils
import org.json.JSONObject

data class ChatMessage(
    val id: Int,
    val from: String,
    val to: String,
    val text: String?,
    val link: String?,
    val time: Long,
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

        fun buildJSONMessageToSend(from: String, to: String, text: String): JSONObject {
            val jsonObject = JSONObject()
            jsonObject.put(Utils.FROM, from)
            jsonObject.put(Utils.TO, to)
            val textObject = JSONObject()
            textObject.put(Utils.TEXT, text)
            val dataObject = JSONObject()
            dataObject.put(Utils.TEXT_CAPITALIZED, textObject)
            jsonObject.put(Utils.DATA, dataObject)
            return jsonObject
        }
    }
}
