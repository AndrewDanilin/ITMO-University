package com.andrewdanilin.homework10

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.andrewdanilin.homework10.utils.Utils
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ButtonsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickZero() {
        Utils.enterNumber("0")
        Utils.checkInputViewText("0")
    }

    @Test
    fun clickOne() {
        Utils.enterNumber("1")
        Utils.checkInputViewText("1")
    }

    @Test
    fun clickTwo() {
        Utils.enterNumber("2")
        Utils.checkInputViewText("2")
    }

    @Test
    fun clickThree() {
        Utils.enterNumber("3")
        Utils.checkInputViewText("3")
    }

    @Test
    fun clickFour() {
        Utils.enterNumber("4")
        Utils.checkInputViewText("4")
    }

    @Test
    fun clickFive() {
        Utils.enterNumber("5")
        Utils.checkInputViewText("5")
    }

    @Test
    fun clickSix() {
        Utils. enterNumber("6")
        Utils.checkInputViewText("6")
    }

    @Test
    fun clickSeven() {
        Utils.enterNumber("7")
        Utils.checkInputViewText("7")
    }

    @Test
    fun clickEight() {
        Utils.enterNumber("8")
        Utils.checkInputViewText("8")
    }

    @Test
    fun clickNine() {
        Utils.enterNumber("9")
        Utils.checkInputViewText("9")
    }

    @Test
    fun clickPlus() {
        Utils.performClickWithId(R.id.buttonPlus)
        Utils.checkInputViewText("+")
    }

    @Test
    fun clickMinus() {
        Utils.performClickWithId(R.id.buttonMinus)
        Utils.checkInputViewText("-")
    }

    @Test
    fun clickDivide() {
        Utils.performClickWithId(R.id.buttonDivide)
        Utils.checkInputViewText("/")
    }

    @Test
    fun clickMultiply() {
        Utils.performClickWithId(R.id.buttonMultiply)
        Utils.checkInputViewText("*")
    }

    @Test
    fun clickEqual() {
        Utils.enterNumber("2")
        Utils.performClickWithId(R.id.buttonPlus)
        Utils.enterNumber("2")
        Utils.performClickWithId(R.id.buttonEqual)
        Utils.checkResultViewText("4")
    }

    @Test
    fun clickC() {
        Utils.enterNumber("12345")
        Utils.performClickWithId(R.id.buttonC)
        Utils.checkInputViewText("")
    }

    @Test
    fun clickCEInteger() {
        Utils.enterNumber("22")
        Utils.performClickWithId(R.id.buttonPlus)
        Utils.enterNumber("22")
        Utils.performClickWithId(R.id.buttonCE)
        Utils.checkInputViewText("22+")
    }

    @Test
    fun clickCEDouble() {
        Utils.enterNumber("22.22")
        Utils.performClickWithId(R.id.buttonPlus)
        Utils.enterNumber("22.22")
        Utils.performClickWithId(R.id.buttonCE)
        Utils.checkInputViewText("22.22+")
    }

    @Test
    fun clickDelete() {
        Utils.enterNumber("111")
        Utils.performClickWithId(R.id.buttonDelete)
        Utils.checkInputViewText("11")
    }

    @Test
    fun clickDot() {
        Utils.performClickWithId(R.id.buttonDot)
        Utils.checkInputViewText(".")
    }

    @Test
    fun clickCopyAndPaste() {
        Utils.enterNumber("111")
        Utils.performClickWithId(R.id.buttonEqual)
        Utils.performClickWithId(R.id.buttonCopy)
        Utils.performClickWithId(R.id.buttonC)
        Utils.performClickWithId(R.id.buttonPaste)
        Utils.checkInputViewText("111")
    }
}