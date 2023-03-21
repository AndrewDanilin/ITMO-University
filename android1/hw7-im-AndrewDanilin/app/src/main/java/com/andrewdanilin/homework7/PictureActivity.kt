package com.andrewdanilin.homework7

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.andrewdanilin.homework7.databinding.PictureActivityBinding
import com.andrewdanilin.homework7.services.ChatService
import com.andrewdanilin.homework7.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class PictureActivity: AppCompatActivity() {
    private lateinit var binding: PictureActivityBinding
    lateinit var linkToPicture: String
    lateinit var scope: CoroutineScope
    private var chatService: ChatService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PictureActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linkToPicture = intent.getStringExtra(Utils.LINK)!!

        Picasso.get()
            .load(Utils.IMG_URL + linkToPicture)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(binding.pictureView)

        scope = CoroutineScope(Dispatchers.Main)
        setupCloseButton()
        setupSaveButton()
    }


    private fun setupCloseButton() {
        binding.closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val bitmap = binding.pictureView.drawable.toBitmap()
                scope.launch {
                    val name = System.currentTimeMillis().toString() + ".png"
                    val values = ContentValues().apply {
                        put(MediaStore.Images.Media.DISPLAY_NAME, name)
                        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val contentUri = contentResolver.insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values
                    )
                    try {
                        withContext(Dispatchers.IO) {
                            contentResolver.openOutputStream(contentUri!!).use {
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                            }
                        }
                        showToast(getString(R.string.image_successfully_saved))
                    } catch (e: IOException) {
                        showToast(getString(R.string.failed_to_save_image))
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(applicationContext,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}