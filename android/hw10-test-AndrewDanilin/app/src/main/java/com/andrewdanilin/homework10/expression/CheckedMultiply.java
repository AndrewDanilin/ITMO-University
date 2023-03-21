package com.andrewdanilin.homework10.expression;

import java.math.BigDecimal;

public class CheckedMultiply extends AbstractOperation {

    public CheckedMultiply(Expression first, Expression second) {
        super(first, second, '*');
    }

    @Override
    public BigDecimal evaluate() {
        return first.evaluate().multiply(second.evaluate());
    }

}
