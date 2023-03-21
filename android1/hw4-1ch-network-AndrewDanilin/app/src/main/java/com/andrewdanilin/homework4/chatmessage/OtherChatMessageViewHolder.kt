package com.andrewdanilin.homework4.chatmessage

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework4.R
import com.andrewdanilin.homework4.databinding.OtherChatMessageBinding
import java.text.SimpleDateFormat
import java.util.*

class OtherChatMessageViewHolder(
    private val chatMessageBinding: OtherChatMessageBinding,
    private val downloadImage: (ChatMessage, ImageView, ProgressBar) -> Unit
) : RecyclerView.ViewHolder(chatMessageBinding.root) {

    fun bind(chatMessage: ChatMessage) {
        setFrom(chatMessage)
        setText(chatMessage)
        setImage(chatMessage)
        setTime(chatMessage)
    }

    private fun setFrom(chatMessage: ChatMessage) {
        chatMessageBinding.messageFromName.text = chatMessage.from
    }

    private fun setText(chatMessage: ChatMessage) {
        if (chatMessage.text != null) {
            chatMessageBinding.messageText.text = chatMessage.text
            chatMessageBinding.messageText.visibility = View.VISIBLE
        } else {
            chatMessageBinding.messageText.text = null
            chatMessageBinding.messageText.visibility = View.GONE
        }
    }

    private fun setImage(chatMessage: ChatMessage) {
        chatMessageBinding.messageImage.visibility = View.GONE
        chatMessageBinding.imageProgressBar.visibility = View.GONE
        if (chatMessage.link != null) {
            chatMessageBinding.imageProgressBar.visibility = View.VISIBLE
            downloadImage(
                chatMessage,
                chatMessageBinding.messageImage,
                chatMessageBinding.imageProgressBar
            )
        }
    }

    private fun setTime(chatMessage: ChatMessage) {
        chatMessageBinding.messageTime.text = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(
            Date(chatMessage.time)
        )
    }
}