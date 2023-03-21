package com.andrewdanilin.homework7

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrewdanilin.homework7.channel.Channel
import com.andrewdanilin.homework7.databinding.ChatActivityBinding
import com.andrewdanilin.homework7.fragments.ChatFragment
import com.andrewdanilin.homework7.services.ChatService
import kotlinx.coroutines.CoroutineScope

class ChatActivity: AppCompatActivity() {
    private lateinit var binding: ChatActivityBinding
    private var channelName: String? = null
    private var isBound = false
    var chatService: ChatService? = null
    private lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        binding = ChatActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            channelName = intent.getStringExtra("CHANNEL_NAME")!!
        }
    }

    override fun onResume() {
        super.onResume()
        val chatFragment = supportFragmentManager.findFragmentById(R.id.chatContainerView) as ChatFragment?
        chatFragment?.channel = Channel(channelName!!)
    }
}