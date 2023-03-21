package com.andrewdanilin.homework5

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.andrewdanilin.homework5.databinding.PictureAcitvityBinding
import com.andrewdanilin.homework5.services.ChatService
import com.andrewdanilin.homework5.utils.Utils

class PictureActivity: AppCompatActivity() {
    private lateinit var binding: PictureAcitvityBinding
    private var chatService: ChatService? = null
    private var isBound = false
    lateinit var linkToPicture: String
    private val mainHandler = Handler(Looper.getMainLooper())

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binderBridge: ChatService.ChatBinder = service as ChatService.ChatBinder
            chatService = binderBridge.getChatService()
            isBound = true

            getServiceHandler().post {
                chatService!!.downloadPicture(linkToPicture, true)
                mainHandler.post {
                    binding.mainProgressBar.visibility = View.INVISIBLE
                    binding.pictureView.setImageBitmap(chatService!!.getImage(linkToPicture, true))
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            chatService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PictureAcitvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linkToPicture = intent.extras!!.getString(Utils.LINK)!!
        binding.mainProgressBar.visibility = View.VISIBLE
        setupCloseButton()
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this@PictureActivity, ChatService::class.java)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(boundServiceConnection)
        }
    }

    fun getServiceHandler(): Handler {
        return chatService!!.handlerList[0]!!
    }

    private fun setupCloseButton() {
        binding.closeButton.setOnClickListener {
            finish()
        }
    }
}