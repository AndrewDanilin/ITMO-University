package expression;

import expression.parser.ParseException;

public class Xor extends AbstractBinaryOperation {
    public Xor(ExpressionInterface first, ExpressionInterface second) {
        super(first, second ,'^');
    }

    @Override
    public int apply(int a, int b) {
        return a ^ b;
    }

}
