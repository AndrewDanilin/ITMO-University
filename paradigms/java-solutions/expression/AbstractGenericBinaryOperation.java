package expression;

import expression.modes.BinaryMode;

public abstract class AbstractGenericBinaryOperation<T extends Number> implements GenericExpressionInterface<T> {
    protected final GenericExpressionInterface<T> first;
    protected final GenericExpressionInterface<T> second;
    protected final BinaryMode<T> mode;

    public AbstractGenericBinaryOperation(GenericExpressionInterface<T> first, GenericExpressionInterface<T> second, BinaryMode<T> mode) {
        this.first = first;
        this.second = second;
        this.mode = mode;
    }

    public T evaluate(T x) {
        return apply(first.evaluate(x), second.evaluate(x));
    }

    public T evaluate(T x, T y, T z) {
        return apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    protected abstract T apply(T a, T b);

}
