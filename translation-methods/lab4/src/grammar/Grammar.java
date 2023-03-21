package grammar;

import java.util.ArrayList;

public record Grammar(
        String nameOfGrammar,
        ArrayList<NonTerminal> nonTerminals,
        ArrayList<Terminal> terminals,
        ArrayList<Rule> rules,
        NonTerminal startNonTerminal
) { }
