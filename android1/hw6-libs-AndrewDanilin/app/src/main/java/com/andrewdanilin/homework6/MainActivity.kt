package com.andrewdanilin.homework6

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework6.chatmessage.ChatMessage
import com.andrewdanilin.homework6.chatmessage.ChatMessageAdapter
import com.andrewdanilin.homework6.databinding.MainActivityBinding
import com.andrewdanilin.homework6.extensions.permissionIsGranted
import com.andrewdanilin.homework6.extensions.requestPermission
import com.andrewdanilin.homework6.services.ChatService
import com.andrewdanilin.homework6.services.Result
import com.andrewdanilin.homework6.utils.Utils
import kotlinx.coroutines.*
import java.util.*


class MainActivity: AppCompatActivity() {

    companion object {
        const val INPUT_FIELD_TEXT = "INPUT_FIELD_TEXT"
    }

    private lateinit var binding: MainActivityBinding
    private var isBound = false
    private var isLoading = false

    private var chatService: ChatService? = null
    private lateinit var scope: CoroutineScope

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            if (data != null) {
                val contentUri = data.data
                scope.launch {
                    val result = getService().sendImageMessage(contentUri!!)
                    if (result.result == Result.SUCCESS) {
                        getAdapter().notifyItemInserted(getAdapter().itemCount)
                        binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
                    } else {
                        showToast(result.text!!)
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

            scope.launch {
                withContext(Dispatchers.IO) {
                    getService().getAllMessagesFromDB()
                }

                val result = getService().requestMessages(ChatService.RequestMessagesType.FIRST_MESSAGES)

                binding.mainProgressBar.visibility = View.INVISIBLE
                result.apply {

                    if (this.result == Result.SUCCESS) {
                        binding.messagesView.adapter = ChatMessageAdapter(getChatMessages(),
                            onClick = {
                                if (it.data.image != null) {
                                    val intentToOpenPicture = Intent(this@MainActivity, PictureActivity::class.java)
                                        .putExtra(Utils.LINK, it.data.image?.link)
                                    startActivity(intentToOpenPicture)
                                }
                            }
                        )

                        getAdapter().notifyItemRangeInserted(this.first!!, this.second!!)
                        if (getService().scrollViewState != null) {
                            binding.messagesView.layoutManager!!.onRestoreInstanceState(getService().scrollViewState)
                        } else {
                            binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
                        }
                    } else {
                        showToast(this.text!!)
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
        setupRecyclerView()
        setProgressBarsVisible()
        setupSendMessageButton()
        setupAttachImageButton()
        setupToLastMessagesButton()
        setupKeyboardEvents()
    }

    private fun setProgressBarsVisible() {
        binding.mainProgressBar.visibility = View.VISIBLE
        binding.toLastMessagesButton.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        scope = CoroutineScope(Dispatchers.Main)
        val intent = Intent(this@MainActivity, ChatService::class.java)
        startService(intent)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        if (isBound) {
            unbindService(boundServiceConnection)
        }
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        getService().scrollViewState = binding.messagesView.layoutManager!!.onSaveInstanceState()
        scope.launch {
            withContext(Dispatchers.IO) {
                getService().saveAllMessagesToDB()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
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
            adapter = ChatMessageAdapter(
                mutableListOf(),
                onClick = { return@ChatMessageAdapter }
            )
        }

        binding.messagesView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!isLoading) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        isLoading = true
                        getService().addNullData(false)
                        getAdapter().notifyItemInserted(getAdapter().itemCount - 1)
                        binding.messagesView.scrollToPosition(getAdapter().itemCount - 1)
                        scope.launch {
                            val result = getService().requestMessages(ChatService.RequestMessagesType.NEW_MESSAGES)
                            getAdapter().notifyItemRemoved(getAdapter().itemCount)

                            result.apply {
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
                        getService().addNullData(true)
                        getAdapter().notifyItemInserted(0)
                        scope.launch {
                            val result = getService().requestMessages(ChatService.RequestMessagesType.OLD_MESSAGES)
                            getAdapter().notifyItemRemoved(0)

                            result.apply {
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
                        getService().sendTextMessage(text)
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

    private fun getChatMessages(): MutableList<ChatMessage?> {
        return getService().chatMessages
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