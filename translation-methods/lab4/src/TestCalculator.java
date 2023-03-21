import generatedArithmetic.ArithmeticLexicalAnalyzer;
import generatedArithmetic.ArithmeticParser;

import java.text.ParseException;

public class TestCalculator {
    private static final String PLUS_TEST = "2+2";
    private static final String MINUS_TEST = "2-2";
    private static final String MUL_TEST = "2*2";
    private static final String DIV_TEST = "2/2";
    private static final String NEG_TEST = "-2";
    private static final String PARENTHESIS_TEST1 = "(2+2)";
    private static final String PARENTHESIS_TEST2 = "(2+2";
    private static final String RANDOM_TEST1 = "2+2*2";
    private static final String RANDOM_TEST2 = "2-2-2";
    private static final String RANDOM_TEST3 = "2+2*2*(2+2)/(2 /2 + 2)";
    private static final String WHITESPACES_TEST = "2             + 2  ";

    public void runTests() throws ParseException {
        testPlus();
        testMinus();
        testMul();
        testDiv();
        testNeg();
        testParenthesis1();
        testParenthesis2();
        testRandom1();
        testRandom2();
        testRandom3();
        testWhitespaces();
    }

    private void test(String formula, String output, int expectedVal) throws ParseException {
        ArithmeticLexicalAnalyzer lexicalAnalyzer = new ArithmeticLexicalAnalyzer(formula);
        ArithmeticParser parser = new ArithmeticParser(lexicalAnalyzer);
        ArithmeticParser.EClass tree = parser.e();

        if (tree.val == expectedVal) {
            System.out.println(output + " passed");
        } else {
            System.out.println(output + " failed. expected: " + expectedVal + " actual: " + tree.val);
        }
    }

    private void testPlus() throws ParseException {
        test(PLUS_TEST, "plus_test", 4);
    }

    private void testMinus() throws ParseException {
        test(MINUS_TEST, "minus_test", 0);
    }

    private void testMul() throws ParseException {
        test(MUL_TEST, "mul_test", 4);
    }

    private void testDiv() throws ParseException {
        test(DIV_TEST, "div_test", 1);
    }

    private void testNeg() throws ParseException {
        test(NEG_TEST, "neg_test", -2);
    }

    private void testParenthesis1() throws ParseException {
        test(PARENTHESIS_TEST1, "parenthesis1_test", 4);
    }

    private void testParenthesis2() {
        try {
            test(PARENTHESIS_TEST2, "parenthesis2_test", 0);
        } catch (ParseException e) {
            System.out.println("parenthesis2_test passed");
        }
    }

    private void testRandom1() throws ParseException {
        test(RANDOM_TEST1, "random_test1", 6);
    }

    private void testRandom2() throws ParseException {
        test(RANDOM_TEST2, "random_test2", -2);
    }

    private void testRandom3() throws ParseException {
        test(RANDOM_TEST3, "random_test3", 7);
    }

    private void testWhitespaces() throws ParseException {
        test(WHITESPACES_TEST , "whitespaces_test", 4);
    }


}
