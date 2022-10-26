package expression;

import expression.modes.BinaryMode;

public class GenericDivide<T extends Number> extends AbstractGenericBinaryOperation<T> {

    public GenericDivide(GenericExpressionInterface<T> first, GenericExpressionInterface<T> second, BinaryMode<T> mode) {
        super(first, second, mode);
    }

    protected T apply(T a, T b) {
        return mode.divide(a, b);
    }
}
