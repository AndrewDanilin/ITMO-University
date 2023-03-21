package generators;

import grammar.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ParserGenerator extends AbstractGenerator {
    private final HashMap<String, Set<String>> first;
    private final HashMap<String, Set<String>> follow;

    public ParserGenerator(Grammar grammar) {
        super(grammar, "Parser");
        first = new HashMap<>();
        constructFirst();
        follow = new HashMap<>();
        constructFollow();
    }

    private void constructFirst() {
        for (NonTerminal nonTerminal : grammar.nonTerminals()) {
            first.put(nonTerminal.name(), new HashSet<>());
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : grammar.rules()) {
                for (Production production : rule.productions()) {
                    int previousSize = first.get(rule.nonTerminal().name()).size();
                    first.get(rule.nonTerminal().name()).addAll(getFirst(production));
                    if (previousSize != first.get(rule.nonTerminal().name()).size()) {
                        changed = true;
                    }
                }
            }
        }
    }

    private void constructFollow() {
        for (NonTerminal nonTerminal : grammar.nonTerminals()) {
            follow.put(nonTerminal.name(), new HashSet<>());
        }

        follow.get(grammar.startNonTerminal().name()).add("END");

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : grammar.rules()) {
                for (Production production : rule.productions()) {
                    for (int i = 0; i < production.order().size(); i++) {
                        if (production.type().get(i) == 0) {
                            String b = production.getNonTerminalByPos(i).name();
                            int previousSize = follow.get(b).size();
                            int sizeOfProd = production.order().size();
                            Production gamma = production.getGamma(i + 1);
                            Set<String> firstOfGamma = getFirst(gamma);
                            if (firstOfGamma.contains("EPS") ||
                                    (gamma.nonTerminals().isEmpty() && gamma.terminals().isEmpty())) {
                                follow.get(b).addAll(follow.get(rule.nonTerminal().name()));
                            }
                            firstOfGamma.remove("EPS");
                            follow.get(b).addAll(firstOfGamma);
                            if (previousSize != follow.get(b).size()) {
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public Set<String> getFirst(Production production) {
        Set<String> result = new HashSet<>();
        if (!production.terminals().isEmpty()) {
            result.add(production.terminals().get(0).name());
        } else if (!production.nonTerminals().isEmpty()) {
            result.addAll(this.first.get(production.nonTerminals().get(0).name()));
        }
        return result;
    }

    @Override
    protected void generateImports(BufferedWriter bufferedWriter) throws IOException {
        String imports = String.format("""
                import java.text.ParseException;
                import java.io.BufferedWriter;
                import java.io.IOException;
                import java.nio.file.Files;
                import java.nio.file.Path;
                import java.util.*;
                import static generated%s.%sToken.TypeOfToken.*;
                
                """, grammar.nameOfGrammar(), grammar.nameOfGrammar());
        bufferedWriter.write(imports);
    }

    @Override
    protected void generateFields(BufferedWriter bufferedWriter) throws IOException {
        String variables = String.format("""
                    private final %sLexicalAnalyzer lexicalAnalyzer;
                """, grammar.nameOfGrammar()
        );
        bufferedWriter.write(variables);
    }

    @Override
    protected void generateConstructor(BufferedWriter bufferedWriter) throws IOException {
        String constructor = String.format("""
                    public %sParser(%sLexicalAnalyzer lexicalAnalyzer) throws ParseException {
                        this.lexicalAnalyzer = lexicalAnalyzer;
                        this.lexicalAnalyzer.nextToken();
                    }
                """, grammar.nameOfGrammar(), grammar.nameOfGrammar()
        );
        bufferedWriter.write(constructor);
    }

    @Override
    protected void generateMethods(BufferedWriter bufferedWriter) throws IOException {
        for (Rule rule : grammar.rules()) {
            generateMethod(bufferedWriter, rule);
        }
    }

    @Override
    protected void generateOther(BufferedWriter bufferedWriter) throws IOException {
        generateClasses(bufferedWriter);
        generateClassTree(bufferedWriter);
    }

    private void generateMethod(BufferedWriter bufferedWriter, Rule rule) throws IOException {
        String resultClass = rule.nonTerminal().name().toUpperCase() + "Class";

        String inheritedAttrs = (rule.nonTerminal().inheritedAttributes() == null)
                ? ""
                : rule.nonTerminal().inheritedAttributes();

        String firstPart = String.format("""
                            public %s %s(%s) throws ParseException {
                                %s res = new %s(%s);
                                switch (lexicalAnalyzer.getCurToken().typeOfToken) {
                        """,
                resultClass,
                rule.nonTerminal().name(),
                inheritedAttrs,
                resultClass,
                resultClass,
                "\"" + rule.nonTerminal().name() + "\""
        );

        StringBuilder sbCases = new StringBuilder();

        for (Production production : rule.productions()) {
            Set<String> firstSet = getFirst(production);
            if (firstSet.contains("EPS")) {
                String stringFollow = String.join(", ", follow.get(rule.nonTerminal().name()));
                sbCases.append(String.format("""
                                        case %s -> {
                                            res.addChild(new Tree("EPS"));
                            """, stringFollow)
                );
                sbCases.append(String.format("""
                                        %s
                                    }
                        """, production.broadcastSymbols().stream()
                                .map(e -> e.code().replaceAll("\\$", ""))
                                .collect(Collectors.joining(NEW_LINE)))
                );
            } else {
                sbCases.append(String.format("""
                                    case %s -> {
                        """, String.join(", ", firstSet))
                );
                for (int i = 0; i < production.order().size(); i++) {
                    if (production.type().get(i) == 0) {
                        NonTerminal nonTerminal = production.getNonTerminalByPos(i);
                        String innerClass = nonTerminal.name().toUpperCase() + "Class";
                        sbCases.append(
                                String.format("""
                                                        %s %s%d = %s(%s);
                                                        res.addChild(%s%d);
                                        """,
                                        innerClass,
                                        nonTerminal.name(),
                                        i,
                                        nonTerminal.name(),
                                        nonTerminal.inheritedAttributes() == null
                                                ? ""
                                                : nonTerminal.inheritedAttributes(),
                                        nonTerminal.name(),
                                        i
                                )
                        );
                    } else if (production.type().get(i) == 1) {
                        sbCases.append(String.format("""
                                                        if (lexicalAnalyzer.getCurToken().typeOfToken != %s) {
                                                            throw new ParseException(
                                                                "Expected token %s",
                                                                 lexicalAnalyzer.getCurPos()
                                                            );
                                                        }
                                                        %sToken %s%d = new %sToken(
                                                            lexicalAnalyzer.getCurToken().text,
                                                            lexicalAnalyzer.getCurToken().typeOfToken
                                                        );
                                                        res.addChild(new Tree(%s%d.text));
                                                        lexicalAnalyzer.nextToken();
                                        """,
                                production.getTerminalByPos(i).name(),
                                production.getTerminalByPos(i).name(),
                                grammar.nameOfGrammar(),
                                production.getTerminalByPos(i).name(),
                                i,
                                grammar.nameOfGrammar(),
                                production.getTerminalByPos(i).name(),
                                i
                                )
                        );
                    } else {
                        String code = production.getBroadcastSymbolByPos(i).code()
                                .replaceAll("\\$", "");
                        sbCases.append(
                                String.format("""
                                                        %s
                                        """, code)
                        );
                    }
                }
                sbCases.append("""
                                            }
                                """);
            }
        }
        String returnsPart = """
                        }
                        return res;
                    }
                """;
        bufferedWriter.write(firstPart);
        bufferedWriter.write(sbCases.toString());
        bufferedWriter.write(returnsPart);
    }

    private void generateClasses(BufferedWriter bufferedWriter) throws IOException {
        for (Rule rule : grammar.rules()) {
            generateClass(bufferedWriter, rule);
        }
    }

    private void generateClass(BufferedWriter bufferedWriter, Rule rule) throws IOException {
        StringBuilder classSb = new StringBuilder();
        String className = rule.nonTerminal().name().toUpperCase() + "Class";

        classSb.append(String.format("""
                    public class %s extends Tree {
                        
                """, className)
        );

        if (rule.nonTerminal().synthesizedAttributes() != null) {
            for (String string : rule.nonTerminal().synthesizedAttributes().split(", ")) {
                classSb.append(String.format("""
                                public %s;
                        """, string)
                );
            }
        }

        classSb.append(String.format("""
                        public %s(String node) {
                            super(node);
                        }
                    }
                """, className)
        );

        bufferedWriter.write(classSb.toString());
    }

    private void generateClassTree(BufferedWriter bufferedWriter) throws IOException {
        String treeClass = """
                    public class Tree {
                        private static final char TAB = '\\t';
                        private static final char NEW_LINE = '\\n';
                                    
                        String node;
                        List<Tree> children;
                                    
                        public Tree(String node) {
                            this.node = node;
                            children = new ArrayList<>(Collections.emptyList());
                        }
                        
                        public void addChild(Tree tree) {
                            children.add(tree);
                        }
                                    
                        public void visualize(String graphName) {
                            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(graphName + ".dot"))) {
                                bufferedWriter.write(treeToDotRepresentation(this));
                                Runtime.getRuntime().exec("dot " + graphName + ".dot -Tpng -o " + graphName + ".png");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                                    
                        private static String treeToDotRepresentation(Tree tree) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("digraph {").append("\\n");
                            bfs(tree, sb);
                            sb.append("}");
                            return sb.toString();
                        }
                                    
                        private static void bfs(Tree tree, StringBuilder sb) {
                            Deque<TreeWithID> deque = new ArrayDeque<>();
                            int id = 0;
                            deque.add(new TreeWithID(tree, id));
                                    
                            while (!deque.isEmpty()) {
                                TreeWithID currentTree = deque.pop();
                                    
                                sb.append(TAB)
                                        .append(currentTree.id)
                                        .append(" [label=\\"")
                                        .append(currentTree.tree.node)
                                        .append("\\"]")
                                        .append(NEW_LINE);
                                    
                                if (!currentTree.tree.children.isEmpty()) {
                                    for (Tree child : currentTree.tree.children) {
                                        id++;
                                        sb.append(TAB).append(currentTree.id).append(" -> ").append(id).append(NEW_LINE);
                                        deque.add(new TreeWithID(child, id));
                                    }
                                }
                            }
                        }
                                    
                        private static class TreeWithID {
                            private Tree tree;
                            private int id;
                                    
                            public TreeWithID(Tree tree, int id) {
                                this.tree = tree;
                                this.id = id;
                            }
                        }
                                    
                    }
                """;
        bufferedWriter.write(treeClass);
    }
}


