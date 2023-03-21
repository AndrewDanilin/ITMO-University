package com.andrewdanilin.homework1.expression;

import java.math.BigDecimal;
import java.math.MathContext;

import com.andrewdanilin.homework1.expression.exceptions.DivisionByZeroException;

public class CheckedDivide extends AbstractOperation {

    public CheckedDivide(Expression first, Expression second) {
        super(first, second ,'/');
    }

    @Override
    public BigDecimal evaluate() {
        BigDecimal a = first.evaluate();
        BigDecimal b = second.evaluate();
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + " / " + b);
        }
        return a.divide(b, MathContext.DECIMAL64);
    }
}
