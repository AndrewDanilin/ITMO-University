package expression.modes;

public interface UnaryMode<T extends Number> {
    T negate(T a);
}
