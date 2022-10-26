package expression;

public class GenericVariable<T extends Number> implements GenericExpressionInterface<T> {
    private final String name;

    public GenericVariable(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public T evaluate(T x) {
        return x;
    }

    public T evaluate(T x, T y, T z) {
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
