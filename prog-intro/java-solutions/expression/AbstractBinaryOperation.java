package expression;

public abstract class AbstractBinaryOperation extends AbstractOperation {
    public AbstractBinaryOperation(ExpressionInterface first, ExpressionInterface second, char signOfOperation) {
        super(first, second, signOfOperation);
    }
}
