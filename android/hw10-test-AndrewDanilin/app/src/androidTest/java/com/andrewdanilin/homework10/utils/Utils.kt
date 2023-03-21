package com.andrewdanilin.homework10.utils

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.andrewdanilin.homework10.R

class Utils {
    companion object {
        val charToId: Map<Char, Int> = mapOf(
            '0' to R.id.buttonZero,
            '1' to R.id.buttonOne,
            '2' to R.id.buttonTwo,
            '3' to R.id.buttonThree,
            '4' to R.id.buttonFour,
            '5' to R.id.buttonFive,
            '6' to R.id.buttonSix,
            '7' to R.id.buttonSeven,
            '8' to R.id.buttonEight,
            '9' to R.id.buttonNine,
            '.' to R.id.buttonDot
        )

        fun performClickNTimes(id: Int, n: Int) {
            for (i in 0 until n) {
                performClickWithId(id)
            }
        }

        fun performClickWithId(id: Int) {
            Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
        }

        fun checkInputViewText(text: String) {
            Espresso.onView(ViewMatchers.withId(R.id.inputView))
                .check(ViewAssertions.matches(ViewMatchers.withText(text)))
        }

        fun enterNumber(number: String) {
            for (i in number) {
                performClickWithId(charToId[i]!!)
            }
        }

        fun checkResultViewText(text: String) {
            Espresso.onView(ViewMatchers.withId(R.id.resultView))
                .check(ViewAssertions.matches(ViewMatchers.withText(text)))
        }
    }
}