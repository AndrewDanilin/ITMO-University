package expression;

public class Const implements ExpressionInterface {
    int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Const.class) {
            Const that = (Const) obj;
            return this.value == that.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
