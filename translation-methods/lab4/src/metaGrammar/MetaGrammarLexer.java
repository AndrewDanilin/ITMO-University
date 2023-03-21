package metaGrammar;// Generated from java-escape by ANTLR 4.11.1

    import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
    import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MetaGrammarLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "OPEN_ANGLE_BRACKET", "CLOSE_ANGLE_BRACKET", "COMMA", "SEMICOLON", 
			"PRODUCTION_SEPARATOR", "OPEN_BRACE", "CLOSE_BRACE", "OPEN_SQUARE_BRACKET", 
			"CLOSE_SQUARE_BRACKET", "RETURNS", "GRAMMAR", "EPS", "ARROW", "COLON", 
			"CODE", "INHERITED_ATTRIBUTES", "SYNTHESIZED_ATTRIBUTES", "WORD_WITH_ALL_CAPITALIZED", 
			"WORD_START_WITH_CAPITALIZED", "WORD_START_WITH_SMALL", "QUOTED_STRING", 
			"REGEXP"
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


	public MetaGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "metaGrammar/MetaGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0017\u00a9\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0004\u0000"+
		"1\b\u0000\u000b\u0000\f\u00002\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0005\u000fd\b\u000f\n\u000f\f\u000fg\t\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0005\u0010m\b\u0010\n\u0010"+
		"\f\u0010p\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0005"+
		"\u0011v\b\u0011\n\u0011\f\u0011y\t\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0004\u0012~\b\u0012\u000b\u0012\f\u0012\u007f\u0001\u0013\u0004"+
		"\u0013\u0083\b\u0013\u000b\u0013\f\u0013\u0084\u0001\u0013\u0005\u0013"+
		"\u0088\b\u0013\n\u0013\f\u0013\u008b\t\u0013\u0001\u0014\u0004\u0014\u008e"+
		"\b\u0014\u000b\u0014\f\u0014\u008f\u0001\u0014\u0005\u0014\u0093\b\u0014"+
		"\n\u0014\f\u0014\u0096\t\u0014\u0001\u0015\u0001\u0015\u0005\u0015\u009a"+
		"\b\u0015\n\u0015\f\u0015\u009d\t\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u00a3\b\u0016\n\u0016\f\u0016\u00a6\t\u0016\u0001"+
		"\u0016\u0001\u0016\u0005enw\u009b\u00a4\u0000\u0017\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010"+
		"!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017\u0001\u0000\u0004\u0003"+
		"\u0000\t\n\r\r  \u0001\u0000AZ\u0002\u0000AZaz\u0001\u0000az\u00b3\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u00010"+
		"\u0001\u0000\u0000\u0000\u00036\u0001\u0000\u0000\u0000\u00058\u0001\u0000"+
		"\u0000\u0000\u0007:\u0001\u0000\u0000\u0000\t<\u0001\u0000\u0000\u0000"+
		"\u000b>\u0001\u0000\u0000\u0000\r@\u0001\u0000\u0000\u0000\u000fB\u0001"+
		"\u0000\u0000\u0000\u0011D\u0001\u0000\u0000\u0000\u0013F\u0001\u0000\u0000"+
		"\u0000\u0015H\u0001\u0000\u0000\u0000\u0017P\u0001\u0000\u0000\u0000\u0019"+
		"X\u0001\u0000\u0000\u0000\u001b\\\u0001\u0000\u0000\u0000\u001d_\u0001"+
		"\u0000\u0000\u0000\u001fa\u0001\u0000\u0000\u0000!j\u0001\u0000\u0000"+
		"\u0000#s\u0001\u0000\u0000\u0000%}\u0001\u0000\u0000\u0000\'\u0082\u0001"+
		"\u0000\u0000\u0000)\u008d\u0001\u0000\u0000\u0000+\u0097\u0001\u0000\u0000"+
		"\u0000-\u00a0\u0001\u0000\u0000\u0000/1\u0007\u0000\u0000\u00000/\u0001"+
		"\u0000\u0000\u000012\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u0000"+
		"23\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0006\u0000\u0000"+
		"\u00005\u0002\u0001\u0000\u0000\u000067\u0005<\u0000\u00007\u0004\u0001"+
		"\u0000\u0000\u000089\u0005>\u0000\u00009\u0006\u0001\u0000\u0000\u0000"+
		":;\u0005,\u0000\u0000;\b\u0001\u0000\u0000\u0000<=\u0005;\u0000\u0000"+
		"=\n\u0001\u0000\u0000\u0000>?\u0005|\u0000\u0000?\f\u0001\u0000\u0000"+
		"\u0000@A\u0005{\u0000\u0000A\u000e\u0001\u0000\u0000\u0000BC\u0005}\u0000"+
		"\u0000C\u0010\u0001\u0000\u0000\u0000DE\u0005[\u0000\u0000E\u0012\u0001"+
		"\u0000\u0000\u0000FG\u0005]\u0000\u0000G\u0014\u0001\u0000\u0000\u0000"+
		"HI\u0005r\u0000\u0000IJ\u0005e\u0000\u0000JK\u0005t\u0000\u0000KL\u0005"+
		"u\u0000\u0000LM\u0005r\u0000\u0000MN\u0005n\u0000\u0000NO\u0005s\u0000"+
		"\u0000O\u0016\u0001\u0000\u0000\u0000PQ\u0005g\u0000\u0000QR\u0005r\u0000"+
		"\u0000RS\u0005a\u0000\u0000ST\u0005m\u0000\u0000TU\u0005m\u0000\u0000"+
		"UV\u0005a\u0000\u0000VW\u0005r\u0000\u0000W\u0018\u0001\u0000\u0000\u0000"+
		"XY\u0005e\u0000\u0000YZ\u0005p\u0000\u0000Z[\u0005s\u0000\u0000[\u001a"+
		"\u0001\u0000\u0000\u0000\\]\u0005-\u0000\u0000]^\u0005>\u0000\u0000^\u001c"+
		"\u0001\u0000\u0000\u0000_`\u0005:\u0000\u0000`\u001e\u0001\u0000\u0000"+
		"\u0000ae\u0003\r\u0006\u0000bd\t\u0000\u0000\u0000cb\u0001\u0000\u0000"+
		"\u0000dg\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000ec\u0001\u0000"+
		"\u0000\u0000fh\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000hi\u0003"+
		"\u000f\u0007\u0000i \u0001\u0000\u0000\u0000jn\u0003\u0003\u0001\u0000"+
		"km\t\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000"+
		"\u0000pn\u0001\u0000\u0000\u0000qr\u0003\u0005\u0002\u0000r\"\u0001\u0000"+
		"\u0000\u0000sw\u0003\u0011\b\u0000tv\t\u0000\u0000\u0000ut\u0001\u0000"+
		"\u0000\u0000vy\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000wu\u0001"+
		"\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000"+
		"z{\u0003\u0013\t\u0000{$\u0001\u0000\u0000\u0000|~\u0007\u0001\u0000\u0000"+
		"}|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080&\u0001\u0000"+
		"\u0000\u0000\u0081\u0083\u0007\u0001\u0000\u0000\u0082\u0081\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0089\u0001\u0000"+
		"\u0000\u0000\u0086\u0088\u0007\u0002\u0000\u0000\u0087\u0086\u0001\u0000"+
		"\u0000\u0000\u0088\u008b\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a(\u0001\u0000\u0000"+
		"\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u008e\u0007\u0003\u0000"+
		"\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000"+
		"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0094\u0001\u0000\u0000\u0000\u0091\u0093\u0007\u0002\u0000"+
		"\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000"+
		"\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000"+
		"\u0000\u0095*\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000"+
		"\u0097\u009b\u0005\'\u0000\u0000\u0098\u009a\t\u0000\u0000\u0000\u0099"+
		"\u0098\u0001\u0000\u0000\u0000\u009a\u009d\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009c"+
		"\u009e\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005\'\u0000\u0000\u009f,\u0001\u0000\u0000\u0000\u00a0\u00a4"+
		"\u0005\"\u0000\u0000\u00a1\u00a3\t\u0000\u0000\u0000\u00a2\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005"+
		"\"\u0000\u0000\u00a8.\u0001\u0000\u0000\u0000\f\u00002enw\u007f\u0084"+
		"\u0089\u008f\u0094\u009b\u00a4\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}