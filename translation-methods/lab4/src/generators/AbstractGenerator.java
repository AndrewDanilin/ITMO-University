package generators;

import grammar.Grammar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractGenerator {
    private final String fileName;
    protected final Grammar grammar;
    protected static final String NEW_LINE = "\n";
    protected static final String TAB = "\t";
    protected static final String JAVA_EXTENSION = ".java";
    protected AbstractGenerator(Grammar grammar, String fileName) {
        this.grammar = grammar;
        this.fileName = fileName;
    }
    protected void generatePackageDeclaration(BufferedWriter bufferedWriter) throws IOException {
        String NAME_OF_PACKAGE = "generated" + grammar.nameOfGrammar();
        String packageDeclaration = String.format("""
                package %s;
                
                """, NAME_OF_PACKAGE
        );
        bufferedWriter.write(packageDeclaration);
    }

    protected void generateClassDeclaration(BufferedWriter bufferedWriter) throws IOException {
        String classDeclaration = String.format("""
                public class %s%s {
                """, grammar.nameOfGrammar(), fileName);
        bufferedWriter.write(classDeclaration);
    }

    public void generate(String directory) {
        directory += grammar.nameOfGrammar() + fileName + JAVA_EXTENSION;

        try {
            Path path = Paths.get(directory);
            Files.createDirectories(path.getParent());
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {

                generatePackageDeclaration(bufferedWriter);
                generateImports(bufferedWriter);
                generateClassDeclaration(bufferedWriter);
                generateFields(bufferedWriter);
                generateConstructor(bufferedWriter);
                generateMethods(bufferedWriter);

                generateOther(bufferedWriter);

                bufferedWriter.write(NEW_LINE);
                bufferedWriter.write("}");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract void generateImports(BufferedWriter bufferedWriter) throws IOException;
    protected abstract void generateFields(BufferedWriter bufferedWriter) throws IOException;
    protected abstract void generateConstructor(BufferedWriter bufferedWriter) throws IOException;
    protected abstract void generateMethods(BufferedWriter bufferedWriter) throws IOException;
    protected abstract void generateOther(BufferedWriter bufferedWriter) throws IOException;
}
