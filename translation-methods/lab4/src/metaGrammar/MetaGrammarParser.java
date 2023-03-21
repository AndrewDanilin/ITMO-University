package metaGrammar;// Generated from java-escape by ANTLR 4.11.1

    import grammar.NonTerminal;
    import grammar.Production;
    import grammar.Rule;
    import grammar.Terminal;
    import grammar.Grammar;
    import grammar.BroadcastSymbol;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
    import org.antlr.v4.runtime.tree.*;
import java.util.List;
    import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MetaGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, OPEN_ANGLE_BRACKET=2, CLOSE_ANGLE_BRACKET=3, COMMA=4, SEMICOLON=5, 
		PRODUCTION_SEPARATOR=6, OPEN_BRACE=7, CLOSE_BRACE=8, OPEN_SQUARE_BRACKET=9, 
		CLOSE_SQUARE_BRACKET=10, RETURNS=11, GRAMMAR=12, EPS=13, ARROW=14, COLON=15, 
		CODE=16, INHERITED_ATTRIBUTES=17, SYNTHESIZED_ATTRIBUTES=18, WORD_WITH_ALL_CAPITALIZED=19, 
		WORD_START_WITH_CAPITALIZED=20, WORD_START_WITH_SMALL=21, QUOTED_STRING=22, 
		REGEXP=23;
	public static final int
		RULE_metaGrammar = 0, RULE_startNonTerminal = 1, RULE_nameOfGrammar = 2, 
		RULE_rules = 3, RULE_rule = 4, RULE_nonTerminalRule = 5, RULE_inheritedAttributes = 6, 
		RULE_synthesizedAttributes = 7, RULE_terminalRule = 8, RULE_string = 9, 
		RULE_productions = 10, RULE_production = 11, RULE_broadcastSymbol = 12, 
		RULE_alpha = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"metaGrammar", "startNonTerminal", "nameOfGrammar", "rules", "rule", 
			"nonTerminalRule", "inheritedAttributes", "synthesizedAttributes", "terminalRule", 
			"string", "productions", "production", "broadcastSymbol", "alpha"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'<'", "'>'", "','", "';'", "'|'", "'{'", "'}'", "'['", "']'", 
			"'returns'", "'grammar'", "'eps'", "'->'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "OPEN_ANGLE_BRACKET", "CLOSE_ANGLE_BRACKET", "COMMA", "SEMICOLON", 
			"PRODUCTION_SEPARATOR", "OPEN_BRACE", "CLOSE_BRACE", "OPEN_SQUARE_BRACKET", 
			"CLOSE_SQUARE_BRACKET", "RETURNS", "GRAMMAR", "EPS", "ARROW", "COLON", 
			"CODE", "INHERITED_ATTRIBUTES", "SYNTHESIZED_ATTRIBUTES", "WORD_WITH_ALL_CAPITALIZED", 
			"WORD_START_WITH_CAPITALIZED", "WORD_START_WITH_SMALL", "QUOTED_STRING", 
			"REGEXP"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MetaGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MetaGrammarContext extends ParserRuleContext {
		public Grammar grammar;
		public NameOfGrammarContext nameOfGrammar;
		public StartNonTerminalContext startNonTerminal;
		public NameOfGrammarContext nameOfGrammar() {
			return getRuleContext(NameOfGrammarContext.class,0);
		}
		public StartNonTerminalContext startNonTerminal() {
			return getRuleContext(StartNonTerminalContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MetaGrammarParser.EOF, 0); }
		public MetaGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metaGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterMetaGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitMetaGrammar(this);
		}
	}

	public final MetaGrammarContext metaGrammar() throws RecognitionException {
		MetaGrammarContext _localctx = new MetaGrammarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_metaGrammar);

		        ArrayList<NonTerminal> nonTerminals = new ArrayList<>();
		        ArrayList<Terminal> terminals = new ArrayList<>();
		        ArrayList<Rule> grammarRules = new ArrayList<>();
		    
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			((MetaGrammarContext)_localctx).nameOfGrammar = nameOfGrammar();
			setState(29);
			((MetaGrammarContext)_localctx).startNonTerminal = startNonTerminal();
			setState(30);
			rules(terminals, nonTerminals, grammarRules);
			setState(31);
			match(EOF);

			        ((MetaGrammarContext)_localctx).grammar =  new Grammar(
			            ((MetaGrammarContext)_localctx).nameOfGrammar.text,
			            nonTerminals,
			            terminals,
			            grammarRules,
			            ((MetaGrammarContext)_localctx).startNonTerminal.nonTerminal
			        );
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartNonTerminalContext extends ParserRuleContext {
		public NonTerminal nonTerminal;
		public Token WORD_START_WITH_SMALL;
		public TerminalNode COLON() { return getToken(MetaGrammarParser.COLON, 0); }
		public TerminalNode WORD_START_WITH_SMALL() { return getToken(MetaGrammarParser.WORD_START_WITH_SMALL, 0); }
		public TerminalNode SEMICOLON() { return getToken(MetaGrammarParser.SEMICOLON, 0); }
		public StartNonTerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startNonTerminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterStartNonTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitStartNonTerminal(this);
		}
	}

	public final StartNonTerminalContext startNonTerminal() throws RecognitionException {
		StartNonTerminalContext _localctx = new StartNonTerminalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_startNonTerminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(COLON);
			setState(35);
			((StartNonTerminalContext)_localctx).WORD_START_WITH_SMALL = match(WORD_START_WITH_SMALL);
			setState(36);
			match(SEMICOLON);

			    ((StartNonTerminalContext)_localctx).nonTerminal =  new NonTerminal((((StartNonTerminalContext)_localctx).WORD_START_WITH_SMALL!=null?((StartNonTerminalContext)_localctx).WORD_START_WITH_SMALL.getText():null), null, null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameOfGrammarContext extends ParserRuleContext {
		public String text;
		public Token WORD_START_WITH_CAPITALIZED;
		public TerminalNode GRAMMAR() { return getToken(MetaGrammarParser.GRAMMAR, 0); }
		public TerminalNode WORD_START_WITH_CAPITALIZED() { return getToken(MetaGrammarParser.WORD_START_WITH_CAPITALIZED, 0); }
		public TerminalNode SEMICOLON() { return getToken(MetaGrammarParser.SEMICOLON, 0); }
		public NameOfGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameOfGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterNameOfGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitNameOfGrammar(this);
		}
	}

	public final NameOfGrammarContext nameOfGrammar() throws RecognitionException {
		NameOfGrammarContext _localctx = new NameOfGrammarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_nameOfGrammar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(GRAMMAR);
			setState(40);
			((NameOfGrammarContext)_localctx).WORD_START_WITH_CAPITALIZED = match(WORD_START_WITH_CAPITALIZED);
			setState(41);
			match(SEMICOLON);

			    ((NameOfGrammarContext)_localctx).text =  (((NameOfGrammarContext)_localctx).WORD_START_WITH_CAPITALIZED!=null?((NameOfGrammarContext)_localctx).WORD_START_WITH_CAPITALIZED.getText():null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RulesContext extends ParserRuleContext {
		public ArrayList<Terminal> terminals;
		public ArrayList<NonTerminal> nonTerminals;
		public ArrayList<Rule> grammarRules;
		public List<RuleContext> rule_() {
			return getRuleContexts(RuleContext.class);
		}
		public RuleContext rule_(int i) {
			return getRuleContext(RuleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RulesContext(ParserRuleContext parent, int invokingState, ArrayList<Terminal> terminals, ArrayList<NonTerminal> nonTerminals, ArrayList<Rule> grammarRules) {
			super(parent, invokingState);
			this.terminals = terminals;
			this.nonTerminals = nonTerminals;
			this.grammarRules = grammarRules;
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitRules(this);
		}
	}

	public final RulesContext rules(ArrayList<Terminal> terminals,ArrayList<NonTerminal> nonTerminals,ArrayList<Rule> grammarRules) throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState(), terminals, nonTerminals, grammarRules);
		enterRule(_localctx, 6, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WORD_WITH_ALL_CAPITALIZED || _la==WORD_START_WITH_SMALL) {
				{
				{
				setState(44);
				rule_(terminals, nonTerminals, grammarRules);
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleContext extends ParserRuleContext {
		public ArrayList<Terminal> terminals;
		public ArrayList<NonTerminal> nonTerminals;
		public ArrayList<Rule> grammarRules;
		public NonTerminalRuleContext nonTerminalRule;
		public TerminalRuleContext terminalRule;
		public NonTerminalRuleContext nonTerminalRule() {
			return getRuleContext(NonTerminalRuleContext.class,0);
		}
		public TerminalRuleContext terminalRule() {
			return getRuleContext(TerminalRuleContext.class,0);
		}
		public RuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RuleContext(ParserRuleContext parent, int invokingState, ArrayList<Terminal> terminals, ArrayList<NonTerminal> nonTerminals, ArrayList<Rule> grammarRules) {
			super(parent, invokingState);
			this.terminals = terminals;
			this.nonTerminals = nonTerminals;
			this.grammarRules = grammarRules;
		}
		@Override public int getRuleIndex() { return RULE_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitRule(this);
		}
	}

	public final RuleContext rule_(ArrayList<Terminal> terminals,ArrayList<NonTerminal> nonTerminals,ArrayList<Rule> grammarRules) throws RecognitionException {
		RuleContext _localctx = new RuleContext(_ctx, getState(), terminals, nonTerminals, grammarRules);
		enterRule(_localctx, 8, RULE_rule);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD_START_WITH_SMALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				((RuleContext)_localctx).nonTerminalRule = nonTerminalRule();

				        _localctx.nonTerminals.add(((RuleContext)_localctx).nonTerminalRule.nonTerminal);
				        _localctx.grammarRules.add(((RuleContext)_localctx).nonTerminalRule.grammarRule);
				    
				}
				break;
			case WORD_WITH_ALL_CAPITALIZED:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				((RuleContext)_localctx).terminalRule = terminalRule();

				        _localctx.terminals.add(((RuleContext)_localctx).terminalRule.terminal);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NonTerminalRuleContext extends ParserRuleContext {
		public NonTerminal nonTerminal;
		public Rule grammarRule;
		public Token WORD_START_WITH_SMALL;
		public InheritedAttributesContext inheritedAttributes;
		public SynthesizedAttributesContext synthesizedAttributes;
		public ProductionsContext productions;
		public TerminalNode WORD_START_WITH_SMALL() { return getToken(MetaGrammarParser.WORD_START_WITH_SMALL, 0); }
		public InheritedAttributesContext inheritedAttributes() {
			return getRuleContext(InheritedAttributesContext.class,0);
		}
		public SynthesizedAttributesContext synthesizedAttributes() {
			return getRuleContext(SynthesizedAttributesContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(MetaGrammarParser.ARROW, 0); }
		public ProductionsContext productions() {
			return getRuleContext(ProductionsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MetaGrammarParser.SEMICOLON, 0); }
		public NonTerminalRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonTerminalRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterNonTerminalRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitNonTerminalRule(this);
		}
	}

	public final NonTerminalRuleContext nonTerminalRule() throws RecognitionException {
		NonTerminalRuleContext _localctx = new NonTerminalRuleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nonTerminalRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((NonTerminalRuleContext)_localctx).WORD_START_WITH_SMALL = match(WORD_START_WITH_SMALL);
			setState(59);
			((NonTerminalRuleContext)_localctx).inheritedAttributes = inheritedAttributes();
			setState(60);
			((NonTerminalRuleContext)_localctx).synthesizedAttributes = synthesizedAttributes();
			setState(61);
			match(ARROW);
			setState(62);
			((NonTerminalRuleContext)_localctx).productions = productions();
			setState(63);
			match(SEMICOLON);

			        ((NonTerminalRuleContext)_localctx).nonTerminal =  new NonTerminal(
			            (((NonTerminalRuleContext)_localctx).WORD_START_WITH_SMALL!=null?((NonTerminalRuleContext)_localctx).WORD_START_WITH_SMALL.getText():null),
			            ((NonTerminalRuleContext)_localctx).inheritedAttributes.text,
			            ((NonTerminalRuleContext)_localctx).synthesizedAttributes.text
			        );
			        ((NonTerminalRuleContext)_localctx).grammarRule =  new Rule(
			            _localctx.nonTerminal,
			            ((NonTerminalRuleContext)_localctx).productions.prods
			        );
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InheritedAttributesContext extends ParserRuleContext {
		public String text;
		public Token INHERITED_ATTRIBUTES;
		public TerminalNode INHERITED_ATTRIBUTES() { return getToken(MetaGrammarParser.INHERITED_ATTRIBUTES, 0); }
		public InheritedAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inheritedAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterInheritedAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitInheritedAttributes(this);
		}
	}

	public final InheritedAttributesContext inheritedAttributes() throws RecognitionException {
		InheritedAttributesContext _localctx = new InheritedAttributesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_inheritedAttributes);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INHERITED_ATTRIBUTES:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				((InheritedAttributesContext)_localctx).INHERITED_ATTRIBUTES = match(INHERITED_ATTRIBUTES);

				        ((InheritedAttributesContext)_localctx).text =  (((InheritedAttributesContext)_localctx).INHERITED_ATTRIBUTES!=null?((InheritedAttributesContext)_localctx).INHERITED_ATTRIBUTES.getText():null).substring(1, (((InheritedAttributesContext)_localctx).INHERITED_ATTRIBUTES!=null?((InheritedAttributesContext)_localctx).INHERITED_ATTRIBUTES.getText():null).length() - 1);
				    
				}
				break;
			case SEMICOLON:
			case PRODUCTION_SEPARATOR:
			case EPS:
			case ARROW:
			case CODE:
			case SYNTHESIZED_ATTRIBUTES:
			case WORD_WITH_ALL_CAPITALIZED:
			case WORD_START_WITH_SMALL:
				enterOuterAlt(_localctx, 2);
				{

				        ((InheritedAttributesContext)_localctx).text =  null;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SynthesizedAttributesContext extends ParserRuleContext {
		public String text;
		public Token SYNTHESIZED_ATTRIBUTES;
		public TerminalNode SYNTHESIZED_ATTRIBUTES() { return getToken(MetaGrammarParser.SYNTHESIZED_ATTRIBUTES, 0); }
		public SynthesizedAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synthesizedAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterSynthesizedAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitSynthesizedAttributes(this);
		}
	}

	public final SynthesizedAttributesContext synthesizedAttributes() throws RecognitionException {
		SynthesizedAttributesContext _localctx = new SynthesizedAttributesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_synthesizedAttributes);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYNTHESIZED_ATTRIBUTES:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				((SynthesizedAttributesContext)_localctx).SYNTHESIZED_ATTRIBUTES = match(SYNTHESIZED_ATTRIBUTES);

				        ((SynthesizedAttributesContext)_localctx).text =  (((SynthesizedAttributesContext)_localctx).SYNTHESIZED_ATTRIBUTES!=null?((SynthesizedAttributesContext)_localctx).SYNTHESIZED_ATTRIBUTES.getText():null).substring(1, (((SynthesizedAttributesContext)_localctx).SYNTHESIZED_ATTRIBUTES!=null?((SynthesizedAttributesContext)_localctx).SYNTHESIZED_ATTRIBUTES.getText():null).length() - 1);
				    
				}
				break;
			case ARROW:
				enterOuterAlt(_localctx, 2);
				{

				        ((SynthesizedAttributesContext)_localctx).text =  null;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TerminalRuleContext extends ParserRuleContext {
		public Terminal terminal;
		public Token WORD_WITH_ALL_CAPITALIZED;
		public StringContext string;
		public TerminalNode WORD_WITH_ALL_CAPITALIZED() { return getToken(MetaGrammarParser.WORD_WITH_ALL_CAPITALIZED, 0); }
		public TerminalNode ARROW() { return getToken(MetaGrammarParser.ARROW, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MetaGrammarParser.SEMICOLON, 0); }
		public TerminalRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminalRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterTerminalRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitTerminalRule(this);
		}
	}

	public final TerminalRuleContext terminalRule() throws RecognitionException {
		TerminalRuleContext _localctx = new TerminalRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_terminalRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			((TerminalRuleContext)_localctx).WORD_WITH_ALL_CAPITALIZED = match(WORD_WITH_ALL_CAPITALIZED);
			setState(77);
			match(ARROW);
			setState(78);
			((TerminalRuleContext)_localctx).string = string();
			setState(79);
			match(SEMICOLON);

			        ((TerminalRuleContext)_localctx).terminal =  new Terminal((((TerminalRuleContext)_localctx).WORD_WITH_ALL_CAPITALIZED!=null?((TerminalRuleContext)_localctx).WORD_WITH_ALL_CAPITALIZED.getText():null), ((TerminalRuleContext)_localctx).string.text);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public String text;
		public Token QUOTED_STRING;
		public Token REGEXP;
		public TerminalNode QUOTED_STRING() { return getToken(MetaGrammarParser.QUOTED_STRING, 0); }
		public TerminalNode REGEXP() { return getToken(MetaGrammarParser.REGEXP, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_string);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUOTED_STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				((StringContext)_localctx).QUOTED_STRING = match(QUOTED_STRING);

				        ((StringContext)_localctx).text =  (((StringContext)_localctx).QUOTED_STRING!=null?((StringContext)_localctx).QUOTED_STRING.getText():null);
				    
				}
				break;
			case REGEXP:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((StringContext)_localctx).REGEXP = match(REGEXP);

				        ((StringContext)_localctx).text =  (((StringContext)_localctx).REGEXP!=null?((StringContext)_localctx).REGEXP.getText():null);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProductionsContext extends ParserRuleContext {
		public ArrayList<Production> prods;
		public ProductionContext production;
		public List<ProductionContext> production() {
			return getRuleContexts(ProductionContext.class);
		}
		public ProductionContext production(int i) {
			return getRuleContext(ProductionContext.class,i);
		}
		public List<TerminalNode> PRODUCTION_SEPARATOR() { return getTokens(MetaGrammarParser.PRODUCTION_SEPARATOR); }
		public TerminalNode PRODUCTION_SEPARATOR(int i) {
			return getToken(MetaGrammarParser.PRODUCTION_SEPARATOR, i);
		}
		public ProductionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_productions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterProductions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitProductions(this);
		}
	}

	public final ProductionsContext productions() throws RecognitionException {
		ProductionsContext _localctx = new ProductionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_productions);

		        ArrayList<Production> listOfProds = new ArrayList<>();
		    
		int _la;
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EPS:
			case CODE:
			case WORD_WITH_ALL_CAPITALIZED:
			case WORD_START_WITH_SMALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(88);
					((ProductionsContext)_localctx).production = production();
					setState(91);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case PRODUCTION_SEPARATOR:
						{
						setState(89);
						match(PRODUCTION_SEPARATOR);
						}
						break;
					case SEMICOLON:
					case EPS:
					case CODE:
					case WORD_WITH_ALL_CAPITALIZED:
					case WORD_START_WITH_SMALL:
						{
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					 listOfProds.add(((ProductionsContext)_localctx).production.prod); 
					}
					}
					setState(97); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 2695168L) != 0 );

				        ((ProductionsContext)_localctx).prods =  listOfProds;
				    
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProductionContext extends ParserRuleContext {
		public Production prod;
		public AlphaContext alpha;
		public List<AlphaContext> alpha() {
			return getRuleContexts(AlphaContext.class);
		}
		public AlphaContext alpha(int i) {
			return getRuleContext(AlphaContext.class,i);
		}
		public ProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_production; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitProduction(this);
		}
	}

	public final ProductionContext production() throws RecognitionException {
		ProductionContext _localctx = new ProductionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_production);

		        List<NonTerminal> nonTerminals = new ArrayList<>();
		        List<Terminal> terminals = new ArrayList<>();
		        List<BroadcastSymbol> broadcastSymbols = new ArrayList<>();
		        List<Integer> type = new ArrayList<>();
		        List<Integer> order = new ArrayList<>();
		    
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(107); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(104);
					((ProductionContext)_localctx).alpha = alpha();

					        switch (((ProductionContext)_localctx).alpha.type) {
					                case 0 -> {
					                    order.add(nonTerminals.size());
					                    nonTerminals.add(((ProductionContext)_localctx).alpha.nonTerminal);
					                    type.add(0);
					                }
					                case 1 -> {
					                    order.add(terminals.size());
					                    terminals.add(((ProductionContext)_localctx).alpha.terminal);
					                    type.add(1);
					                }
					                case 2 -> {
					                    order.add(broadcastSymbols.size());
					                    broadcastSymbols.add(((ProductionContext)_localctx).alpha.brSymbol);
					                    type.add(2);
					                }
					            };
					    
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(109); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );

			        ((ProductionContext)_localctx).prod =  new Production(nonTerminals, terminals, broadcastSymbols, order, type);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BroadcastSymbolContext extends ParserRuleContext {
		public String text;
		public Token CODE;
		public TerminalNode CODE() { return getToken(MetaGrammarParser.CODE, 0); }
		public BroadcastSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_broadcastSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterBroadcastSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitBroadcastSymbol(this);
		}
	}

	public final BroadcastSymbolContext broadcastSymbol() throws RecognitionException {
		BroadcastSymbolContext _localctx = new BroadcastSymbolContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_broadcastSymbol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			((BroadcastSymbolContext)_localctx).CODE = match(CODE);

			    ((BroadcastSymbolContext)_localctx).text =  (((BroadcastSymbolContext)_localctx).CODE!=null?((BroadcastSymbolContext)_localctx).CODE.getText():null).substring(1, (((BroadcastSymbolContext)_localctx).CODE!=null?((BroadcastSymbolContext)_localctx).CODE.getText():null).length() - 1).trim();

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlphaContext extends ParserRuleContext {
		public int type;
		public NonTerminal nonTerminal;
		public Terminal terminal;
		public BroadcastSymbol brSymbol;
		public Token WORD_WITH_ALL_CAPITALIZED;
		public Token WORD_START_WITH_SMALL;
		public InheritedAttributesContext inheritedAttributes;
		public BroadcastSymbolContext broadcastSymbol;
		public Token EPS;
		public TerminalNode WORD_WITH_ALL_CAPITALIZED() { return getToken(MetaGrammarParser.WORD_WITH_ALL_CAPITALIZED, 0); }
		public TerminalNode WORD_START_WITH_SMALL() { return getToken(MetaGrammarParser.WORD_START_WITH_SMALL, 0); }
		public InheritedAttributesContext inheritedAttributes() {
			return getRuleContext(InheritedAttributesContext.class,0);
		}
		public BroadcastSymbolContext broadcastSymbol() {
			return getRuleContext(BroadcastSymbolContext.class,0);
		}
		public TerminalNode EPS() { return getToken(MetaGrammarParser.EPS, 0); }
		public AlphaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alpha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).enterAlpha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetaGrammarListener ) ((MetaGrammarListener)listener).exitAlpha(this);
		}
	}

	public final AlphaContext alpha() throws RecognitionException {
		AlphaContext _localctx = new AlphaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_alpha);
		try {
			setState(127);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD_WITH_ALL_CAPITALIZED:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((AlphaContext)_localctx).WORD_WITH_ALL_CAPITALIZED = match(WORD_WITH_ALL_CAPITALIZED);

				        ((AlphaContext)_localctx).type =  1;
				        ((AlphaContext)_localctx).terminal =  new Terminal((((AlphaContext)_localctx).WORD_WITH_ALL_CAPITALIZED!=null?((AlphaContext)_localctx).WORD_WITH_ALL_CAPITALIZED.getText():null), null);
				    
				}
				break;
			case WORD_START_WITH_SMALL:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				((AlphaContext)_localctx).WORD_START_WITH_SMALL = match(WORD_START_WITH_SMALL);
				setState(119);
				((AlphaContext)_localctx).inheritedAttributes = inheritedAttributes();

				        ((AlphaContext)_localctx).type =  0;
				        ((AlphaContext)_localctx).nonTerminal =  new NonTerminal(
				            (((AlphaContext)_localctx).WORD_START_WITH_SMALL!=null?((AlphaContext)_localctx).WORD_START_WITH_SMALL.getText():null),
				            ((AlphaContext)_localctx).inheritedAttributes.text,
				            null
				        );
				    
				}
				break;
			case CODE:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				((AlphaContext)_localctx).broadcastSymbol = broadcastSymbol();

				        ((AlphaContext)_localctx).type =  2;
				        ((AlphaContext)_localctx).brSymbol =  new BroadcastSymbol(((AlphaContext)_localctx).broadcastSymbol.text);
				    
				}
				break;
			case EPS:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				((AlphaContext)_localctx).EPS = match(EPS);

				        ((AlphaContext)_localctx).type =  1;
				        ((AlphaContext)_localctx).terminal =  new Terminal((((AlphaContext)_localctx).EPS!=null?((AlphaContext)_localctx).EPS.getText():null), "eps");
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017\u0082\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0005\u0003.\b\u0003\n\u0003\f\u00031\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u00049\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006F\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007K\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\tW\b\t\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\\\b\n\u0001\n\u0001\n\u0004\n`\b\n\u000b\n\f\na\u0001\n\u0001\n\u0001"+
		"\n\u0003\ng\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000bl\b\u000b"+
		"\u000b\u000b\f\u000bm\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u0080\b\r\u0001\r\u0000\u0000\u000e\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0000"+
		"\u007f\u0000\u001c\u0001\u0000\u0000\u0000\u0002\"\u0001\u0000\u0000\u0000"+
		"\u0004\'\u0001\u0000\u0000\u0000\u0006/\u0001\u0000\u0000\u0000\b8\u0001"+
		"\u0000\u0000\u0000\n:\u0001\u0000\u0000\u0000\fE\u0001\u0000\u0000\u0000"+
		"\u000eJ\u0001\u0000\u0000\u0000\u0010L\u0001\u0000\u0000\u0000\u0012V"+
		"\u0001\u0000\u0000\u0000\u0014f\u0001\u0000\u0000\u0000\u0016k\u0001\u0000"+
		"\u0000\u0000\u0018q\u0001\u0000\u0000\u0000\u001a\u007f\u0001\u0000\u0000"+
		"\u0000\u001c\u001d\u0003\u0004\u0002\u0000\u001d\u001e\u0003\u0002\u0001"+
		"\u0000\u001e\u001f\u0003\u0006\u0003\u0000\u001f \u0005\u0000\u0000\u0001"+
		" !\u0006\u0000\uffff\uffff\u0000!\u0001\u0001\u0000\u0000\u0000\"#\u0005"+
		"\u000f\u0000\u0000#$\u0005\u0015\u0000\u0000$%\u0005\u0005\u0000\u0000"+
		"%&\u0006\u0001\uffff\uffff\u0000&\u0003\u0001\u0000\u0000\u0000\'(\u0005"+
		"\f\u0000\u0000()\u0005\u0014\u0000\u0000)*\u0005\u0005\u0000\u0000*+\u0006"+
		"\u0002\uffff\uffff\u0000+\u0005\u0001\u0000\u0000\u0000,.\u0003\b\u0004"+
		"\u0000-,\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000"+
		"\u0000\u0000/0\u0001\u0000\u0000\u00000\u0007\u0001\u0000\u0000\u0000"+
		"1/\u0001\u0000\u0000\u000023\u0003\n\u0005\u000034\u0006\u0004\uffff\uffff"+
		"\u000049\u0001\u0000\u0000\u000056\u0003\u0010\b\u000067\u0006\u0004\uffff"+
		"\uffff\u000079\u0001\u0000\u0000\u000082\u0001\u0000\u0000\u000085\u0001"+
		"\u0000\u0000\u00009\t\u0001\u0000\u0000\u0000:;\u0005\u0015\u0000\u0000"+
		";<\u0003\f\u0006\u0000<=\u0003\u000e\u0007\u0000=>\u0005\u000e\u0000\u0000"+
		">?\u0003\u0014\n\u0000?@\u0005\u0005\u0000\u0000@A\u0006\u0005\uffff\uffff"+
		"\u0000A\u000b\u0001\u0000\u0000\u0000BC\u0005\u0011\u0000\u0000CF\u0006"+
		"\u0006\uffff\uffff\u0000DF\u0006\u0006\uffff\uffff\u0000EB\u0001\u0000"+
		"\u0000\u0000ED\u0001\u0000\u0000\u0000F\r\u0001\u0000\u0000\u0000GH\u0005"+
		"\u0012\u0000\u0000HK\u0006\u0007\uffff\uffff\u0000IK\u0006\u0007\uffff"+
		"\uffff\u0000JG\u0001\u0000\u0000\u0000JI\u0001\u0000\u0000\u0000K\u000f"+
		"\u0001\u0000\u0000\u0000LM\u0005\u0013\u0000\u0000MN\u0005\u000e\u0000"+
		"\u0000NO\u0003\u0012\t\u0000OP\u0005\u0005\u0000\u0000PQ\u0006\b\uffff"+
		"\uffff\u0000Q\u0011\u0001\u0000\u0000\u0000RS\u0005\u0016\u0000\u0000"+
		"SW\u0006\t\uffff\uffff\u0000TU\u0005\u0017\u0000\u0000UW\u0006\t\uffff"+
		"\uffff\u0000VR\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000W\u0013"+
		"\u0001\u0000\u0000\u0000X[\u0003\u0016\u000b\u0000Y\\\u0005\u0006\u0000"+
		"\u0000Z\\\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[Z\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000]^\u0006\n\uffff\uffff\u0000^`"+
		"\u0001\u0000\u0000\u0000_X\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000"+
		"\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0001\u0000"+
		"\u0000\u0000cd\u0006\n\uffff\uffff\u0000dg\u0001\u0000\u0000\u0000eg\u0001"+
		"\u0000\u0000\u0000f_\u0001\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000"+
		"g\u0015\u0001\u0000\u0000\u0000hi\u0003\u001a\r\u0000ij\u0006\u000b\uffff"+
		"\uffff\u0000jl\u0001\u0000\u0000\u0000kh\u0001\u0000\u0000\u0000lm\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000op\u0006\u000b\uffff\uffff\u0000p\u0017\u0001"+
		"\u0000\u0000\u0000qr\u0005\u0010\u0000\u0000rs\u0006\f\uffff\uffff\u0000"+
		"s\u0019\u0001\u0000\u0000\u0000tu\u0005\u0013\u0000\u0000u\u0080\u0006"+
		"\r\uffff\uffff\u0000vw\u0005\u0015\u0000\u0000wx\u0003\f\u0006\u0000x"+
		"y\u0006\r\uffff\uffff\u0000y\u0080\u0001\u0000\u0000\u0000z{\u0003\u0018"+
		"\f\u0000{|\u0006\r\uffff\uffff\u0000|\u0080\u0001\u0000\u0000\u0000}~"+
		"\u0005\r\u0000\u0000~\u0080\u0006\r\uffff\uffff\u0000\u007ft\u0001\u0000"+
		"\u0000\u0000\u007fv\u0001\u0000\u0000\u0000\u007fz\u0001\u0000\u0000\u0000"+
		"\u007f}\u0001\u0000\u0000\u0000\u0080\u001b\u0001\u0000\u0000\u0000\n"+
		"/8EJV[afm\u007f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}