package expression.modes;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerMode implements Mode<Integer> {
    private final boolean isCheckOverflow;

    public IntegerMode(boolean isCheckOverflow) {
        this.isCheckOverflow = isCheckOverflow;
    }

    public Integer add(Integer a, Integer b) {
        return isCheckOverflow ? checkAddOverflow(a, b) : a + b;
    }

    public Integer checkAddOverflow(Integer a, Integer b) {
        if ((a > 0 && b > Integer.MAX_VALUE - a) || (a < 0 && b < Integer.MIN_VALUE - a)) {
            throw new OverflowException("Overflow by addition: " + a + " + " + b);
        }
        return a + b;
    }

    public Integer subtract(Integer a, Integer b) {
        return isCheckOverflow ? checkSubtractOverflow(a, b) : a - b;
    }

    private Integer checkSubtractOverflow(Integer a, Integer b) {
        if ((b > 0 && a < Integer.MIN_VALUE + b) || (b < 0 && a > Integer.MAX_VALUE + b)) {
            throw new OverflowException("Overflow by subtracting: " + a + " - " + b);
        }
        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        return isCheckOverflow ? checkMultiplyOverflow(a, b) : a * b;
    }

    private Integer checkMultiplyOverflow(Integer a, Integer b) {
        Integer res = a * b;
        if ((a != 0 && res / a != b) ||
                (a == -1 && b == Integer.MIN_VALUE) ||
                (a == Integer.MIN_VALUE && b == -1)) {
            throw new OverflowException("Overflow by multiplying: " + a + " * " + b);
        }
        return res;
    }

    public Integer divide(Integer a, Integer b) {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + " / " + b);
        }
        return isCheckOverflow ? checkDivideOverflow(a, b) : a / b;
    }

    private Integer checkDivideOverflow(Integer a, Integer b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException("Overflow by dividing: " + a + " / " + b);
        }
        return a / b;
    }

    public Integer getValue(String s) {
        return Integer.parseInt(s);
    }

    public Integer negate(Integer a) {
        return -a;
    }
}
