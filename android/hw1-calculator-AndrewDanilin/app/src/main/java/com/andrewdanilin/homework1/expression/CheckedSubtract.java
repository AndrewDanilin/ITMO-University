package com.andrewdanilin.homework1.expression;

import java.math.BigDecimal;

public class CheckedSubtract extends AbstractOperation {

    public CheckedSubtract(Expression first, Expression second) {
        super(first, second, '-');
    }

    @Override
    public BigDecimal evaluate() {
        return first.evaluate().subtract(second.evaluate());
    }

}

