public enum Token {
    NOT("!"),
    AND("&"),
    XOR("^"),
    OR("|"),
    VAR("x"),
    LPAREN("("),
    RPAREN(")"),

    IMPLICATION("->"),
    END("$");

    private final String name;

    Token(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
