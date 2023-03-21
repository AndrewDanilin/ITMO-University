package com.andrewdanilin.homework1.expression;

import java.math.BigDecimal;

public class Const implements Expression {
    BigDecimal value;

    public Const(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Const.class) {
            Const that = (Const) obj;
            return this.value == that.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
