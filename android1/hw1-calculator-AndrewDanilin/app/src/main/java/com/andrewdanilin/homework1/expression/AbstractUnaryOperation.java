package com.andrewdanilin.homework1.expression;

import java.math.BigDecimal;

public abstract class AbstractUnaryOperation implements Expression {
    protected final Expression operand;
    private final String signOfOperation;

    public AbstractUnaryOperation (Expression operand, String signOfOperation) {
        this.operand = operand;
        this.signOfOperation = signOfOperation;
    }

    public String toString() {
        return "(" + signOfOperation + operand.toString() + ")";
    }

    public abstract BigDecimal evaluate();

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            AbstractUnaryOperation that = (AbstractUnaryOperation) obj;
            return this.operand.equals(that.operand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.operand.hashCode() * 29;
    }

}
