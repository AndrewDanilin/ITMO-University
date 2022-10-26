package expression.modes;

public interface Mode<T extends Number> extends BinaryMode<T>, UnaryMode<T> {
    T getValue(String s);
}
