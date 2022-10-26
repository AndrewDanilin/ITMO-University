package expression.generic;

import expression.GenericTripleExpression;
import expression.exceptions.EvaluatingException;
import expression.exceptions.ModeException;
import expression.modes.*;
import expression.parser.GenericExpressionParser;


public class GenericTabulator implements Tabulator {

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ModeException {
        return switch (mode) {
            case "i" -> apply(expression, x1, x2, y1, y2, z1, z2, new IntegerMode(true));
            case "d" -> apply(expression, x1, x2, y1, y2, z1, z2, new DoubleMode());
            case "bi" -> apply(expression, x1, x2, y1, y2, z1, z2, new BigIntegerMode());
            case "u" -> apply(expression, x1, x2, y1, y2, z1, z2, new IntegerMode(false));
            case "f" -> apply(expression, x1, x2, y1, y2, z1, z2, new FloatMode());
            case "b" -> apply(expression, x1, x2, y1, y2, z1, z2, new ByteMode());
            default -> throw new ModeException("Illegal mode: " + mode);
        };
    }

    private <T extends Number> Object[][][] apply(String expression, int x1, int x2, int y1, int y2, int z1, int z2, Mode<T> mode) {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        GenericExpressionParser<T> parser = new GenericExpressionParser<>();
        GenericTripleExpression<T> parsingResult = parser.parse(expression, mode);

        for (int i = x1; i <= x2; i++) {
            T first = mode.getValue(Integer.toString(i));
            for (int j = y1; j <= y2; j++) {
                T second = mode.getValue(Integer.toString(j));
                for (int k = z1; k <= z2; k++) {
                    T third = mode.getValue(Integer.toString(k));
                    try {
                        result[i - x1][j - y1][k - z1] = parsingResult.evaluate(first, second, third);
                    } catch (EvaluatingException e) {
                        result[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}
