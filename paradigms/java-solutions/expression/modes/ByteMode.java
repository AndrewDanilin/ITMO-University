package expression.modes;

import expression.exceptions.DivisionByZeroException;

public class ByteMode implements Mode<Byte> {

    public Byte getValue(String s) {
        return (byte) Integer.parseInt(s);
    }

    public Byte add(Byte a, Byte b) {
        return (byte) (a + b);
    }

    public Byte multiply(Byte a, Byte b) {
        return (byte)(a * b);
    }

    public Byte subtract(Byte a, Byte b) {
        return (byte)(a - b);
    }

    public Byte divide(Byte a, Byte b) {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + " / " + b);
        }
        return (byte)(a / b);
    }

    public Byte negate(Byte a) {
        return (byte) (-a);
    }

}
