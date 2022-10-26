package expression;

public class Divide extends AbstractOperation {

    public Divide(ExpressionInterface first, ExpressionInterface second) {
        super(first, second ,'/');
    }

    @Override
    public int apply(int a, int b) {
        return a / b;
    }
}
