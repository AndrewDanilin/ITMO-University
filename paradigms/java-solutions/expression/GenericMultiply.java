package expression;

import expression.modes.BinaryMode;


public class GenericMultiply<T extends Number> extends AbstractGenericBinaryOperation<T> {

    public GenericMultiply(GenericExpressionInterface<T> first, GenericExpressionInterface<T> second, BinaryMode<T> mode) {
        super(first, second, mode);
    }

    protected T apply(T a, T b) {
        return mode.multiply(a, b);
    }
}
