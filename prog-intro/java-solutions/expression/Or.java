package expression;

public class Or extends AbstractBinaryOperation {
    public Or(ExpressionInterface first, ExpressionInterface second) {
        super(first, second ,'|');
    }

    @Override
    public int apply(int a, int b) {
        return a | b;
    }
}
