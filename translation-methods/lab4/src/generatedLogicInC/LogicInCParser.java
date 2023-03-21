package generatedLogicInC;

import java.text.ParseException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static generatedLogicInC.LogicInCToken.TypeOfToken.*;

public class LogicInCParser {
    private final LogicInCLexicalAnalyzer lexicalAnalyzer;
    public LogicInCParser(LogicInCLexicalAnalyzer lexicalAnalyzer) throws ParseException {
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.lexicalAnalyzer.nextToken();
    }
    public FClass f() throws ParseException {
        FClass res = new FClass("f");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case NOT, VAR, OPEN -> {
                TClass t0 = t();
                res.addChild(t0);
                FPRIMEClass fPrime1 = fPrime();
                res.addChild(fPrime1);
            }
        }
        return res;
    }
    public FPRIMEClass fPrime() throws ParseException {
        FPRIMEClass res = new FPRIMEClass("fPrime");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case OR -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != OR) {
                    throw new ParseException(
                        "Expected token OR",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken OR0 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(OR0.text));
                lexicalAnalyzer.nextToken();
                TClass t1 = t();
                res.addChild(t1);
                FPRIMEClass fPrime2 = fPrime();
                res.addChild(fPrime2);
            }
            case END, CLOSE -> {
                res.addChild(new Tree("EPS"));
                
            }
        }
        return res;
    }
    public TClass t() throws ParseException {
        TClass res = new TClass("t");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case NOT, VAR, OPEN -> {
                GClass g0 = g();
                res.addChild(g0);
                TPRIMEClass tPrime1 = tPrime();
                res.addChild(tPrime1);
            }
        }
        return res;
    }
    public TPRIMEClass tPrime() throws ParseException {
        TPRIMEClass res = new TPRIMEClass("tPrime");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case XOR -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != XOR) {
                    throw new ParseException(
                        "Expected token XOR",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken XOR0 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(XOR0.text));
                lexicalAnalyzer.nextToken();
                GClass g1 = g();
                res.addChild(g1);
                TPRIMEClass tPrime2 = tPrime();
                res.addChild(tPrime2);
            }
            case OR, END, CLOSE -> {
                res.addChild(new Tree("EPS"));
                
            }
        }
        return res;
    }
    public GClass g() throws ParseException {
        GClass res = new GClass("g");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case NOT, VAR, OPEN -> {
                NClass n0 = n();
                res.addChild(n0);
                GPRIMEClass gPrime1 = gPrime();
                res.addChild(gPrime1);
            }
        }
        return res;
    }
    public GPRIMEClass gPrime() throws ParseException {
        GPRIMEClass res = new GPRIMEClass("gPrime");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case AND -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != AND) {
                    throw new ParseException(
                        "Expected token AND",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken AND0 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(AND0.text));
                lexicalAnalyzer.nextToken();
                NClass n1 = n();
                res.addChild(n1);
                GPRIMEClass gPrime2 = gPrime();
                res.addChild(gPrime2);
            }
            case OR, END, XOR, CLOSE -> {
                res.addChild(new Tree("EPS"));
                
            }
        }
        return res;
    }
    public NClass n() throws ParseException {
        NClass res = new NClass("n");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case NOT -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != NOT) {
                    throw new ParseException(
                        "Expected token NOT",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken NOT0 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(NOT0.text));
                lexicalAnalyzer.nextToken();
                NClass n1 = n();
                res.addChild(n1);
            }
            case VAR, OPEN -> {
                PClass p0 = p();
                res.addChild(p0);
            }
        }
        return res;
    }
    public PClass p() throws ParseException {
        PClass res = new PClass("p");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case OPEN -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != OPEN) {
                    throw new ParseException(
                        "Expected token OPEN",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken OPEN0 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(OPEN0.text));
                lexicalAnalyzer.nextToken();
                FClass f1 = f();
                res.addChild(f1);
                if (lexicalAnalyzer.getCurToken().typeOfToken != CLOSE) {
                    throw new ParseException(
                        "Expected token CLOSE",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken CLOSE2 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(CLOSE2.text));
                lexicalAnalyzer.nextToken();
            }
            case VAR -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != VAR) {
                    throw new ParseException(
                        "Expected token VAR",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                LogicInCToken VAR0 = new LogicInCToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(VAR0.text));
                lexicalAnalyzer.nextToken();
            }
        }
        return res;
    }
    public class FClass extends Tree {

        public FClass(String node) {
            super(node);
        }
    }
    public class FPRIMEClass extends Tree {

        public FPRIMEClass(String node) {
            super(node);
        }
    }
    public class TClass extends Tree {

        public TClass(String node) {
            super(node);
        }
    }
    public class TPRIMEClass extends Tree {

        public TPRIMEClass(String node) {
            super(node);
        }
    }
    public class GClass extends Tree {

        public GClass(String node) {
            super(node);
        }
    }
    public class GPRIMEClass extends Tree {

        public GPRIMEClass(String node) {
            super(node);
        }
    }
    public class NClass extends Tree {

        public NClass(String node) {
            super(node);
        }
    }
    public class PClass extends Tree {

        public PClass(String node) {
            super(node);
        }
    }
    public class Tree {
        private static final char TAB = '\t';
        private static final char NEW_LINE = '\n';

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
            sb.append("digraph {").append("\n");
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
                        .append(" [label=\"")
                        .append(currentTree.tree.node)
                        .append("\"]")
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

}