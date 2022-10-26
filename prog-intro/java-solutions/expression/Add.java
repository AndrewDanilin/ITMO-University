package expression;

public class Add extends AbstractOperation {

    public Add(ExpressionInterface first, ExpressionInterface second) {
        super(first, second ,'+');
    }


    @Override
    public int apply(int a, int b) {
        return  a + b;
    }
}
