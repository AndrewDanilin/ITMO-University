package com.andrewdanilin.homework2

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class MainActivity: AppCompatActivity() {

    private val PASSWORD_IS_HIDDEN = "PASSWORD_IS_HIDDEN"
    private val MESSAGE_TEXT_NUMBER = "MESSAGE_TEXT_NUMBER"
    private val MESSAGE_VIEW_VISIBILITY = "MESSAGE_VIEW_VISIBILITY"

    private lateinit var passwordShowButton: ImageButton
    private lateinit var logInButton: Button
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText
    private lateinit var messageView: TextView
    private lateinit var themeSwitch: SwitchCompat

    private var passwordIsHidden = true
    private var lastMessageInt = 0

    private lateinit var numberToMessageText: Map<Int, String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        numberToMessageText = mapOf(
            0 to getString(R.string.empty),
            1 to getString(R.string.message_incorrect_login_spaces),
            2 to getString(R.string.message_incorrect_login_empty),
            3 to getString(R.string.message_incorrect_password_empty),
            4 to getString(R.string.message_incorrect_login_or_password)
        )
        emailView = findViewById(R.id.emailView)
        passwordView = findViewById(R.id.passwordView)
        messageView = findViewById(R.id.messageView)
        setupPasswordShowButton()
        setupLogInButton()
        setupThemeSwitch()
        setThemeSwitchToCorrectState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(PASSWORD_IS_HIDDEN, passwordIsHidden)
        outState.putInt(MESSAGE_TEXT_NUMBER, lastMessageInt)
        outState.putInt(MESSAGE_VIEW_VISIBILITY, messageView.visibility)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val passwordIsHidden = savedInstanceState.getBoolean(PASSWORD_IS_HIDDEN)
        messageView.text = numberToMessageText[savedInstanceState.getInt(MESSAGE_TEXT_NUMBER)]
        messageView.visibility = savedInstanceState.getInt(MESSAGE_VIEW_VISIBILITY)
        when (passwordIsHidden) {
            true -> passwordSetInvisible()
            false -> passwordSetVisible()
        }
        this.passwordIsHidden = passwordIsHidden
        passwordView.setSelection(passwordView.length())
    }

    private fun setThemeSwitchToCorrectState() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            UI_MODE_NIGHT_YES -> themeSwitch.isChecked = true
            UI_MODE_NIGHT_NO -> themeSwitch.isChecked = false
        }
    }

    private fun setupPasswordShowButton() {
        passwordShowButton = findViewById(R.id.showPasswordButton)
        passwordShowButton.setOnClickListener {
            when (passwordIsHidden) {
                true -> passwordSetVisible()
                false -> passwordSetInvisible()
            }
            passwordView.setSelection(passwordView.length())
            passwordIsHidden = !passwordIsHidden
        }
    }

    private fun setupLogInButton() {
        logInButton = findViewById(R.id.logInButton)
        logInButton.setOnClickListener {
            val login = emailView.text.toString()
            val password = passwordView.text.toString()
            if (login.contains(" ")) {
                setMessageText(1)
            } else if (login.isEmpty()) {
                setMessageText(2)
            } else if (password.isEmpty()) {
                setMessageText(3)
            } else {
                setMessageText(4)
            }
            messageView.visibility = View.VISIBLE
        }
    }

    private fun setupThemeSwitch() {
        themeSwitch = findViewById(R.id.themeSwitch)
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast(getString(R.string.dark_mode_is_on))
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                showToast(getString(R.string.dark_mode_is_off))
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }


    private fun passwordSetVisible() {
        passwordShowButton.setImageResource(R.drawable.visible)
        passwordView.transformationMethod = null
    }

    private fun passwordSetInvisible() {
        passwordShowButton.setImageResource(R.drawable.invisible)
        passwordView.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private fun setMessageText(number: Int) {
        lastMessageInt = number
        messageView.text = numberToMessageText[number]
    }

}