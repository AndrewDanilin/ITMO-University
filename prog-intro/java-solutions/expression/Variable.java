package expression;

public class Variable implements ExpressionInterface {
    String name;

    public Variable (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Variable.class) {
            Variable that = (Variable) obj;
            return this.name.equals(that.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (name.hashCode() * 7);
    }


    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (this.name.equals("x")) {
            return x;
        } else if (this.name.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

}
