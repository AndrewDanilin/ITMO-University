package com.andrewdanilin.homework1.expression;

import java.math.BigDecimal;

public abstract class AbstractOperation implements Expression {
    protected final Expression first;
    protected final Expression second;
    private final char signOfOperation;

    public AbstractOperation(Expression first, Expression second, char signOfOperation) {
        this.first = first;
        this.second = second;
        this.signOfOperation = signOfOperation;
    }

    public abstract BigDecimal evaluate();

    public String toString() {
        return "(" + first.toString() + " " + signOfOperation + " " + second.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            AbstractOperation that = (AbstractOperation) obj;
            return this.first.equals(that.first) && this.second.equals(that.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((29 * this.first.hashCode() + this.second.hashCode()) * 29 + getClass().hashCode()) * 29;
    }
}
