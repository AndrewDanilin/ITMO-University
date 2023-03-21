package metaGrammar;// Generated from java-escape by ANTLR 4.11.1

    import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MetaGrammarParser}.
 */
public interface MetaGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#metaGrammar}.
	 * @param ctx the parse tree
	 */
	void enterMetaGrammar(MetaGrammarParser.MetaGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#metaGrammar}.
	 * @param ctx the parse tree
	 */
	void exitMetaGrammar(MetaGrammarParser.MetaGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#startNonTerminal}.
	 * @param ctx the parse tree
	 */
	void enterStartNonTerminal(MetaGrammarParser.StartNonTerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#startNonTerminal}.
	 * @param ctx the parse tree
	 */
	void exitStartNonTerminal(MetaGrammarParser.StartNonTerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#nameOfGrammar}.
	 * @param ctx the parse tree
	 */
	void enterNameOfGrammar(MetaGrammarParser.NameOfGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#nameOfGrammar}.
	 * @param ctx the parse tree
	 */
	void exitNameOfGrammar(MetaGrammarParser.NameOfGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(MetaGrammarParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(MetaGrammarParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#rule}.
	 * @param ctx the parse tree
	 */
	void enterRule(MetaGrammarParser.RuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#rule}.
	 * @param ctx the parse tree
	 */
	void exitRule(MetaGrammarParser.RuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#nonTerminalRule}.
	 * @param ctx the parse tree
	 */
	void enterNonTerminalRule(MetaGrammarParser.NonTerminalRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#nonTerminalRule}.
	 * @param ctx the parse tree
	 */
	void exitNonTerminalRule(MetaGrammarParser.NonTerminalRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#inheritedAttributes}.
	 * @param ctx the parse tree
	 */
	void enterInheritedAttributes(MetaGrammarParser.InheritedAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#inheritedAttributes}.
	 * @param ctx the parse tree
	 */
	void exitInheritedAttributes(MetaGrammarParser.InheritedAttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#synthesizedAttributes}.
	 * @param ctx the parse tree
	 */
	void enterSynthesizedAttributes(MetaGrammarParser.SynthesizedAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#synthesizedAttributes}.
	 * @param ctx the parse tree
	 */
	void exitSynthesizedAttributes(MetaGrammarParser.SynthesizedAttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#terminalRule}.
	 * @param ctx the parse tree
	 */
	void enterTerminalRule(MetaGrammarParser.TerminalRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#terminalRule}.
	 * @param ctx the parse tree
	 */
	void exitTerminalRule(MetaGrammarParser.TerminalRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(MetaGrammarParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(MetaGrammarParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#productions}.
	 * @param ctx the parse tree
	 */
	void enterProductions(MetaGrammarParser.ProductionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#productions}.
	 * @param ctx the parse tree
	 */
	void exitProductions(MetaGrammarParser.ProductionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#production}.
	 * @param ctx the parse tree
	 */
	void enterProduction(MetaGrammarParser.ProductionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#production}.
	 * @param ctx the parse tree
	 */
	void exitProduction(MetaGrammarParser.ProductionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#broadcastSymbol}.
	 * @param ctx the parse tree
	 */
	void enterBroadcastSymbol(MetaGrammarParser.BroadcastSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#broadcastSymbol}.
	 * @param ctx the parse tree
	 */
	void exitBroadcastSymbol(MetaGrammarParser.BroadcastSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MetaGrammarParser#alpha}.
	 * @param ctx the parse tree
	 */
	void enterAlpha(MetaGrammarParser.AlphaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetaGrammarParser#alpha}.
	 * @param ctx the parse tree
	 */
	void exitAlpha(MetaGrammarParser.AlphaContext ctx);
}