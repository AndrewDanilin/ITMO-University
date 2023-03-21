package com.andrewdanilin.homework1.expression;

import java.math.BigDecimal;

public class CheckedNegate extends AbstractUnaryOperation {

    public CheckedNegate(Expression operand) {
        super(operand, "-");
    }

    @Override
    public BigDecimal evaluate() {
        return operand.evaluate().multiply(BigDecimal.valueOf(-1));
    }

}
