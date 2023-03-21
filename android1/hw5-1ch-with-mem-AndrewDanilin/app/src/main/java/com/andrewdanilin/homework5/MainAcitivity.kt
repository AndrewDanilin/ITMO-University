package com.andrewdanilin.homework5

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification.Action
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework5.chatmessage.ChatMessage
import com.andrewdanilin.homework5.chatmessage.ChatMessageAdapter
import com.andrewdanilin.homework5.databinding.MainActivityBinding
import com.andrewdanilin.homework5.extensions.permissionIsGranted
import com.andrewdanilin.homework5.extensions.requestPermission
import com.andrewdanilin.homework5.services.ChatService
import com.andrewdanilin.homework5.utils.Utils
import java.io.File
import java.util.jar.Manifest


class MainActivity: AppCompatActivity() {

    companion object {
        const val INPUT_FIELD_TEXT = "INPUT_FIELD_TEXT"
    }

    private lateinit var binding: MainActivityBinding
    private var chatService: ChatService? = null
    private var isBound = false
    private var isLoading = false
    private val mainHandler = Handler(Looper.getMainLooper())

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            if (data != null) {
                val contentUri = data.data
                getServiceHandler().post {
                    getService().sendImageMessage(contentUri!!)
                    mainHandler.post {
                        mainHandler.postDelayed({ goToLastMessages() }, 500)
                    }
                }
            }
        }
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binderBridge: ChatService.ChatBinder = service as ChatService.ChatBinder
            chatService = binderBridge.getChatService()
            isBound = true
            getServiceHandler().post {
                val result = getService().requestFirstMessages()
                mainHandler.post {
                    setupRecyclerView()
                    if (getService().recyclerViewState != null) {
                        binding.messagesView.layoutManager?.onRestoreInstanceState(getService().recyclerViewState)
                    }
                    binding.mainProgressBar.visibility = View.INVISIBLE
                    if (result.first == -1) {
                        showToast(getString(R.string.failed_messages))
                        binding.toLastMessagesButton.visibility = View.INVISIBLE
                    } else {
                        getAdapter().notifyItemRangeInserted(result.first, result.second)
                    }
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
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSendMessageButton()
        setupToLastMessagesButton()
        setupAttachImageButton()
        setupKeyboardEvents()
        binding.mainProgressBar.visibility = View.VISIBLE
        binding.toLastMessagesButton.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this@MainActivity, ChatService::class.java)
        startService(intent)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        getService().recyclerViewState = binding.messagesView.layoutManager?.onSaveInstanceState()
        if (isBound) {
            unbindService(boundServiceConnection)
        }
        super.onStop()
    }

    override fun onPause() {
        getServiceHandler().post { getService().saveAllData() }
        super.onPause()
    }


    override fun onDestroy() {
        super.onDestroy()
        getServiceHandler().removeCallbacksAndMessages(null)
        val intent = Intent(this@MainActivity, ChatService::class.java)
        stopService(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INPUT_FIELD_TEXT, binding.inputMessageField.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.inputMessageField.setText(
            savedInstanceState.getString(INPUT_FIELD_TEXT).orEmpty()
        )
    }

    private fun setupRecyclerView() {
        binding.messagesView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ChatMessageAdapter(getChatMessages(),
                onClick = {
                    if (it.link != null) {
                        val intentToOpenPicture = Intent(this@MainActivity, PictureActivity::class.java)
                            .putExtra(Utils.LINK, it.link)
                        startActivity(intentToOpenPicture)
                    }
                },
                downloadImage = { chatMessage, imageView ->
                    getServiceHandler().removeCallbacksAndMessages(imageView)
                    getServiceHandler().postDelayed({
                        getService().downloadPicture(chatMessage.link!!, false)
                        mainHandler.post {
                            imageView.setImageBitmap(getService().getImage(chatMessage.link, false))
                        }
                    }, imageView, 0)
                }
            )
        }

        binding.messagesView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!isLoading) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val lastVisibleMessageId = getChatMessages().values.toList().last().id
                        isLoading = true
                        binding.recyclerViewProgressBar.visibility = View.VISIBLE
                        getServiceHandler().post {
                            val result = getService().requestMessagesFromId(idOfLastMessage = lastVisibleMessageId)
                            mainHandler.post {
                                if (result.second == 0) {
                                    showToast(getString(R.string.no_new_messages))
                                } else {
                                    getAdapter().notifyItemRangeInserted(
                                        result.first,
                                        result.second
                                    )
                                }
                                binding.recyclerViewProgressBar.visibility = View.GONE
                                isLoading = false
                            }
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
            val text = binding.inputMessageField.text.toString()
            binding.inputMessageField.text.clear()
            if (text.trim().isNotEmpty()) {
                getServiceHandler().post {
                    getService().sendTextMessage(text)
                    mainHandler.post {
                        hideKeyboard()
                        mainHandler.postDelayed({ goToLastMessages() }, 500)
                    }
                }

            }
        }
    }

    private fun setupToLastMessagesButton() {
        binding.toLastMessagesButton.setOnClickListener {
            goToLastMessages()
        }
    }

    private fun goToLastMessages() {
        isLoading = true
        binding.messagesView.visibility = View.INVISIBLE
        binding.mainProgressBar.visibility = View.VISIBLE
        getServiceHandler().post {
            val result = getService().getLastMessages()
            mainHandler.post {
                binding.toLastMessagesButton.visibility = View.GONE
                binding.mainProgressBar.visibility = View.GONE
                getAdapter().notifyItemRangeInserted(result.first, result.second)
                isLoading = false
                binding.messagesView.visibility = View.VISIBLE
                binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
            }
        }
    }

    private fun setupAttachImageButton() {
        binding.attachImageButton!!.setOnClickListener {
            if (permissionIsGranted(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                pickImage.launch(intent)
            } else (requestPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupKeyboardEvents() {
        binding.messagesView.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }

    private fun hideKeyboard() {
        this.currentFocus?.let { v ->
            (getSystemService(Context.INPUT_METHOD_SERVICE) as
                    InputMethodManager).hideSoftInputFromWindow(v.windowToken, 0)
        }
    }


    private fun getServiceHandler(): Handler {
        return getService().handlerList[0]!!
    }

    private fun getChatMessages(): HashMap<Int, ChatMessage> {
        return getService().idToChatMessage
    }

    private fun getService(): ChatService {
        return chatService!!
    }

    private fun getAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return binding.messagesView.adapter!!
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this@MainActivity,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}