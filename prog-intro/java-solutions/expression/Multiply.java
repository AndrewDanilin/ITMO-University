package expression;

public class Multiply extends AbstractOperation {

    public Multiply(ExpressionInterface first, ExpressionInterface second) {
        super(first, second, '*');
    }

    @Override
    public int apply(int a, int b) {
        return a * b;
    }

}
