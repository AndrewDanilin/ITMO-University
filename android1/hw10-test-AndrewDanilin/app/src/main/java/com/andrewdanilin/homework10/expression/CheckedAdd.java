package com.andrewdanilin.homework10.expression;

import java.math.BigDecimal;


public class CheckedAdd extends AbstractOperation {

    public CheckedAdd(Expression first, Expression second) {
        super(first, second ,'+');
    }

    @Override
    public BigDecimal evaluate() {
        return first.evaluate().add(second.evaluate());
    }
}
