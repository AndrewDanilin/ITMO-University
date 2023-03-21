import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;
import generatedArithmetic.ArithmeticLexicalAnalyzer;
import generatedArithmetic.ArithmeticParser;
import generatedLogicInC.LogicInCLexicalAnalyzer;
import generatedLogicInC.LogicInCParser;
import metaGrammar.MetaGrammarLexer;
import metaGrammar.MetaGrammarParser;
import generators.LexicalAnalyzerGenerator;
import generators.ParserGenerator;
import generators.TokenGenerator;
import grammar.Grammar;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    private static final String[] nameOfGrammars = {"Arithmetic", "LogicInC"};

    public static void main(String[] args) throws IOException, ParseException {
        System.out.print("""
                Usage:
                0 - calculator
                1 - second lab (Logic expression in C style)
                """
        );
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (!input.equals("Arithmetic") && !input.equals("LogicInC")) {
            System.err.println("Invalid input. Should be Arithmetic or LogicInC");
            return;
        }

//        generate(input)
        int n = Integer.parseInt(input);
        if (n == 0) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    ArithmeticLexicalAnalyzer lexicalAnalyzer = new ArithmeticLexicalAnalyzer(line);
                    ArithmeticParser parser = new ArithmeticParser(lexicalAnalyzer);
                    ArithmeticParser.EClass tree = parser.e();

                    tree.visualize("graph");
                    System.out.println(tree.val);
                } catch (ParseException e) {
                    System.err.println(e.getLocalizedMessage() + " " + e.getErrorOffset());
                }
            }
        } else if (n == 1) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    LogicInCLexicalAnalyzer lexicalAnalyzer = new LogicInCLexicalAnalyzer(line);
                    LogicInCParser parser = new LogicInCParser(lexicalAnalyzer);
                    LogicInCParser.FClass tree = parser.f();

                    tree.visualize("graph");
                } catch (ParseException e) {
                    System.err.println(e.getLocalizedMessage() + " " + e.getErrorOffset());
                }
            }
        }
    }

//    public static void generate(int input) throws IOException {
//        String inputGrammar = Files.readString(Path.of("./src/Arithemic"));
//
//        CharStream charStream = CharStreams.fromString(inputGrammar);
//        MetaGrammarLexer lexer = new MetaGrammarLexer(charStream);
//        MetaGrammarParser parser = new MetaGrammarParser(new CommonTokenStream(lexer));
//
//        Grammar grammar = parser.metaGrammar().grammar;
//
//
//        TokenGenerator tokenGenerator = new TokenGenerator(grammar);
//        LexicalAnalyzerGenerator lexicalAnalyzerGenerator = new LexicalAnalyzerGenerator(grammar);
//        ParserGenerator parserGenerator = new ParserGenerator(grammar);
//
//        String directoryToGenerate = "./src/generated" + nameOfGrammars[input] + "/";
//        Path dir = Paths.get(directoryToGenerate);
//        if (Files.exists(dir)) {
//            for (File file : Objects.requireNonNull(dir.toFile().listFiles())) {
//                file.delete();
//            }
//        }
//
//        tokenGenerator.generate(directoryToGenerate);
//        lexicalAnalyzerGenerator.generate(directoryToGenerate);
//        parserGenerator.generate(directoryToGenerate);
//
//        System.out.println("Enter your " + nameOfGrammars[input] + " expression");
//    }
}