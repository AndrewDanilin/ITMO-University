package grammar;

import java.util.ArrayList;

public record Rule(NonTerminal nonTerminal, ArrayList<Production> productions) {

    @Override
    public String toString() {
        return "Rule{" +
                "nonTerminal=" + nonTerminal +
                ", productions=" + productions +
                '}';
    }
}
