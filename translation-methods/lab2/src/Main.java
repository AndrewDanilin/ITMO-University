import java.io.*;
import java.text.ParseException;

public class Main {
    private static final String FORMULA = "(a -> b) -> c";

    public static void main(String[] args) throws ParseException {
        Parser parser = new Parser();
        try {
            Tree tree = parser.parse(new ByteArrayInputStream(FORMULA.getBytes()));
            tree.visualize("graph1");
        } catch (ParseException e) {
            System.err.println(e.getMessage() + " " + e.getErrorOffset());
        }

//        Test test = new Test(parser);
//        test.runTests();
    }
}