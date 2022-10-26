package expression.modes;

public class FloatMode implements Mode<Float> {

    public Float add(Float a, Float b) {
        return a + b;
    }

    public Float multiply(Float a, Float b) {
        return a * b;
    }

    public Float subtract(Float a, Float b) {
        return a - b;
    }

    public Float divide(Float a, Float b) {
        return a / b;
    }

    public Float getValue(String s) {
        return Float.parseFloat(s);
    }

    public Float negate(Float a) {
        return -a;
    }
}
