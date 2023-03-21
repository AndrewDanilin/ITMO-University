package com.andrewdanilin.homework1

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andrewdanilin.homework1.expression.exceptions.DivisionByZeroException
import com.andrewdanilin.homework1.expression.parser.ExpressionParser
import com.andrewdanilin.homework1.expression.parser.ParseException

class MainActivity: AppCompatActivity() {

    private val SAVED_RESULT = "SAVED_RESULT"
    private val INPUT_EXPRESSION = "INPUT_EXPRESSION"
    private val EVALUATION_RESULT = "EVALUATION_RESULT"

    private lateinit var inputView: TextView
    private lateinit var resultView: TextView
    private lateinit var buttonC: Button
    private lateinit var buttonCE: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonEqual: Button
    private lateinit var buttonCopy: Button
    private lateinit var buttonPaste: Button
    private lateinit var numberAndOperationButtons: Array<Button>

    private var inputExpression = StringBuilder()
    private var evaluationResult = "0"
    private var alreadyDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        inputView = findViewById(R.id.inputView)
        resultView = findViewById(R.id.resultView)
        setupButtons();
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INPUT_EXPRESSION, inputExpression.toString())
        outState.putString(EVALUATION_RESULT, evaluationResult)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputExpression = StringBuilder(savedInstanceState.getString(INPUT_EXPRESSION)!!)
        evaluationResult = savedInstanceState.getString(EVALUATION_RESULT)!!
        inputView.text = inputExpression
        resultView.text = evaluationResult
    }

    private fun setupButtons() {
        buttonC = findViewById(R.id.buttonC)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonCE = findViewById(R.id.buttonCE)
        buttonEqual = findViewById(R.id.buttonEqual)
        buttonCopy = findViewById(R.id.buttonCopy)
        buttonPaste = findViewById(R.id.buttonPaste)

        val indexToButton = mapOf(
            0 to R.id.buttonZero,
            1 to R.id.buttonOne,
            2 to R.id.buttonTwo,
            3 to R.id.buttonThree,
            4 to R.id.buttonFour,
            5 to R.id.buttonFive,
            6 to R.id.buttonSix,
            7 to R.id.buttonSeven,
            8 to R.id.buttonEight,
            9 to R.id.buttonNine,
            10 to R.id.buttonDot,
            11 to R.id.buttonPlus,
            12 to R.id.buttonMinus,
            13 to R.id.buttonMultiply,
            14 to R.id.buttonDivide
        )

        numberAndOperationButtons = Array(15) {i -> findViewById(indexToButton[i]!!)}
        IntRange(0, 14).forEach {
            i -> numberAndOperationButtons[i].setOnClickListener {
                when (i) {
                    in 0 .. 9 -> inputExpression.append(i.toString())
                    10 -> {
                        if (!alreadyDouble) {
                            alreadyDouble = true
                            inputExpression.append(getString(R.string.dot))
                        }
                    } else  -> {
                        alreadyDouble = false
                        when (i) {
                            11 -> inputExpression.append(getString(R.string.plus))
                            12 -> inputExpression.append(getString(R.string.minus))
                            13 -> inputExpression.append(getString(R.string.multiply))
                            14 -> inputExpression.append(getString(R.string.divide))
                        }
                    }
                }
                inputView.text = inputExpression
            }
        }


        buttonEqual.setOnClickListener {
            try {
                evaluationResult = ExpressionParser()
                    .parse(inputExpression.toString())
                    .evaluate()
                    .stripTrailingZeros()
                    .toPlainString()
                resultView.text = evaluationResult
            } catch (e: DivisionByZeroException) {
                resultView.text = getString(R.string.division_by_zero)
            } catch (e: ParseException) {
                resultView.text = getString(R.string.incorrect_input)
            }
        }

        buttonC.setOnClickListener {
            inputExpression.clear()
            evaluationResult = ""
            alreadyDouble = false
            resultView.text = evaluationResult
            inputView.text = inputExpression
        }

        buttonDelete.setOnClickListener {
            if (inputExpression.isNotEmpty()) {
                val c = inputExpression.last()
                inputExpression = StringBuilder(inputExpression.dropLast(1))
                inputView.text = inputExpression
                checkIsDotAndChangeFlag(c)
            }
        }

        buttonCE.setOnClickListener {
            inputExpression = StringBuilder(inputExpression.dropLastWhile { c ->
                    Character.isDigit(c) || checkIsDotAndChangeFlag(c)
                })
            inputView.text = inputExpression
        }

        val clipboardManager: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        buttonCopy.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(SAVED_RESULT, evaluationResult))
            resultView.text = getString(R.string.result_was_saved)
        }

        buttonPaste.setOnClickListener {
            val clipData = clipboardManager.primaryClip
            if (clipData != null) {
                val savedRes = clipData.getItemAt(0).text.toString()
                inputExpression.append(savedRes)
                inputView.text = inputExpression
                if (savedRes.contains('.')) {
                    alreadyDouble = true
                }
            } else {
                resultView.text = getString(R.string.nothing_to_paste)
            }
        }
    }

    private fun checkIsDotAndChangeFlag(c: Char): Boolean {
        if (c == '.') {
            alreadyDouble = false
            return true
        }
        return false
    }

}