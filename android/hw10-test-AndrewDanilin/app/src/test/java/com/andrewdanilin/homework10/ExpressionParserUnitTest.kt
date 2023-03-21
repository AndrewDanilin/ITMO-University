package com.andrewdanilin.homework10

import com.andrewdanilin.homework10.expression.exceptions.DivisionByZeroException
import com.andrewdanilin.homework10.expression.exceptions.MissingParenthesisException
import com.andrewdanilin.homework10.expression.parser.ExpressionParser
import org.junit.Test

import org.junit.Assert.*

class ExpressionParserUnitTest {

    companion object {
        private const val delta = 0.000_000_1
    }

    private val numbers: Map<Double, Double> = mapOf(
        3294.293 to 1928.330,
        2.5 to 2.5,
        10.0 to 0.39232,
        2.8374 to 1.3729,
        1000.0 to 2000.0
    )

    private val parser = ExpressionParser()


    @Test
    fun add() {
        numbers.entries.forEach { entry ->
            testBinaryOperation(entry.key, entry.value, '+') {
                    a, b -> a + b
            }
        }
    }

    @Test
    fun subtract() {
        numbers.entries.forEach { entry ->
            testBinaryOperation(entry.key, entry.value, '-') {
                    a, b -> a - b
            }
        }
    }

    @Test
    fun multiply() {
        numbers.entries.forEach { entry ->
            testBinaryOperation(entry.key, entry.value, '*') {
                    a, b -> a * b
            }
        }
    }

    @Test
    fun divide() {
        numbers.entries.forEach { entry ->
            testBinaryOperation(entry.key, entry.value, '/') {
                a, b -> a / b
            }
        }
    }

    @Test
    fun parseWhitespacesCorrectly() {
        val expr = "     2        "
        assertEquals(2, parser.parse(expr).evaluate().toInt())
    }

    @Test
    fun parseParenthesisCorrectly() {
        val expr = "(2 +    2)"
        assertEquals(4, parser.parse(expr).evaluate().toInt())
    }

    @Test(expected = MissingParenthesisException::class)
    fun missingParenthesis() {
        val expr = "(  2 + 2"
        parser.parse(expr)
    }

    @Test(expected = DivisionByZeroException::class)
    fun divideByZero() {
        val expr = "1 / 0"
        parser.parse(expr).evaluate()
    }


    private fun testBinaryOperation(
        a: Double,
        b: Double,
        signOfOperation: Char,
        f : (Double, Double) -> Double
    ) {
        val expr = "$a$signOfOperation$b"
        val expected = f(a, b)
        assertEquals(expected, parser.parse(expr).evaluate().toDouble(), delta)
    }
}