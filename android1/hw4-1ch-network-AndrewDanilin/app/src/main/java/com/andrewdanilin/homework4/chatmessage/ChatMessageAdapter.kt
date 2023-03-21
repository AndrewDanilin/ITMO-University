package com.andrewdanilin.homework4.chatmessage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework4.databinding.MyChatMessageBinding
import com.andrewdanilin.homework4.databinding.OtherChatMessageBinding
import com.andrewdanilin.homework4.utils.Utils

class ChatMessageAdapter(
    private val idToChatMessage: HashMap<Int, ChatMessage>,
    private val onClick: (ChatMessage) -> Unit,
    private val downloadImage: (ChatMessage, ImageView, ProgressBar) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private companion object {
        private const val MY = 0
        private const val OTHER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder
        when (viewType) {
            MY -> {
                val binding = MyChatMessageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                binding.messageImage.setOnClickListener {
                    onClick(idToChatMessage.values.toList()[holder.adapterPosition])
                }
                holder = MyChatMessageViewHolder(binding, downloadImage)
            }
            OTHER -> {
                val binding = OtherChatMessageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                binding.messageImage.setOnClickListener {
                    onClick(idToChatMessage.values.toList()[holder.adapterPosition])
                }
                holder = OtherChatMessageViewHolder(binding, downloadImage)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MY -> {
                (holder as MyChatMessageViewHolder).bind(idToChatMessage.values.toList()[position])
            }
            OTHER -> {
                (holder as OtherChatMessageViewHolder).bind(idToChatMessage.values.toList()[position])
            }
        }
    }

    override fun getItemCount(): Int = idToChatMessage.size

    override fun getItemViewType(position: Int): Int {
        return if (idToChatMessage.values.toList()[position].from == Utils.DEFAULT_SENDER) MY else OTHER
    }
}