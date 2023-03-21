package com.andrewdanilin.homework8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.andrewdanilin.homework8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainHandler = Handler(Looper.getMainLooper())
    private val timer = Timer(2000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        mainHandler.post(timer)
    }

    override fun onStop() {
        super.onStop()
        mainHandler.removeCallbacks(timer)
    }

    private fun animateSecondIndicator() {
        binding.secondIndicator.animate()
            .rotationBy(360f)
            .setDuration(500)
    }

    inner class Timer(private val delay: Long): Runnable {
        override fun run() {
            animateSecondIndicator()
            mainHandler.postDelayed(this, delay)
        }
    }
}