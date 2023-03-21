package com.andrewdanilin.homework6.chatmessage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework6.databinding.MyChatMessageBinding
import com.andrewdanilin.homework6.databinding.OtherChatMessageBinding
import com.andrewdanilin.homework6.databinding.ProgressBarBinding
import com.andrewdanilin.homework6.utils.Utils
import java.util.*

class ChatMessageAdapter(
    private val chatMessages: MutableList<ChatMessage?>,
    private val onClick: (ChatMessage) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private companion object {
        private const val MY = 0
        private const val OTHER = 1
        private const val PROGRESS_BAR = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder
        when (viewType) {
            MY -> {
                val binding = MyChatMessageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                binding.messageImage.setOnClickListener {
                    onClick(chatMessages[holder.adapterPosition]!!)
                }
                holder = MyChatMessageViewHolder(binding)
            }
            OTHER -> {
                val binding = OtherChatMessageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                binding.messageImage.setOnClickListener {
                    onClick(chatMessages[holder.adapterPosition]!!)
                }
                holder = OtherChatMessageViewHolder(binding)
            }
            PROGRESS_BAR -> {
                val binding = ProgressBarBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                holder = ProgressBarViewHolder(binding)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MY -> {
                (holder as MyChatMessageViewHolder).bind(chatMessages[position]!!)
            }
            OTHER -> {
                (holder as OtherChatMessageViewHolder).bind(chatMessages[position]!!)
            }
        }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int {
        if (chatMessages[position] != null) {
            return if (chatMessages[position]?.from == Utils.DEFAULT_SENDER) MY else OTHER
        }
        return PROGRESS_BAR
    }
}