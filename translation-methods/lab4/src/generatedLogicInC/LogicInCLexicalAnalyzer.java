package generatedLogicInC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class LogicInCLexicalAnalyzer {

    List<Pattern> patterns = Arrays.asList(
		Pattern.compile("!"),
		Pattern.compile("\\|"),
		Pattern.compile("&"),
		Pattern.compile("\\^"),
		Pattern.compile("\\("),
		Pattern.compile("\\)"),
		Pattern.compile("[a-zA-Z]+")
    );
    List<LogicInCToken.TypeOfToken> tokens = Arrays.asList(
		LogicInCToken.TypeOfToken.NOT,
		LogicInCToken.TypeOfToken.OR,
		LogicInCToken.TypeOfToken.AND,
		LogicInCToken.TypeOfToken.XOR,
		LogicInCToken.TypeOfToken.OPEN,
		LogicInCToken.TypeOfToken.CLOSE,
		LogicInCToken.TypeOfToken.VAR
    );
    private final Pattern whitespacesPattern = Pattern.compile("[ \r\n\t]+");
    private final String input;
    private final int n;
    private final Matcher matcher;
    private LogicInCToken curToken;
    private int previousMatcherEnd;
    private int currentMatcherEnd;

    public LogicInCLexicalAnalyzer(String input) throws ParseException {
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
                this.curToken = new LogicInCToken(input.substring(previousMatcherEnd, currentMatcherEnd), tokens.get(i));
                return;
            }
        }
        if (currentMatcherEnd != n) {
            throw new ParseException("Incorrect token at position: ", currentMatcherEnd);
        } else {
            this.curToken = new LogicInCToken("$", LogicInCToken.TypeOfToken.END);
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

    public LogicInCToken getCurToken() {
        return this.curToken;
    }


}