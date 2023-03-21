package com.andrewdanilin.homework7.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework7.ChatActivity
import com.andrewdanilin.homework7.PictureActivity
import com.andrewdanilin.homework7.R
import com.andrewdanilin.homework7.channel.Channel
import com.andrewdanilin.homework7.channel.ChannelAdapter
import com.andrewdanilin.homework7.chatmessage.ChatMessageAdapter
import com.andrewdanilin.homework7.databinding.FragmentChatBinding
import com.andrewdanilin.homework7.extensions.permissionIsGranted
import com.andrewdanilin.homework7.extensions.requestPermission
import com.andrewdanilin.homework7.services.ChatService
import com.andrewdanilin.homework7.services.Result
import com.andrewdanilin.homework7.utils.Utils
import kotlinx.coroutines.*

class ChatFragment: Fragment() {

    private lateinit var binding: FragmentChatBinding
    private var isLoading = false
    private var isBound = false
    private lateinit var scope: CoroutineScope
    var channel: Channel? = null
    var chatService: ChatService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scope = CoroutineScope(Dispatchers.Main)
        println("on create")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("on create view")
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSendMessageButton()
        setupAttachImageButton()
        setupToLastMessagesButton()
        setupKeyboardEvents()
        setupCloseChatButton()
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(activity, ChatService::class.java)
        activity?.startService(intent)
        activity?.bindService(intent, boundServiceConnection, AppCompatActivity.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        if (isBound) {
            activity?.unbindService(boundServiceConnection)
        }
        super.onStop()
    }

    fun showMessages() {
        binding.messagesView.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = ChatMessageAdapter(chatService!!.channelToMessages[channel!!]!!,
                onClick = {
                    val intent = Intent(activity, PictureActivity::class.java).apply {
                        putExtra(Utils.LINK, it.data.image!!.link)
                    }
                    startActivity(intent)
            })
        }
    }

    private fun setProgressBarsVisible() {
        binding.mainProgressBar.visibility = View.VISIBLE
        binding.toLastMessagesButton.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        binding.messagesView.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = ChatMessageAdapter(chatService!!.channelToMessages[channel]!!,
                onClick = {
                    val intent = Intent(activity, PictureActivity::class.java)
                    intent.putExtra(Utils.LINK, it.data.image!!.link)
                    startActivity(intent)
                }
            )
        }

        binding.messagesView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!isLoading) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        isLoading = true
                        binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
                        scope.launch {
                            val result = async {
                                val messages = chatService!!.requestMessages(ChatService.RequestMessagesType.NEW_MESSAGES, channel!!)
                                return@async messages
                            }
                            result.await().apply {
                                if (this.result == Result.SUCCESS) {
                                    getAdapter().notifyItemRangeInserted(this.first!! , this.second!!)
                                    if (this.second == 0) {
                                        showToast(getString(R.string.no_new_messages))
                                    }
                                } else {
                                    showToast(this.text!!)
                                }
                            }
                            isLoading = false
                        }
                    } else if (!recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        isLoading = true
                        scope.launch {
                            val result = async {
                                val messages = chatService!!.requestMessages(ChatService.RequestMessagesType.OLD_MESSAGES, channel!!)
                                return@async messages
                            }
                            result.await().apply {
                                if (this.result == Result.SUCCESS) {
                                    getAdapter().notifyItemRangeInserted(this.first!! , this.second!!)
                                    if (this.second == 0) {
                                        showToast(getString(R.string.no_new_messages))
                                    }
                                } else {
                                    showToast(this.text!!)
                                }
                            }
                            isLoading = false
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    binding.toLastMessagesButton.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun setupSendMessageButton() {
        binding.sendMessageButton.setOnClickListener {
            val text = binding.inputMessageField
                .text
                .toString()
                .trim()
            if (text.isNotEmpty()) {
                scope.launch {
                    val sendMessageJob = launch {
                        chatService!!.sendTextMessage(text, channel!!)
                    }
                    binding.inputMessageField.text.clear()
                    hideKeyboard()

                    sendMessageJob.join()
                    getAdapter().notifyItemInserted(getAdapter().itemCount - 1)
                    binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
                }

            }
        }
    }

    private fun setupToLastMessagesButton() {
        binding.toLastMessagesButton.setOnClickListener {
            binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
            binding.toLastMessagesButton.visibility = View.INVISIBLE
        }
    }

    private fun setupAttachImageButton() {
        binding.attachImageButton.setOnClickListener {
            if ((activity as ChatActivity).permissionIsGranted(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                pickImage.launch(intent)
            } else ((activity as ChatActivity).requestPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            if (data != null) {
                val contentUri = data.data
                scope.launch {
                    val result = async {
                        chatService!!.sendImageMessage(contentUri!!, channel!!)
                    }
                    if (result.await().result == Result.SUCCESS) {
                        getAdapter().notifyItemInserted(getAdapter().itemCount)
                        binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
                    } else {
                        showToast(result.await().text!!)
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupKeyboardEvents() {
        binding.messagesView.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }

    private fun setupCloseChatButton() {
        binding.closeChatButton?.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus?.let { v ->
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                    InputMethodManager).hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun getAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return binding.messagesView.adapter!!
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(binding.root.context,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binderBridge: ChatService.ChatBinder = service as ChatService.ChatBinder
            chatService = binderBridge.getChatService()
            isBound = true

            scope.launch {
                if (channel != null) {
                    chatService!!.requestMessages(ChatService.RequestMessagesType.FIRST_MESSAGES, channel!!)
                    showMessages()
                    setupRecyclerView()
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            chatService = null
        }
    }

    fun changeChat(toChannel: Channel) {
        channel = toChannel
        binding.nameOfChannel.text = channel!!.name

        scope.launch {
            chatService!!.requestMessages(ChatService.RequestMessagesType.FIRST_MESSAGES, channel!!)
            showMessages()
            setupRecyclerView()
        }
    }
}