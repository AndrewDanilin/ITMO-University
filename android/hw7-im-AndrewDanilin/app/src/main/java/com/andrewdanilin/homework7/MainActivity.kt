package com.andrewdanilin.homework7

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.andrewdanilin.homework7.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}