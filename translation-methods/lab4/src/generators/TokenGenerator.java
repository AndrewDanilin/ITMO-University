package generators;

import grammar.Grammar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class TokenGenerator extends AbstractGenerator {
    private static final String TOKEN = "Token";

    public TokenGenerator(Grammar grammar) {
        super(grammar, "Token");
    }
    @Override
    protected void generateImports(BufferedWriter bufferedWriter) throws IOException {}

    @Override
    protected void generateFields(BufferedWriter bufferedWriter) throws IOException {
        String variables = """
                    public final TypeOfToken typeOfToken;
                    public final String text;
                    
                """;

        bufferedWriter.write(variables);
    }

    @Override
    protected void generateConstructor(BufferedWriter bufferedWriter) throws IOException {
        String constructor = String.format("""
                    %s%s(String text, TypeOfToken typeOfToken) {
                        this.text = text;
                        this.typeOfToken = typeOfToken;
                    }
                    
                """, grammar.nameOfGrammar(), TOKEN
        );
        bufferedWriter.write(constructor);
    }

    @Override
    protected void generateMethods(BufferedWriter bufferedWriter) throws IOException {}

    @Override
    protected void generateOther(BufferedWriter bufferedWriter) throws IOException {
        String enumDeclaration = String.format("""
                    enum TypeOfToken {
                %s,
                        END
                    }
                    
                """,
                grammar.terminals().stream()
                        .map(e -> TAB.repeat(2) + e.name())
                        .collect(Collectors.joining("," + NEW_LINE))
        );

        bufferedWriter.write(enumDeclaration);
    }

}
