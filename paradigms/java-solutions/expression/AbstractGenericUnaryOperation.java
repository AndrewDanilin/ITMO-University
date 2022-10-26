package expression;

import expression.modes.UnaryMode;

public abstract class AbstractGenericUnaryOperation<T extends Number> implements GenericExpressionInterface<T> {
    protected final GenericExpressionInterface<T> operand;
    protected final UnaryMode<T> mode;

    public AbstractGenericUnaryOperation(GenericExpressionInterface<T> operand, UnaryMode<T> mode) {
        this.operand = operand;
        this.mode = mode;
    }

    public T evaluate(T x) {
        return apply(operand.evaluate(x));
    }

    public T evaluate(T x, T y, T z) {
        return apply(operand.evaluate(x, y, z));
    }

    protected abstract T apply(T x);

}
