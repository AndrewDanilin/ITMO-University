package expression;

import expression.modes.BinaryMode;

public class GenericAdd<T extends Number> extends AbstractGenericBinaryOperation<T> {

    public GenericAdd(GenericExpressionInterface<T> first, GenericExpressionInterface<T> second, BinaryMode<T> mode) {
        super(first, second, mode);
    }

    protected T apply(T a, T b) {
        return mode.add(a, b);
    }
}
