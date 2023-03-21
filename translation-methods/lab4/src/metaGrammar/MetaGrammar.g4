grammar MetaGrammar;

@header {
    import grammar.NonTerminal;
    import grammar.Production;
    import grammar.Rule;
    import grammar.Terminal;
    import grammar.Grammar;
    import grammar.BroadcastSymbol;
}

metaGrammar returns [Grammar grammar]
    @init {
        ArrayList<NonTerminal> nonTerminals = new ArrayList<>();
        ArrayList<Terminal> terminals = new ArrayList<>();
        ArrayList<Rule> grammarRules = new ArrayList<>();
    }
    : nameOfGrammar startNonTerminal rules[terminals, nonTerminals, grammarRules] EOF {
        $grammar = new Grammar(
            $nameOfGrammar.text,
            nonTerminals,
            terminals,
            grammarRules,
            $startNonTerminal.nonTerminal
        );
    }
    ;

startNonTerminal returns [NonTerminal nonTerminal]: COLON WORD_START_WITH_SMALL SEMICOLON {
    $nonTerminal = new NonTerminal($WORD_START_WITH_SMALL.text, null, null);
};

nameOfGrammar returns [String text]: GRAMMAR WORD_START_WITH_CAPITALIZED SEMICOLON {
    $text = $WORD_START_WITH_CAPITALIZED.text;
};

rules[ArrayList<Terminal> terminals, ArrayList<NonTerminal> nonTerminals, ArrayList<Rule> grammarRules]
    : (rule[terminals, nonTerminals, grammarRules])* ;

rule [ArrayList<Terminal> terminals, ArrayList<NonTerminal> nonTerminals, ArrayList<Rule> grammarRules]
    : nonTerminalRule {
        $nonTerminals.add($nonTerminalRule.nonTerminal);
        $grammarRules.add($nonTerminalRule.grammarRule);
    }
    | terminalRule {
        $terminals.add($terminalRule.terminal);
    }
    ;

nonTerminalRule returns [NonTerminal nonTerminal, Rule grammarRule]
    : WORD_START_WITH_SMALL inheritedAttributes     synthesizedAttributes ARROW productions SEMICOLON {
        $nonTerminal = new NonTerminal(
            $WORD_START_WITH_SMALL.text,
            $inheritedAttributes.text,
            $synthesizedAttributes.text
        );
        $grammarRule = new Rule(
            $nonTerminal,
            $productions.prods
        );
    }
    ;

inheritedAttributes returns [String text]
    : INHERITED_ATTRIBUTES {
        $text = $INHERITED_ATTRIBUTES.text.substring(1, $INHERITED_ATTRIBUTES.text.length() - 1);
    }
    | {
        $text = null;
    }
    ;

synthesizedAttributes returns [String text]
    : SYNTHESIZED_ATTRIBUTES {
        $text = $SYNTHESIZED_ATTRIBUTES.text.substring(1, $SYNTHESIZED_ATTRIBUTES.text.length() - 1);
    }
    | {
        $text = null;
    }
    ;

terminalRule returns [Terminal terminal]
    : WORD_WITH_ALL_CAPITALIZED ARROW string SEMICOLON {
        $terminal = new Terminal($WORD_WITH_ALL_CAPITALIZED.text, $string.text);
    }
    ;

string returns [String text]
    : QUOTED_STRING {
        $text = $QUOTED_STRING.text;
    }
    | REGEXP {
        $text = $REGEXP.text;
    }
    ;

productions returns [ArrayList<Production> prods]
    @init {
        ArrayList<Production> listOfProds = new ArrayList<>();
    }
    : (production (PRODUCTION_SEPARATOR | ) { listOfProds.add($production.prod); })+ {
        $prods = listOfProds;
    }
    |
    ;

production returns [Production prod]
    @init {
        List<NonTerminal> nonTerminals = new ArrayList<>();
        List<Terminal> terminals = new ArrayList<>();
        List<BroadcastSymbol> broadcastSymbols = new ArrayList<>();
        List<Integer> type = new ArrayList<>();
        List<Integer> order = new ArrayList<>();
    }
    : (alpha {
        switch ($alpha.type) {
                case 0 -> {
                    order.add(nonTerminals.size());
                    nonTerminals.add($alpha.nonTerminal);
                    type.add(0);
                }
                case 1 -> {
                    order.add(terminals.size());
                    terminals.add($alpha.terminal);
                    type.add(1);
                }
                case 2 -> {
                    order.add(broadcastSymbols.size());
                    broadcastSymbols.add($alpha.brSymbol);
                    type.add(2);
                }
            };
    } )+ {
        $prod = new Production(nonTerminals, terminals, broadcastSymbols, order, type);
    }
    ;

broadcastSymbol returns [String text] : CODE {
    $text = $CODE.text.substring(1, $CODE.text.length() - 1).trim();
};

alpha returns [int type, NonTerminal nonTerminal, Terminal terminal, BroadcastSymbol brSymbol]
    : WORD_WITH_ALL_CAPITALIZED {
        $type = 1;
        $terminal = new Terminal($WORD_WITH_ALL_CAPITALIZED.text, null);
    }
    | WORD_START_WITH_SMALL inheritedAttributes {
        $type = 0;
        $nonTerminal = new NonTerminal(
            $WORD_START_WITH_SMALL.text,
            $inheritedAttributes.text,
            null
        );
    }
    | broadcastSymbol {
        $type = 2;
        $brSymbol = new BroadcastSymbol($broadcastSymbol.text);
    }
    | EPS {
        $type = 1;
        $terminal = new Terminal($EPS.text, "eps");
    }
    ;

WS : [ \t\r\n]+ -> skip;

OPEN_ANGLE_BRACKET   : '<' ;
CLOSE_ANGLE_BRACKET  : '>' ;
COMMA                : ',' ;
SEMICOLON            : ';' ;
PRODUCTION_SEPARATOR : '|' ;
OPEN_BRACE           : '{' ;
CLOSE_BRACE          : '}' ;
OPEN_SQUARE_BRACKET  : '[' ;
CLOSE_SQUARE_BRACKET : ']' ;
RETURNS              : 'returns' ;
GRAMMAR              : 'grammar' ;
EPS                  : 'eps' ;
ARROW                : '->' ;
COLON                : ':' ;

CODE : OPEN_BRACE .*? CLOSE_BRACE ;
INHERITED_ATTRIBUTES: OPEN_ANGLE_BRACKET .*? CLOSE_ANGLE_BRACKET ;
SYNTHESIZED_ATTRIBUTES: OPEN_SQUARE_BRACKET .*? CLOSE_SQUARE_BRACKET ;

WORD_WITH_ALL_CAPITALIZED   : [A-Z]+ ;
WORD_START_WITH_CAPITALIZED : [A-Z]+[a-zA-Z]* ;
WORD_START_WITH_SMALL       : [a-z]+[a-zA-Z]* ;
QUOTED_STRING               : '\'' .*? '\'' ;
REGEXP                      : '"' .*? '"' ;
