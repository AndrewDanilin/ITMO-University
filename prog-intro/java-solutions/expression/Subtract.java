package expression;

public class Subtract extends AbstractOperation {

    public Subtract(ExpressionInterface first, ExpressionInterface second) {
        super(first, second, '-');
    }

    @Override
    public int apply(int a, int b) {
        return a - b;
    }

}

