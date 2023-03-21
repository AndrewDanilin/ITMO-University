package com.andrewdanilin.homework7.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework7.databinding.ChannelBinding

data class Channel(
    val name: String
)

class ChannelAdapter(
    private val channels: MutableList<Channel>,
    private val onClick: (Channel) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder
        val binding = ChannelBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        binding.channelName.setOnClickListener {
            onClick(channels[holder.adapterPosition])
        }
        holder = ChannelViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChannelViewHolder).bind(channels[position])
    }

    override fun getItemCount(): Int = channels.size
}

class ChannelViewHolder(
    private val channelBinding: ChannelBinding,
) : RecyclerView.ViewHolder(channelBinding.root) {

    fun bind(channel: Channel) {
        channelBinding.channelName.text = channel.name
    }
}