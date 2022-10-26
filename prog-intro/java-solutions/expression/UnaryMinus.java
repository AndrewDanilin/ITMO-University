package expression;

public class UnaryMinus extends AbstractUnaryOperation {

    public UnaryMinus(ExpressionInterface operand) {
        super(operand, "-");
    }

    @Override
    public int apply(int x) {
        return x * -1;
    }

}
