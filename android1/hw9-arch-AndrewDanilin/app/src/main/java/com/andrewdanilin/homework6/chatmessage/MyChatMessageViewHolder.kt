package com.andrewdanilin.homework6.chatmessage

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework6.R
import com.andrewdanilin.homework6.databinding.MyChatMessageBinding
import com.andrewdanilin.homework6.utils.Utils
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class MyChatMessageViewHolder(
    private val chatMessageBinding: MyChatMessageBinding,
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
        if (chatMessage.data.text != null) {
            chatMessageBinding.messageText.text = chatMessage.data.text?.text
            chatMessageBinding.messageText.visibility = View.VISIBLE
        } else {
            chatMessageBinding.messageText.text = null
            chatMessageBinding.messageText.visibility = View.GONE
        }
    }

    private fun setImage(chatMessage: ChatMessage) {
        chatMessageBinding.messageImage.visibility = View.GONE
        chatMessageBinding.imageProgressBar.visibility = View.GONE
        if (chatMessage.data.image != null) {
            chatMessageBinding.messageImage.visibility = View.VISIBLE
            Picasso.get()
                .load(Utils.THUMB_URL + chatMessage.data.image?.link)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(chatMessageBinding.messageImage)
        }
    }

    private fun setTime(chatMessage: ChatMessage) {
        chatMessageBinding.messageTime.text = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(
            Date(chatMessage.time)
        )
    }
}