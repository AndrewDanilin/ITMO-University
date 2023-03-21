package generatedArithmetic;

import java.text.ParseException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static generatedArithmetic.ArithmeticToken.TypeOfToken.*;

public class ArithmeticParser {
    private final ArithmeticLexicalAnalyzer lexicalAnalyzer;
    public ArithmeticParser(ArithmeticLexicalAnalyzer lexicalAnalyzer) throws ParseException {
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.lexicalAnalyzer.nextToken();
    }
    public EClass e() throws ParseException {
        EClass res = new EClass("e");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case DIGIT, MINUS, OPEN -> {
                TClass t0 = t();
                res.addChild(t0);
                EPRIMEClass ePrime1 = ePrime(t0.val);
                res.addChild(ePrime1);
                res.val = ePrime1.val;
            }
        }
        return res;
    }
    public EPRIMEClass ePrime(int acc) throws ParseException {
        EPRIMEClass res = new EPRIMEClass("ePrime");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case PLUS -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != PLUS) {
                    throw new ParseException(
                        "Expected token PLUS",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken PLUS0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(PLUS0.text));
                lexicalAnalyzer.nextToken();
                TClass t1 = t();
                res.addChild(t1);
                res.val = acc + t1.val;
                EPRIMEClass ePrime3 = ePrime(res.val);
                res.addChild(ePrime3);
                res.val = ePrime3.val;
            }
            case MINUS -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != MINUS) {
                    throw new ParseException(
                        "Expected token MINUS",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken MINUS0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(MINUS0.text));
                lexicalAnalyzer.nextToken();
                TClass t1 = t();
                res.addChild(t1);
                res.val = acc - t1.val;
                EPRIMEClass ePrime3 = ePrime(res.val);
                res.addChild(ePrime3);
                res.val = ePrime3.val;
            }
            case END, CLOSE -> {
                res.addChild(new Tree("EPS"));
                res.val = acc;
            }
        }
        return res;
    }
    public TClass t() throws ParseException {
        TClass res = new TClass("t");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case DIGIT, MINUS, OPEN -> {
                MClass m0 = m();
                res.addChild(m0);
                TPRIMEClass tPrime1 = tPrime(m0.val);
                res.addChild(tPrime1);
                res.val = tPrime1.val;
            }
        }
        return res;
    }
    public TPRIMEClass tPrime(int acc) throws ParseException {
        TPRIMEClass res = new TPRIMEClass("tPrime");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case MUL -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != MUL) {
                    throw new ParseException(
                        "Expected token MUL",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken MUL0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(MUL0.text));
                lexicalAnalyzer.nextToken();
                MClass m1 = m();
                res.addChild(m1);
                res.val = acc * m1.val;
                TPRIMEClass tPrime3 = tPrime(res.val);
                res.addChild(tPrime3);
                res.val = tPrime3.val;
            }
            case DIV -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != DIV) {
                    throw new ParseException(
                        "Expected token DIV",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken DIV0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(DIV0.text));
                lexicalAnalyzer.nextToken();
                MClass m1 = m();
                res.addChild(m1);
                res.val = acc / m1.val;
                TPRIMEClass tPrime3 = tPrime(res.val);
                res.addChild(tPrime3);
                res.val = tPrime3.val;
            }
            case END, CLOSE, PLUS, MINUS -> {
                res.addChild(new Tree("EPS"));
                res.val = acc;
            }
        }
        return res;
    }
    public MClass m() throws ParseException {
        MClass res = new MClass("m");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case MINUS -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != MINUS) {
                    throw new ParseException(
                        "Expected token MINUS",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken MINUS0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(MINUS0.text));
                lexicalAnalyzer.nextToken();
                MClass m1 = m();
                res.addChild(m1);
                res.val = -m1.val;
            }
            case DIGIT, OPEN -> {
                PClass p0 = p();
                res.addChild(p0);
                res.val = p0.val;
            }
        }
        return res;
    }
    public PClass p() throws ParseException {
        PClass res = new PClass("p");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case DIGIT, OPEN -> {
                FClass f0 = f();
                res.addChild(f0);
                PPRIMEClass pPrime1 = pPrime(f0.val);
                res.addChild(pPrime1);
                res.val = pPrime1.val;
            }
        }
        return res;
    }
    public PPRIMEClass pPrime(int acc) throws ParseException {
        PPRIMEClass res = new PPRIMEClass("pPrime");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case POW -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != POW) {
                    throw new ParseException(
                        "Expected token POW",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken POW0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(POW0.text));
                lexicalAnalyzer.nextToken();
                PClass p1 = p();
                res.addChild(p1);
                res.val = (int) Math.pow(acc, p1.val);
            }
            case DIV, MUL, END, CLOSE, PLUS, MINUS -> {
                res.addChild(new Tree("EPS"));
                res.val = acc;
            }
        }
        return res;
    }
    public FClass f() throws ParseException {
        FClass res = new FClass("f");
        switch (lexicalAnalyzer.getCurToken().typeOfToken) {
            case DIGIT -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != DIGIT) {
                    throw new ParseException(
                        "Expected token DIGIT",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken DIGIT0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(DIGIT0.text));
                lexicalAnalyzer.nextToken();
                res.val = Integer.parseInt(DIGIT0.text);
            }
            case OPEN -> {
                if (lexicalAnalyzer.getCurToken().typeOfToken != OPEN) {
                    throw new ParseException(
                        "Expected token OPEN",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken OPEN0 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(OPEN0.text));
                lexicalAnalyzer.nextToken();
                EClass e1 = e();
                res.addChild(e1);
                if (lexicalAnalyzer.getCurToken().typeOfToken != CLOSE) {
                    throw new ParseException(
                        "Expected token CLOSE",
                         lexicalAnalyzer.getCurPos()
                    );
                }
                ArithmeticToken CLOSE2 = new ArithmeticToken(
                    lexicalAnalyzer.getCurToken().text,
                    lexicalAnalyzer.getCurToken().typeOfToken
                );
                res.addChild(new Tree(CLOSE2.text));
                lexicalAnalyzer.nextToken();
                res.val = e1.val;
            }
        }
        return res;
    }
    public class EClass extends Tree {

        public int val;
        public EClass(String node) {
            super(node);
        }
    }
    public class EPRIMEClass extends Tree {

        public int val;
        public EPRIMEClass(String node) {
            super(node);
        }
    }
    public class TClass extends Tree {

        public int val;
        public TClass(String node) {
            super(node);
        }
    }
    public class TPRIMEClass extends Tree {

        public int val;
        public TPRIMEClass(String node) {
            super(node);
        }
    }
    public class MClass extends Tree {

        public int val;
        public MClass(String node) {
            super(node);
        }
    }
    public class PClass extends Tree {

        public int val;
        public PClass(String node) {
            super(node);
        }
    }
    public class PPRIMEClass extends Tree {

        public int val;
        public PPRIMEClass(String node) {
            super(node);
        }
    }
    public class FClass extends Tree {

        public int val;
        public FClass(String node) {
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