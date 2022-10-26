package info.kgeorgiy.ja.danilin.implementor;

import info.kgeorgiy.java.advanced.implementor.Impler;
import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 * Class which implements {@link Impler} and {@link JarImpler} interfaces.
 */
public class Implementor implements Impler, JarImpler {

    /**
     * Constant equals to java extension.
     */
    private static final String JAVA_EXTENSION = ".java";

    /**
     * Constant equals to class extension.
     */
    private static final String CLASS_EXTENSION = ".class";

    /**
     * Constant equals to one dot.
     */
    private static final String DOT = ".";

    /**
     * Constant equals to path separator char.
     */
    private static final String PATH_SEPARATOR = "/";

    /**
     * Custom {@link FileVisitor} which clean all directory.
     */
    private static final SimpleFileVisitor<Path> CUSTOM_FILE_VISITOR = new SimpleFileVisitor<>() {

        @Override
        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
        }

    };

    /**
     * Main function of program, gets arguments from console and if arguments are valid run corresponding implementation.
     *
     * @param args given arguments.
     */
    public static void main(String[] args) {
        if (!argsAreValid(args)) {
            System.err.printf(String.join(System.lineSeparator()),
                    "Invalid arguments. Usage:",
                    "To implement interface: First - interface name, second - path",
                    "To implement interface and zip into jar: first - -jar, second - interface name, third - path to jar"
            );
            return;
        }

        Implementor implementor = new Implementor();
        try {
            if (args.length == 2) {
                implementor.implement(Class.forName(args[0]), Path.of(args[1]));
            } else {
                implementor.implementJar(Class.forName(args[1]), Path.of(args[2]));
            }
        } catch (ImplerException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Invalid input name of class: " + e.getMessage());
        } catch (InvalidPathException e) {
            System.err.println("Ivalid input path: " + e.getMessage());
        }
    }

    /**
     * Check if arguments aren't null and have length 2 or 3.
     *
     * @param args given arguments.
     * @return {@code true} if arguments satisfy condition or {@code false} if not.
     */
    private static boolean argsAreValid(String[] args) {
        if (args == null || (args.length != 2 && args.length != 3)) {
            return false;
        }

        for (String argument : args) {
            if (argument == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates a {@code .java} file with name as class name of {@code token} with {@code Impl} suffix,
     * this file which contains code of class implementing interface
     * specified by {@code token} in {@code root} directory.
     *
     * @param token type token to create implementation for.
     * @param root  root directory.
     * @throws ImplerException if I/O exception occurred or given token isn't supported for implement.
     */
    @Override
    public void implement(Class<?> token, Path root) throws ImplerException {
        checkTokenIsValid(token);

        Path path = getResolvedPath(token, root);

        createParentDirectories(path);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(getUnicodeRepresentation(ClassGenerator.generateCode(token)));
        } catch (IOException e) {
            throw new ImplerException("Error while reading output file: " + e.getMessage());
        }
    }

    /**
     * Converts {@code string} to unicode representation.
     *
     * @param string given string.
     * @return unicode representation of given string.
     */
    private String getUnicodeRepresentation(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : string.toCharArray()) {
            stringBuilder.append(c < 128 ? c : String.format("\\u%04x", (int) c));
        }
        return stringBuilder.toString();
    }

    /**
     * Checks if token isn't primitive, array, enum type and checks if modifier of this token isn't private.
     *
     * @param token given token.
     * @throws ImplerException if token doesn't satisfy all conditions.
     */
    private void checkTokenIsValid(Class<?> token) throws ImplerException {
        if (token.isPrimitive() ||
                token.isArray() ||
                token.isEnum() ||
                Modifier.isPrivate(token.getModifiers()) ||
                !token.isInterface()
        ) {
            throw new ImplerException("Invalid token. Token should be interface");
        }
    }

    /**
     * Creates a {@link Path} which presents correct path where {@code token} should be by resolve with {@code root} path.
     *
     * @param token given token.
     * @param root  given root.
     * @return a {@link Path} the resulting path.
     */
    private Path getResolvedPath(Class<?> token, Path root) {
        return root.resolve(Path.of(token.getPackageName().replace('.', File.separatorChar),
                ClassGenerator.getImplClassName(token) +
                        JAVA_EXTENSION)
        );
    }

    /**
     * Creates a directory by creating parent directory, if parent directory is null this method do nothing.
     *
     * @param path given path.
     * @throws ImplerException if an I/O error occurred.
     */
    private void createParentDirectories(Path path) throws ImplerException {
        if (path.getParent() != null) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new ImplerException("Failed to create parent directory: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Creates a {@code .jar} file which contains compiled class
     * implemented by {@link #implement(Class, Path)} in given {@code jarFile}.
     *
     * @param token   type token to create implementation for.
     * @param jarFile target <var>.jar</var> file.
     * @throws ImplerException if I/O exception occurred or given token isn't supported for implement.
     */
    @Override
    public void implementJar(Class<?> token, Path jarFile) throws ImplerException {
        checkTokenIsValid(token);

        createParentDirectories(jarFile);

        final Path tempRoot;
        try {
            tempRoot = Files.createTempDirectory(jarFile.getParent(), "tempRoot");
        } catch (IOException e) {
            throw new ImplerException("Failed to create temporary directory: " + e.getMessage(), e);
        }

        implement(token, tempRoot);

        try (JarOutputStream jarOutputStream = new JarOutputStream(Files.newOutputStream(jarFile))) {
            compileByTokenAndPath(token, tempRoot);
            String pathToClass = (token.getPackageName() + DOT).replace(DOT, PATH_SEPARATOR)
                    + ClassGenerator.getImplClassName(token) + CLASS_EXTENSION;
            jarOutputStream.putNextEntry(new ZipEntry(pathToClass));
            Files.copy(tempRoot.resolve(pathToClass), jarOutputStream);
        } catch (IOException e) {
            throw new ImplerException("Error while writing", e);
        }

        try {
            Files.walkFileTree(tempRoot, CUSTOM_FILE_VISITOR);
        } catch (IOException e) {
            throw new ImplerException("Something went wrong while deleting temporary directory: " + e.getMessage(), e);
        }

    }

    /**
     * Compile {@code .java} file by specified {@code token}.
     *
     * @param token given token.
     * @param path  given path.
     * @throws ImplerException if {@link URISyntaxException} occurred
     *                         or java compiler not found or code of compilation doesn't equal 0.
     */
    public void compileByTokenAndPath(Class<?> token, Path path) throws ImplerException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {
            throw new ImplerException("Could not find java compiler");
        }

        final String classpath;

        try {
            classpath = path +
                    File.pathSeparator +
                    Path.of(token.getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
        } catch (URISyntaxException e) {
            throw new ImplerException("Failed to get URI", e);
        }

        final String[] args = Stream.of("-cp", classpath, getResolvedPath(token, path).toAbsolutePath().toString()).toArray(String[]::new);
        final int exitCode = compiler.run(null, null, null, args);

        if (exitCode != 0) {
            throw new ImplerException("Failed to compile file");
        }

    }

    /**
     * Class which generate code.
     */
    private static class ClassGenerator {

        /**
         * Constant equals to left curly bracket symbol.
         */
        private static final String LEFT_CURLY_BRACKET = "{";

        /**
         * Constant equals to right curly bracket symbol.
         */
        private static final String RIGHT_CURLY_BRACKET = "}";

        /**
         * Constant equals to one space.
         */
        private static final String SPACE = " ";

        /**
         * Constant equals to four spaces.
         */
        private static final String TAB = SPACE.repeat(4);

        /**
         * Constant equals to semicolon symbol.
         */
        private static final String SEMICOLON = ";";

        /**
         * Constant equals to line separator.
         */
        private static final String NEW_LINE = System.lineSeparator();

        /**
         * Constant equals to string {@code package}.
         */
        private static final String PACKAGE = "package";

        /**
         * Constant equals to string {@code public}.
         */
        private static final String PUBLIC = "public";

        /**
         * Constant equals to string {@code class}.
         */
        private static final String CLASS = "class";

        /**
         * Constant equals to string {@code implements}.
         */
        private static final String IMPLEMENTS = "implements";

        /**
         * Constant equals to empty string.
         */
        private static final String EMPTY = "";

        /**
         * Constant equals to delimiter of function arguments.
         */
        private static final String DELIMITER = ", ";

        /**
         * Constant equals to string {@code return}.
         */
        private static final String RETURN = "return";

        /**
         * Constant equals to string {@code throws}.
         */
        private static final String THROWS = "throws";

        /**
         * Constant equals to string {@code true}.
         */
        private static final String TRUE = "true";

        /**
         * Constant equals to string {@code null}.
         */
        private static final String NULL = "null";

        /**
         * Constant equals to zero string representation.
         */
        private static final String ZERO = "0";

        /**
         * Constant equals to left parenthesis.
         */
        private static final String LEFT_PARENTHESIS = "(";

        /**
         * Constant equals to right parenthesis.
         */
        private static final String RIGHT_PARENTHESIS = ")";

        /**
         * Constant equals to eight spaces.
         */
        private static final String DOUBLE_TAB = TAB.repeat(2);

        /**
         * Creates code which implements interface specified by {@code token}.
         *
         * @param token given token.
         * @return result of generating in string representation.
         */
        public static String generateCode(Class<?> token) {
            return String.join(NEW_LINE,
                    generatePackage(token),
                    generateClassDeclaration(token) + SPACE + LEFT_CURLY_BRACKET,
                    generateMethods(token),
                    RIGHT_CURLY_BRACKET
            );
        }

        /**
         * Creates a string which contains package declaration specified by {@code token}.
         *
         * @param token given token.
         * @return result of generating in string representation, if package doesn't exist return empty string.
         */
        private static String generatePackage(Class<?> token) {
            String packageName = token.getPackageName();
            if (packageName.isEmpty()) {
                return EMPTY;
            }
            return PACKAGE + SPACE + packageName + SEMICOLON;
        }

        /**
         * Creates class declaration specified by {@code token}.
         *
         * @param token given token.
         * @return result of generating in string representation.
         */
        private static String generateClassDeclaration(Class<?> token) {
            return String.join(SPACE, PUBLIC, CLASS, getImplClassName(token), IMPLEMENTS, token.getCanonicalName());
        }

        /**
         * Creates methods implementation specified by {@code token}.
         *
         * @param token given token.
         * @return result of generating in string representation.
         */
        private static String generateMethods(Class<?> token) {
            return Arrays.stream(token.getMethods())
                    .map(ClassGenerator::generateMethod)
                    .collect(Collectors.joining(NEW_LINE));
        }

        /**
         * Creates method declaration and method body specified by {@code method}.
         *
         * @param method given method.
         * @return result of generating in string representation.
         */
        private static String generateMethod(Method method) {
            return String.join(NEW_LINE,
                    EMPTY,
                    TAB + String.join(SPACE,
                            PUBLIC,
                            method.getReturnType().getCanonicalName(),
                            method.getName() + generateArguments(method),
                            generateExceptions(method),
                            LEFT_CURLY_BRACKET),
                    generateMethodBody(method),
                    TAB + RIGHT_CURLY_BRACKET,
                    EMPTY);
        }

        /**
         * Creates string which contains exception which {@code method} can throw.
         *
         * @param method given method.
         * @return result string or empty string if method doesn't throw any exceptions.
         */
        private static String generateExceptions(Method method) {
            Class<?>[] exceptions = method.getExceptionTypes();
            return exceptions.length == 0
                    ? EMPTY
                    : THROWS + SPACE + Arrays.stream(exceptions).map(Class::getCanonicalName).collect(Collectors.joining(DELIMITER));
        }

        /**
         * Creates method body.
         *
         * @param method given method.
         * @return result in string representation.
         */
        private static String generateMethodBody(Method method) {
            return DOUBLE_TAB + RETURN + generateDefaultReturnValue(method) + SEMICOLON;
        }

        /**
         * Creates arguments which method use.
         *
         * @param method given method.
         * @return result in string representation.
         */
        private static String generateArguments(Method method) {
            return LEFT_PARENTHESIS +
                    Arrays.stream(method.getParameters())
                            .map(parameter -> parameter.getType().getCanonicalName() + SPACE + parameter.getName())
                            .collect(Collectors.joining(DELIMITER)) +
                    RIGHT_PARENTHESIS;
        }

        /**
         * Creates a string which contains {@code token} class name concatenated with {@code Impl} suffix.
         *
         * @param token given token.
         * @return result in string representation.
         */
        private static String getImplClassName(Class<?> token) {
            return token.getSimpleName() + "Impl";
        }

        /**
         * Creates a string which contains default return value of given {@code method},
         * creates true, 0, null or empty string depending on {@code method} return value type.
         *
         * @param method given method.
         * @return default return value in string representation.
         */
        private static String generateDefaultReturnValue(Method method) {
            if (method.getReturnType().equals(void.class)) {
                return EMPTY;
            } else if (method.getReturnType().equals(boolean.class)) {
                return SPACE + TRUE;
            } else if (method.getReturnType().isPrimitive()) {
                return SPACE + ZERO;
            } else {
                return SPACE + NULL;
            }
        }
    }
}
