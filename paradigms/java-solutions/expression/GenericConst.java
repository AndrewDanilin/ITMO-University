package expression;

public class GenericConst<T extends Number> implements GenericExpressionInterface<T> {
    private T value;

    public GenericConst(T value) {
        this.value = value;
    }

    public T evaluate(T x) {
        return value;
    }

    public T evaluate(T x, T y, T z) {
        return value;
    }

}
