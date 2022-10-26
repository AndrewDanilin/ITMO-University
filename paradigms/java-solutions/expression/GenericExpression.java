package expression;


public interface GenericExpression<T extends Number> {
    T evaluate(T x);
}
