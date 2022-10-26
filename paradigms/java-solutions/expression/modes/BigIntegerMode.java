package expression.modes;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerMode implements Mode<BigInteger> {

    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    public BigInteger divide(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("Division by zero " + a.toString() + " / " + b.toString());
        }
        return a.divide(b);
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    public BigInteger getValue(String s) {
        return new BigInteger(s);
    }

    public BigInteger negate(BigInteger a) {
        return a.negate();
    }
}
