package expression;

public abstract class AbstractUnaryOperation implements ExpressionInterface {
    protected final ExpressionInterface operand;
    private final String signOfOperation;

    public AbstractUnaryOperation (ExpressionInterface operand, String signOfOperation) {
        this.operand = operand;
        this.signOfOperation = signOfOperation;
    }

    public String toString() {
        return "(" + signOfOperation + operand.toString() + ")";
    }

    public int evaluate(int x) {
        return apply(operand.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return apply(operand.evaluate(x, y, z));
    }

    public abstract int apply(int x);

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            AbstractUnaryOperation that = (AbstractUnaryOperation) obj;
            return this.operand.equals(that.operand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.operand.hashCode() * 29;
    }

}
