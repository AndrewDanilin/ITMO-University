package expression;

import expression.modes.BinaryMode;

public class GenericSubtract<T extends Number> extends AbstractGenericBinaryOperation<T> {

    public GenericSubtract(GenericExpressionInterface<T> first, GenericExpressionInterface<T> second, BinaryMode<T> mode) {
        super(first, second, mode);
    }

    protected T apply(T a, T b) {
        return mode.subtract(a, b);
    }
}
