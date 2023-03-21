package com.andrewdanilin.homework7.fragments

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewdanilin.homework7.ChatActivity
import com.andrewdanilin.homework7.MainActivity
import com.andrewdanilin.homework7.R
import com.andrewdanilin.homework7.channel.ChannelAdapter
import com.andrewdanilin.homework7.databinding.FragmentChannelsBinding
import com.andrewdanilin.homework7.services.ChatService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChannelsFragment: Fragment() {

    private lateinit var binding: FragmentChannelsBinding
    private lateinit var scope: CoroutineScope
    var chatService: ChatService? = null
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scope = CoroutineScope(Dispatchers.Main)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChannelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun showChannels() {
        binding.channelsView.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = ChannelAdapter(chatService!!.channels,
                onClick = {

                    val chatFragment = parentFragmentManager.findFragmentById(R.id.chatContainerView) as ChatFragment?

                    if (chatFragment != null && chatFragment.isVisible) {
                        chatFragment.changeChat(it)
                    } else {
                        val intent = Intent(activity, ChatActivity::class.java).apply {
                            putExtra("CHANNEL_NAME", it.name)
                        }
                        startActivity(intent)
                    }

                }
            )
        }
    }

    override fun onStop() {
        if (isBound) {
            activity?.unbindService(boundServiceConnection)
        }
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(activity, ChatService::class.java)
        activity?.startService(intent)
        activity?.bindService(intent, boundServiceConnection, AppCompatActivity.BIND_AUTO_CREATE)
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binderBridge: ChatService.ChatBinder = service as ChatService.ChatBinder
            chatService = binderBridge.getChatService()
            isBound = true

            scope.launch {
                chatService!!.requestChannels()
                showChannels()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            chatService = null
        }
    }
}