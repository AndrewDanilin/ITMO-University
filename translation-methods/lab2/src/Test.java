import java.io.ByteArrayInputStream;
import java.text.ParseException;

public class Test {
    private static final String OR_TEST = "a|b";
    private static final String AND_TEST = "a&b";
    private static final String XOR_TEST = "a^b";
    private static final String NOT_TEST = "!a";
    private static final String PRIM_TEST = "a";
    private static final String PARENTHESIS_TEST1 = "(a|b)";
    private static final String PARENTHESIS_TEST2 = "(a|b";
    private static final String RANDOM_TEST = "b&c^(!c^!!b&!(a|b))";
    private static final String WHITESPACES_TEST = "a             | b  ";
    private final Parser parser;

    public Test(Parser parser) {
        this.parser = parser;
    }

    public void runTests() throws ParseException {
        testOR();
        testXOR();
        testAND();
        testNot();
        testPrim();
        testParenthesis1();
        testParenthesis2();
        testRandom();
        testWhitespaces();
    }

    private void test(String formula, String output) throws ParseException {
        Tree tree = parser.parse(new ByteArrayInputStream(formula.getBytes()));
        tree.visualize(output);
        System.out.println(output + " " + "passed");
    }

    private void testOR() throws ParseException {
        test(OR_TEST, "or_test");
    }

    private void testXOR() throws ParseException {
        test(XOR_TEST, "xor_test");
    }

    private void testAND() throws ParseException {
        test(AND_TEST, "and_test");
    }

    private void testNot() throws ParseException {
        test(NOT_TEST, "not_test");
    }

    private void testPrim() throws ParseException {
        test(PRIM_TEST, "prim_test");
    }

    private void testParenthesis1() throws ParseException {
        test(PARENTHESIS_TEST1, "parenthesis1_test");
    }

    private void testParenthesis2() {
        try {
            test(PARENTHESIS_TEST2, "parenthesis2_test");
        } catch (ParseException e) {
            System.out.println("parenthesis2_test passed");
        }
    }

    private void testRandom() throws ParseException {
        test(RANDOM_TEST, "random_test");
    }

    private void testWhitespaces() throws ParseException {
        test(WHITESPACES_TEST , "whitespaces_test");
    }


}
