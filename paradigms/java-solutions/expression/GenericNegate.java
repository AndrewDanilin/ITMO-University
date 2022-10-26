package expression;

import expression.modes.UnaryMode;

public class GenericNegate<T extends Number> extends AbstractGenericUnaryOperation<T> {

    public GenericNegate(GenericExpressionInterface<T> operand, UnaryMode<T> mode) {
        super(operand, mode);
    }

    protected T apply(T x) {
        return mode.negate(x);
    }
}
