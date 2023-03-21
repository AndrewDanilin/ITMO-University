package gen;// Generated from java-escape by ANTLR 4.11.1



import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PrefixExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MINUS=2, MUL=3, DIV=4, NOT=5, XOR=6, AND=7, OR=8, GE=9, GT=10, 
		LE=11, LT=12, EQ=13, NEQ=14, IF=15, PRINT=16, BOTH=17, VAR=18, NUMBER=19, 
		ASSIGNMENT=20, NEWLINE=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PLUS", "MINUS", "MUL", "DIV", "NOT", "XOR", "AND", "OR", "GE", "GT", 
			"LE", "LT", "EQ", "NEQ", "IF", "PRINT", "BOTH", "VAR", "NUMBER", "ASSIGNMENT", 
			"NEWLINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'!'", "'^'", "'&'", "'|'", "'>='", 
			"'>'", "'<='", "'<'", "'=='", "'!='", "'if'", "'print'", "'both'", null, 
			null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PLUS", "MINUS", "MUL", "DIV", "NOT", "XOR", "AND", "OR", "GE", 
			"GT", "LE", "LT", "EQ", "NEQ", "IF", "PRINT", "BOTH", "VAR", "NUMBER", 
			"ASSIGNMENT", "NEWLINE"
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


	public PrefixExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrefixExpr.g4"; }

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
		"\u0004\u0000\u0015i\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0004\u0012]\b\u0012\u000b\u0012\f\u0012^\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0004\u0014d\b\u0014\u000b\u0014\f\u0014"+
		"e\u0001\u0014\u0001\u0014\u0000\u0000\u0015\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015\u0001\u0000\u0003\u0001\u0000az\u0001\u0000"+
		"09\u0003\u0000\t\n\r\r  j\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"+
		")\u0001\u0000\u0000\u0000\u0001+\u0001\u0000\u0000\u0000\u0003-\u0001"+
		"\u0000\u0000\u0000\u0005/\u0001\u0000\u0000\u0000\u00071\u0001\u0000\u0000"+
		"\u0000\t3\u0001\u0000\u0000\u0000\u000b5\u0001\u0000\u0000\u0000\r7\u0001"+
		"\u0000\u0000\u0000\u000f9\u0001\u0000\u0000\u0000\u0011;\u0001\u0000\u0000"+
		"\u0000\u0013>\u0001\u0000\u0000\u0000\u0015@\u0001\u0000\u0000\u0000\u0017"+
		"C\u0001\u0000\u0000\u0000\u0019E\u0001\u0000\u0000\u0000\u001bH\u0001"+
		"\u0000\u0000\u0000\u001dK\u0001\u0000\u0000\u0000\u001fN\u0001\u0000\u0000"+
		"\u0000!T\u0001\u0000\u0000\u0000#Y\u0001\u0000\u0000\u0000%\\\u0001\u0000"+
		"\u0000\u0000\'`\u0001\u0000\u0000\u0000)c\u0001\u0000\u0000\u0000+,\u0005"+
		"+\u0000\u0000,\u0002\u0001\u0000\u0000\u0000-.\u0005-\u0000\u0000.\u0004"+
		"\u0001\u0000\u0000\u0000/0\u0005*\u0000\u00000\u0006\u0001\u0000\u0000"+
		"\u000012\u0005/\u0000\u00002\b\u0001\u0000\u0000\u000034\u0005!\u0000"+
		"\u00004\n\u0001\u0000\u0000\u000056\u0005^\u0000\u00006\f\u0001\u0000"+
		"\u0000\u000078\u0005&\u0000\u00008\u000e\u0001\u0000\u0000\u00009:\u0005"+
		"|\u0000\u0000:\u0010\u0001\u0000\u0000\u0000;<\u0005>\u0000\u0000<=\u0005"+
		"=\u0000\u0000=\u0012\u0001\u0000\u0000\u0000>?\u0005>\u0000\u0000?\u0014"+
		"\u0001\u0000\u0000\u0000@A\u0005<\u0000\u0000AB\u0005=\u0000\u0000B\u0016"+
		"\u0001\u0000\u0000\u0000CD\u0005<\u0000\u0000D\u0018\u0001\u0000\u0000"+
		"\u0000EF\u0005=\u0000\u0000FG\u0005=\u0000\u0000G\u001a\u0001\u0000\u0000"+
		"\u0000HI\u0005!\u0000\u0000IJ\u0005=\u0000\u0000J\u001c\u0001\u0000\u0000"+
		"\u0000KL\u0005i\u0000\u0000LM\u0005f\u0000\u0000M\u001e\u0001\u0000\u0000"+
		"\u0000NO\u0005p\u0000\u0000OP\u0005r\u0000\u0000PQ\u0005i\u0000\u0000"+
		"QR\u0005n\u0000\u0000RS\u0005t\u0000\u0000S \u0001\u0000\u0000\u0000T"+
		"U\u0005b\u0000\u0000UV\u0005o\u0000\u0000VW\u0005t\u0000\u0000WX\u0005"+
		"h\u0000\u0000X\"\u0001\u0000\u0000\u0000YZ\u0007\u0000\u0000\u0000Z$\u0001"+
		"\u0000\u0000\u0000[]\u0007\u0001\u0000\u0000\\[\u0001\u0000\u0000\u0000"+
		"]^\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_&\u0001\u0000\u0000\u0000`a\u0005=\u0000\u0000a(\u0001\u0000\u0000"+
		"\u0000bd\u0007\u0002\u0000\u0000cb\u0001\u0000\u0000\u0000de\u0001\u0000"+
		"\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001"+
		"\u0000\u0000\u0000gh\u0006\u0014\u0000\u0000h*\u0001\u0000\u0000\u0000"+
		"\u0003\u0000^e\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}