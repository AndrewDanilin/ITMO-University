package generators;

import grammar.Grammar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class LexicalAnalyzerGenerator extends AbstractGenerator {
    private final String LEXICAL_ANALYZER = "LexicalAnalyzer";

    public LexicalAnalyzerGenerator(Grammar grammar) {
        super(grammar, "LexicalAnalyzer");
    }

    @Override
    protected void generateImports(BufferedWriter bufferedWriter) throws IOException {
        String imports = """
                import java.util.regex.Matcher;
                import java.util.regex.Pattern;
                import java.text.ParseException;
                import java.util.Arrays;
                import java.util.List;
                
                """;
        bufferedWriter.write(imports);
    }

    @Override
    protected void generateClassDeclaration(BufferedWriter bufferedWriter) throws IOException {
        String classDeclaration = String.format("""
                public class %s%s {
                
                """,
                grammar.nameOfGrammar(),
                LEXICAL_ANALYZER
        );
        bufferedWriter.write(classDeclaration);
    }

    @Override
    protected void generateFields(BufferedWriter bufferedWriter) throws IOException {
        String patterns = String.format("""
                    List<Pattern> patterns = Arrays.asList(
                %s
                    );
                """,
                grammar.terminals().stream().map(terminal ->
                                TAB.repeat(2) + "Pattern.compile(" + (
                                        (terminal.text().startsWith("\""))
                                                ? terminal.text()
                                                : transformStringToValid(terminal.text())
                                ) + ")")
                        .collect(Collectors.joining("," + NEW_LINE))
                );
        bufferedWriter.write(patterns);
        String tokens = String.format("""
                    List<%sToken.TypeOfToken> tokens = Arrays.asList(
                %s
                    );
                """,
                grammar.nameOfGrammar(),
                grammar.terminals().stream().map(
                                terminal -> TAB.repeat(2) +
                                        grammar.nameOfGrammar() +
                                        "Token.TypeOfToken." +
                                        terminal.name())
                        .collect(Collectors.joining("," + NEW_LINE))
                );
        bufferedWriter.write(tokens);
        String utilVariables = String.format("""
                    private final Pattern whitespacesPattern = Pattern.compile("[ \\r\\n\\t]+");
                    private final String input;
                    private final int n;
                    private final Matcher matcher;
                    private %sToken curToken;
                    private int previousMatcherEnd;
                    private int currentMatcherEnd;
                    
                """, grammar.nameOfGrammar()
        );
        bufferedWriter.write(utilVariables);
    }

    @Override
    protected void generateConstructor(BufferedWriter bufferedWriter) throws IOException {
        String constructor = String.format("""
                    public %s%s(String input) throws ParseException {
                        this.input = input;
                        previousMatcherEnd = 0;
                        currentMatcherEnd = 0;
                        n = input.length();
                        matcher = whitespacesPattern.matcher(input);
                    }
                    
                """,
                grammar.nameOfGrammar(),
                LEXICAL_ANALYZER
        );
        bufferedWriter.write(constructor);
    }

    @Override
    protected void generateMethods(BufferedWriter bufferedWriter) throws IOException {
        String methods = String.format("""
                    public void nextToken() throws ParseException {
                        skipWhitespaces();
                        for (int i = 0; i < patterns.size(); i++) {
                            matcher.usePattern(patterns.get(i));
                            if (matcher.lookingAt()) {
                                previousMatcherEnd = currentMatcherEnd;
                                currentMatcherEnd = previousMatcherEnd + matcher.end();
                                matcher.reset(input.substring(currentMatcherEnd));
                                this.curToken = new %sToken(input.substring(previousMatcherEnd, currentMatcherEnd), tokens.get(i));
                                return;
                            }
                        }
                        if (currentMatcherEnd != n) {
                            throw new ParseException("Incorrect token at position: ", currentMatcherEnd);
                        } else {
                            this.curToken = new %sToken("$", %sToken.TypeOfToken.END);
                        }
                    }
                    
                    public int getCurPos() {
                        return currentMatcherEnd;
                    }
                    
                    private void skipWhitespaces() {
                        matcher.usePattern(whitespacesPattern);
                        if (matcher.lookingAt()) {
                            previousMatcherEnd = currentMatcherEnd;
                            currentMatcherEnd = previousMatcherEnd + matcher.end();
                            matcher.reset(input.substring(currentMatcherEnd));
                        }
                    }
                    
                    public %sToken getCurToken() {
                        return this.curToken;
                    }
                    
                """, grammar.nameOfGrammar(), grammar.nameOfGrammar(), grammar.nameOfGrammar(), grammar.nameOfGrammar());
        bufferedWriter.write(methods);
    }

    @Override
    protected void generateOther(BufferedWriter bufferedWriter) throws IOException {
        return;
    }

    public String transformStringToValid(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (int i = 1; i < str.length() - 1; i++) {
            switch (str.charAt(i)) {
                case '+' -> sb.append("\\\\+");
                case '*' -> sb.append("\\\\*");
                case '(' -> sb.append("\\\\(");
                case ')' -> sb.append("\\\\)");
                case '[' -> sb.append("\\\\[");
                case ']' -> sb.append("\\\\]");
                case '|' -> sb.append("\\\\|");
                case '^' -> sb.append("\\\\^");
                case '?' -> sb.append("\\\\?");
                default -> sb.append(str.charAt(i));
            }
        }
        sb.append("\"");
        return sb.toString();
    }
}
