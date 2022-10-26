package expression;


import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
//        ExpressionInterface op = (ExpressionInterface) parser.parse(
//                "-(-(-          -5 + 16   *x*y) + 1 * z) -(((-11)))");
//        System.out.println(op.toString());
//        System.out.println(op.evaluate(0, 0, 0));
    }

}