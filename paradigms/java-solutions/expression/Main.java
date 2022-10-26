package expression;

import expression.exceptions.ModeException;
import expression.generic.GenericTabulator;
import expression.parser.BaseParser;


public class Main {
    public static void main(String[] args) throws ModeException {
        Object[][][] result;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
        }
        try {
            result = new GenericTabulator().tabulate(args[0].substring(1), sb.toString(), -2, 2, -2, 2, -2, 2);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    for (int k = -2; k <= 2; k++) {
                        System.out.println("Result= " + result[i + 2][j + 2][k + 2] + " for x=" + i + " y=" + j + " z=" + k);
                    }
                }
            }
        } catch (ModeException e) {
            System.out.println("Illegal mode");
        }
    }

}