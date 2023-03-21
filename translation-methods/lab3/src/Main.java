import gen.PrefixExprLexer;
import gen.PrefixExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.*;

public class Main {
    public static final List<String> list = Arrays.asList(
            "if > 2 3 print 3",
            "= a 3",
            "both = a 3 = b 4",
            "if > 2 3 print 3 if > 4 - 7 2 print + 3 4",
            "both if > 5 - 7 2 both = a 3 both = b 4 print b if ! a print 10",
            "both = a 3 both = b 4 both = c 7 print both a both b c",
            "if | a & b c print c if & a b print a print b"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "examples" -> {
                list.forEach(example -> {
                    System.out.println(example);
                    CharStream charStream = CharStreams.fromString(example);
                    PrefixExprLexer lexer = new PrefixExprLexer(charStream);
                    PrefixExprParser parser = new PrefixExprParser(new CommonTokenStream(lexer));
                    System.out.println(parser.code().str + "\n");
                });
            }
            case "custom" -> {
                while (scanner.hasNextLine()) {
                    String expression = scanner.nextLine();
                    CharStream charStream = CharStreams.fromString(expression);
                    PrefixExprLexer lexer = new PrefixExprLexer(charStream);
                    PrefixExprParser parser = new PrefixExprParser(new CommonTokenStream(lexer));
                    System.out.println(parser.code().str + "\n");
                }
            }
        }
    }
}