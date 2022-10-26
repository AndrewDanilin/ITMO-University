package expression;

public abstract class AbstractOperation implements ExpressionInterface {
    protected final ExpressionInterface first;
    protected final ExpressionInterface second;
    private final char signOfOperation;

    public AbstractOperation(ExpressionInterface first, ExpressionInterface second, char signOfOperation) {
        this.first = first;
        this.second = second;
        this.signOfOperation = signOfOperation;
    }

    public int evaluate(int x) {
        return apply(first.evaluate(x), second.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    public abstract int apply(int a, int b);

    public String toString() {
        return "(" + first.toString() + " " + signOfOperation + " " + second.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            AbstractOperation that = (AbstractOperation) obj;
            return this.first.equals(that.first) && this.second.equals(that.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((29 * this.first.hashCode() + this.second.hashCode()) * 29 + getClass().hashCode()) * 29;
    }
}
