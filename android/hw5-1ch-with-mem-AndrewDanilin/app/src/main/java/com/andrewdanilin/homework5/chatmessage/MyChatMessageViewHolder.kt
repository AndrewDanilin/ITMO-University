package com.andrewdanilin.homework5.chatmessage

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework5.R
import com.andrewdanilin.homework5.databinding.MyChatMessageBinding
import java.text.SimpleDateFormat
import java.util.*

class MyChatMessageViewHolder(
    private val chatMessageBinding: MyChatMessageBinding,
    private val downloadImage: (ChatMessage, ImageView) -> Unit
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
        if (chatMessage.link != null) {
            chatMessageBinding.messageImage.visibility = View.VISIBLE
            chatMessageBinding.messageImage.setImageResource(R.drawable.placeholder_image)
            downloadImage(
                chatMessage,
                chatMessageBinding.messageImage
            )
        }
    }

    private fun setTime(chatMessage: ChatMessage) {
        chatMessageBinding.messageTime.text = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(
            Date(chatMessage.time)
        )
    }
}