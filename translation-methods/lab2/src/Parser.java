import java.io.InputStream;
import java.text.ParseException;

public class Parser {
    private LexicalAnalyzer lexicalAnalyzer;

    public Tree parse(InputStream is) throws ParseException {
        lexicalAnalyzer = new LexicalAnalyzer(is);
        lexicalAnalyzer.nextToken();
        return E();
    }

    private Tree E() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case NOT, VAR, LPAREN -> {
                Tree left = F();
                Tree right = EPrime();
                return new Tree("E", left, right);
            }
            default -> throw new ParseException("incorrect token while parsing F", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree EPrime() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case IMPLICATION -> {
                lexicalAnalyzer.nextToken();
                Tree tree = E();
                return new Tree("E'", new Tree("->"), tree);
            }
            case END, RPAREN -> {
                return new Tree("E'", new Tree("EPS"));
            }
            default -> throw new ParseException("incorrect token while parsing F", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree F() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case NOT, VAR, LPAREN -> {
                Tree left = T();
                Tree right = FPrime();
                return new Tree("F", left, right);
            }
            default -> throw new ParseException("incorrect token while parsing F", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree FPrime() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case OR -> {
                lexicalAnalyzer.nextToken();
                Tree left = T();
                Tree right = FPrime();
                return new Tree("F'", new Tree("|"), left, right);
            }
            case END, IMPLICATION, RPAREN -> {
                return new Tree("F'", new Tree("EPS"));
            }
            default -> throw new ParseException("incorrect token while parsing FPrime", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree T() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case NOT, VAR, LPAREN -> {
                Tree left = G();
                Tree right = TPrime();
                return new Tree("O", left, right);
            }
            default -> throw new ParseException("incorrect token while parsing O", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree TPrime() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case XOR -> {
                lexicalAnalyzer.nextToken();
                Tree left = G();
                Tree right = TPrime();
                return new Tree("O'", new Tree("^"), left, right);
            }
            case END, OR, IMPLICATION, RPAREN -> {
                return new Tree("O'", new Tree("EPS"));
            }
            default -> throw new ParseException("incorrect token while parsing OPrime", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree G() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case NOT, VAR, LPAREN -> {
                Tree left = N();
                Tree right = GPrime();
                return new Tree("X", left, right);
            }
            default -> throw new ParseException("incorrect token while parsing X", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree GPrime() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case AND -> {
                lexicalAnalyzer.nextToken();
                Tree left = N();
                Tree right = GPrime();
                return new Tree("X'", new Tree("&"), left, right);
            }
            case END, XOR, OR, IMPLICATION, RPAREN -> {
                return new Tree("X'", new Tree("EPS"));
            }
            default -> throw new ParseException("incorrect token while parsing XPrime", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree N() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case NOT -> {
                lexicalAnalyzer.nextToken();
                Tree operand = N();
                return new Tree("A", new Tree("!"), operand);
            }
            case VAR, LPAREN -> {
                Tree operand = P();
                return new Tree("A", operand);
            }
            default -> throw new ParseException("incorrect token while parsing A", lexicalAnalyzer.getCurPos());
        }
    }

    private Tree P() throws ParseException {
        switch (lexicalAnalyzer.getCurToken()) {
            case VAR -> {
                String operand = lexicalAnalyzer.getCurToken().getName();
                lexicalAnalyzer.nextToken();
                return new Tree("P", new Tree(operand));
            }
            case LPAREN -> {
                lexicalAnalyzer.nextToken();
                Tree operand = E();
                if (lexicalAnalyzer.getCurToken() != Token.RPAREN) {
                    throw new ParseException(") expected at position", lexicalAnalyzer.getCurPos() - 1);
                }
                lexicalAnalyzer.nextToken();
                return new Tree("P", new Tree("("), operand, new Tree(")"));
            }
            default -> throw new ParseException("incorrect token while parsing P", lexicalAnalyzer.getCurPos());
        }
    }
}

