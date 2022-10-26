package expression;

import expression.parser.ParseException;

public class And extends AbstractBinaryOperation {
    public And(ExpressionInterface first, ExpressionInterface second) {
        super(first, second ,'&');
    }

    @Override
    public int apply(int a, int b) {
        return a & b;
    }

}
