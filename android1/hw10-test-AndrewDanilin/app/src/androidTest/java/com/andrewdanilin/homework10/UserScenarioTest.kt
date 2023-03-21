package com.andrewdanilin.homework10

import android.content.ClipboardManager
import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.andrewdanilin.homework10.utils.Utils
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UserScenarioTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun inputViewTextNotLost() {
        Utils.enterNumber("12345")
        Utils.performClickWithId(R.id.buttonEqual)

        device.setOrientationLeft()

        Utils.checkInputViewText("12345")
        Utils.checkResultViewText("12345")

        Utils.performClickWithId(R.id.buttonC)

        Utils.enterNumber("54321")

        device.setOrientationNatural()

        Utils.checkInputViewText("54321")
    }

    @Test
    fun copyToClipboard() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        Utils.enterNumber("12345")
        Utils.performClickWithId(R.id.buttonEqual)
        Utils.performClickWithId(R.id.buttonCopy)

        Assert.assertEquals("12345", clipboardManager.primaryClip?.getItemAt(0)?.text)

    }

}