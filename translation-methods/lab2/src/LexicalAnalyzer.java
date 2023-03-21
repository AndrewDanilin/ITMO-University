import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class LexicalAnalyzer {
    private final InputStream is;
    int curChar;
    int curPos;
    private Token curToken;

    public LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    public void nextToken() throws ParseException {
        skipWhitespaces();
        switch (curChar) {
            case '!' -> {
                nextChar();
                curToken = Token.NOT;
            }
            case '&' -> {
                nextChar();
                curToken = Token.AND;
            }
            case '^' -> {
                nextChar();
                curToken = Token.XOR;
            }
            case '|' -> {
                nextChar();
                curToken = Token.OR;
            }
            case '(' -> {
                nextChar();
                curToken = Token.LPAREN;
            }
            case ')' -> {
                nextChar();
                curToken = Token.RPAREN;
            }
            case -1 -> {
                curToken = Token.END;
            }
            default -> {
                if (Character.isAlphabetic(curChar)) {
                    curToken = Token.VAR;
                    nextChar();
                } else if (curChar == '-') {
                    nextChar();
                    if (curChar == '>') {
                        nextChar();
                        curToken = Token.IMPLICATION;
                    } else {
                        throw new ParseException("Unknown token at position", curPos);
                    }
                } else {
                    throw new ParseException("Unknown token at position", curPos);
                }
            }
        }
    }

    public void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), curPos);
        }
    }

    public int getCurPos() {
        return curPos;
    }

    public Token getCurToken() {
        return curToken;
    }

    private void skipWhitespaces() throws ParseException {
        while (isBlank()) {
            nextChar();
        }
    }

    private boolean isBlank() {
            return curChar == ' ' || curChar == '\r' || curChar == '\n' || curChar == '\t';
    }
}
