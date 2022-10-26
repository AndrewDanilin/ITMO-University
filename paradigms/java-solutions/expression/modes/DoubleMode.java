package expression.modes;


public class DoubleMode implements Mode<Double> {

    public Double add(Double a, Double b) {
        return a + b;
    }

    public Double subtract(Double a, Double b) {
        return a - b;
    }

    public Double divide(Double a, Double b) {
        return a / b;
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }

    public Double getValue(String s) {
        return Double.parseDouble(s);
    }

    public Double negate(Double a) {
        return -a;
    }
}
