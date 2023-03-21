package generatedArithmetic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class ArithmeticLexicalAnalyzer {

    List<Pattern> patterns = Arrays.asList(
		Pattern.compile("\\*\\*"),
		Pattern.compile("/"),
		Pattern.compile("\\*"),
		Pattern.compile("\\+"),
		Pattern.compile("-"),
		Pattern.compile("[0-9]+"),
		Pattern.compile("\\("),
		Pattern.compile("\\)")
    );
    List<ArithmeticToken.TypeOfToken> tokens = Arrays.asList(
		ArithmeticToken.TypeOfToken.POW,
		ArithmeticToken.TypeOfToken.DIV,
		ArithmeticToken.TypeOfToken.MUL,
		ArithmeticToken.TypeOfToken.PLUS,
		ArithmeticToken.TypeOfToken.MINUS,
		ArithmeticToken.TypeOfToken.DIGIT,
		ArithmeticToken.TypeOfToken.OPEN,
		ArithmeticToken.TypeOfToken.CLOSE
    );
    private final Pattern whitespacesPattern = Pattern.compile("[ \r\n\t]+");
    private final String input;
    private final int n;
    private final Matcher matcher;
    private ArithmeticToken curToken;
    private int previousMatcherEnd;
    private int currentMatcherEnd;

    public ArithmeticLexicalAnalyzer(String input) throws ParseException {
        this.input = input;
        previousMatcherEnd = 0;
        currentMatcherEnd = 0;
        n = input.length();
        matcher = whitespacesPattern.matcher(input);
    }

    public void nextToken() throws ParseException {
        skipWhitespaces();
        for (int i = 0; i < patterns.size(); i++) {
            matcher.usePattern(patterns.get(i));
            if (matcher.lookingAt()) {
                previousMatcherEnd = currentMatcherEnd;
                currentMatcherEnd = previousMatcherEnd + matcher.end();
                matcher.reset(input.substring(currentMatcherEnd));
                this.curToken = new ArithmeticToken(input.substring(previousMatcherEnd, currentMatcherEnd), tokens.get(i));
                return;
            }
        }
        if (currentMatcherEnd != n) {
            throw new ParseException("Incorrect token at position: ", currentMatcherEnd);
        } else {
            this.curToken = new ArithmeticToken("$", ArithmeticToken.TypeOfToken.END);
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

    public ArithmeticToken getCurToken() {
        return this.curToken;
    }


}