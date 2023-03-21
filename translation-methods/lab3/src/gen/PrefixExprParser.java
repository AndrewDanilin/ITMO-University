package gen;// Generated from java-escape by ANTLR 4.11.1



import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PrefixExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MINUS=2, MUL=3, DIV=4, NOT=5, XOR=6, AND=7, OR=8, GE=9, GT=10, 
		LE=11, LT=12, EQ=13, NEQ=14, IF=15, PRINT=16, BOTH=17, VAR=18, NUMBER=19, 
		ASSIGNMENT=20, NEWLINE=21;
	public static final int
		RULE_code = 0, RULE_blocksOfCode = 1, RULE_blockOfCode = 2, RULE_instruction = 3, 
		RULE_blockIf = 4, RULE_blockIfContinue = 5, RULE_expr = 6, RULE_logicalExpr = 7, 
		RULE_logicalBinaryOp = 8, RULE_logicalUnaryOp = 9, RULE_comparison = 10, 
		RULE_comparisonOp = 11, RULE_arithmeticExpr = 12, RULE_arithmeticBinaryOp = 13, 
		RULE_arithmeticUnaryOp = 14, RULE_print = 15, RULE_printOperands = 16, 
		RULE_assignment = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"code", "blocksOfCode", "blockOfCode", "instruction", "blockIf", "blockIfContinue", 
			"expr", "logicalExpr", "logicalBinaryOp", "logicalUnaryOp", "comparison", 
			"comparisonOp", "arithmeticExpr", "arithmeticBinaryOp", "arithmeticUnaryOp", 
			"print", "printOperands", "assignment"
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

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrefixExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CodeContext extends ParserRuleContext {
		public String str;
		public BlocksOfCodeContext blocksOfCode;
		public BlocksOfCodeContext blocksOfCode() {
			return getRuleContext(BlocksOfCodeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PrefixExprParser.EOF, 0); }
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_code);

		        StringBuilder sb = new StringBuilder("def main():\n");
		    
		try {
			setState(40);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case PRINT:
			case BOTH:
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				((CodeContext)_localctx).blocksOfCode = blocksOfCode(1);

				        sb.append(((CodeContext)_localctx).blocksOfCode.str);
				    
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);

			        sb.append("\nmain()");
			        ((CodeContext)_localctx).str =  sb.toString();
			    
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
	public static class BlocksOfCodeContext extends ParserRuleContext {
		public int tabs;
		public String str;
		public BlockOfCodeContext blockOfCode;
		public BlocksOfCodeContext blocksOfCode;
		public BlockOfCodeContext blockOfCode() {
			return getRuleContext(BlockOfCodeContext.class,0);
		}
		public TerminalNode BOTH() { return getToken(PrefixExprParser.BOTH, 0); }
		public BlocksOfCodeContext blocksOfCode() {
			return getRuleContext(BlocksOfCodeContext.class,0);
		}
		public BlocksOfCodeContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlocksOfCodeContext(ParserRuleContext parent, int invokingState, int tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_blocksOfCode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterBlocksOfCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitBlocksOfCode(this);
		}
	}

	public final BlocksOfCodeContext blocksOfCode(int tabs) throws RecognitionException {
		BlocksOfCodeContext _localctx = new BlocksOfCodeContext(_ctx, getState(), tabs);
		enterRule(_localctx, 2, RULE_blocksOfCode);

		        StringBuilder sb = new StringBuilder();
		    
		try {
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case PRINT:
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				((BlocksOfCodeContext)_localctx).blockOfCode = blockOfCode(_localctx.tabs);

				        sb.append(((BlocksOfCodeContext)_localctx).blockOfCode.str);
				    
				}
				break;
			case BOTH:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				match(BOTH);
				setState(46);
				((BlocksOfCodeContext)_localctx).blockOfCode = blockOfCode(_localctx.tabs);
				setState(47);
				((BlocksOfCodeContext)_localctx).blocksOfCode = blocksOfCode(_localctx.tabs);

				        sb.append(((BlocksOfCodeContext)_localctx).blockOfCode.str).append("\n").append(((BlocksOfCodeContext)_localctx).blocksOfCode.str);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);

			        ((BlocksOfCodeContext)_localctx).str =  sb.toString();
			    
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
	public static class BlockOfCodeContext extends ParserRuleContext {
		public int tabs;
		public String str;
		public BlockIfContext blockIf;
		public InstructionContext instruction;
		public BlockIfContext blockIf() {
			return getRuleContext(BlockIfContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public BlockOfCodeContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockOfCodeContext(ParserRuleContext parent, int invokingState, int tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_blockOfCode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterBlockOfCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitBlockOfCode(this);
		}
	}

	public final BlockOfCodeContext blockOfCode(int tabs) throws RecognitionException {
		BlockOfCodeContext _localctx = new BlockOfCodeContext(_ctx, getState(), tabs);
		enterRule(_localctx, 4, RULE_blockOfCode);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				((BlockOfCodeContext)_localctx).blockIf = blockIf(_localctx.tabs);

				        ((BlockOfCodeContext)_localctx).str =  ((BlockOfCodeContext)_localctx).blockIf.str;
				    
				}
				break;
			case PRINT:
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				((BlockOfCodeContext)_localctx).instruction = instruction(_localctx.tabs);

				        ((BlockOfCodeContext)_localctx).str =  ((BlockOfCodeContext)_localctx).instruction.str;
				    
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
	public static class InstructionContext extends ParserRuleContext {
		public int tabs;
		public String str;
		public AssignmentContext assignment;
		public PrintContext print;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public InstructionContext(ParserRuleContext parent, int invokingState, int tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction(int tabs) throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState(), tabs);
		enterRule(_localctx, 6, RULE_instruction);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				((InstructionContext)_localctx).assignment = assignment();

				        ((InstructionContext)_localctx).str =  "\t".repeat(_localctx.tabs) + ((InstructionContext)_localctx).assignment.str;
				    
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				((InstructionContext)_localctx).print = print();

				        ((InstructionContext)_localctx).str =  "\t".repeat(_localctx.tabs) + ((InstructionContext)_localctx).print.str;
				    
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
	public static class BlockIfContext extends ParserRuleContext {
		public int tabs;
		public String str;
		public LogicalExprContext logicalExpr;
		public BlocksOfCodeContext blocksOfCode;
		public BlockIfContinueContext blockIfContinue;
		public TerminalNode IF() { return getToken(PrefixExprParser.IF, 0); }
		public LogicalExprContext logicalExpr() {
			return getRuleContext(LogicalExprContext.class,0);
		}
		public BlocksOfCodeContext blocksOfCode() {
			return getRuleContext(BlocksOfCodeContext.class,0);
		}
		public BlockIfContinueContext blockIfContinue() {
			return getRuleContext(BlockIfContinueContext.class,0);
		}
		public BlockIfContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockIfContext(ParserRuleContext parent, int invokingState, int tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_blockIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterBlockIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitBlockIf(this);
		}
	}

	public final BlockIfContext blockIf(int tabs) throws RecognitionException {
		BlockIfContext _localctx = new BlockIfContext(_ctx, getState(), tabs);
		enterRule(_localctx, 8, RULE_blockIf);

		        StringBuilder sb = new StringBuilder();
		    
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(IF);
			setState(69);
			((BlockIfContext)_localctx).logicalExpr = logicalExpr();
			setState(70);
			((BlockIfContext)_localctx).blocksOfCode = blocksOfCode(_localctx.tabs + 1);
			setState(71);
			((BlockIfContext)_localctx).blockIfContinue = blockIfContinue(_localctx.tabs);

			        sb.append("\t".repeat(_localctx.tabs)).append("if").append(" ")
			            .append(((BlockIfContext)_localctx).logicalExpr.str).append(":")
			            .append("\n")
			            .append(((BlockIfContext)_localctx).blocksOfCode.str);

			        if (!((BlockIfContext)_localctx).blockIfContinue.str.isEmpty()) {
			            sb.append("\n")
			                .append("\t".repeat(_localctx.tabs)).append("el").append(((BlockIfContext)_localctx).blockIfContinue.str.trim());
			        }
			    
			}
			_ctx.stop = _input.LT(-1);

			        ((BlockIfContext)_localctx).str =  sb.toString();
			    
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
	public static class BlockIfContinueContext extends ParserRuleContext {
		public int tabs;
		public String str;
		public BlockIfContext blockIf;
		public BlocksOfCodeContext blocksOfCode;
		public BlockIfContext blockIf() {
			return getRuleContext(BlockIfContext.class,0);
		}
		public BlocksOfCodeContext blocksOfCode() {
			return getRuleContext(BlocksOfCodeContext.class,0);
		}
		public BlockIfContinueContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockIfContinueContext(ParserRuleContext parent, int invokingState, int tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_blockIfContinue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterBlockIfContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitBlockIfContinue(this);
		}
	}

	public final BlockIfContinueContext blockIfContinue(int tabs) throws RecognitionException {
		BlockIfContinueContext _localctx = new BlockIfContinueContext(_ctx, getState(), tabs);
		enterRule(_localctx, 10, RULE_blockIfContinue);
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{

				        ((BlockIfContinueContext)_localctx).str =  "";
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				((BlockIfContinueContext)_localctx).blockIf = blockIf(_localctx.tabs);

				        ((BlockIfContinueContext)_localctx).str =  ((BlockIfContinueContext)_localctx).blockIf.str;
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				((BlockIfContinueContext)_localctx).blocksOfCode = blocksOfCode(_localctx.tabs + 1);

				        StringBuilder sb = new StringBuilder();
				        sb.append("se").append(":").append("\n").append(((BlockIfContinueContext)_localctx).blocksOfCode.str);
				        ((BlockIfContinueContext)_localctx).str =  sb.toString();
				    
				}
				break;
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
	public static class ExprContext extends ParserRuleContext {
		public String str;
		public LogicalExprContext logicalExpr;
		public ArithmeticExprContext arithmeticExpr;
		public LogicalExprContext logicalExpr() {
			return getRuleContext(LogicalExprContext.class,0);
		}
		public ArithmeticExprContext arithmeticExpr() {
			return getRuleContext(ArithmeticExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				((ExprContext)_localctx).logicalExpr = logicalExpr();

				        ((ExprContext)_localctx).str =  ((ExprContext)_localctx).logicalExpr.str;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				((ExprContext)_localctx).arithmeticExpr = arithmeticExpr();

				        ((ExprContext)_localctx).str =  ((ExprContext)_localctx).arithmeticExpr.str;
				    
				}
				break;
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
	public static class LogicalExprContext extends ParserRuleContext {
		public String str;
		public LogicalBinaryOpContext logicalBinaryOp;
		public LogicalExprContext left;
		public LogicalExprContext right;
		public LogicalUnaryOpContext logicalUnaryOp;
		public LogicalExprContext logicalExpr;
		public ComparisonContext comparison;
		public Token VAR;
		public LogicalBinaryOpContext logicalBinaryOp() {
			return getRuleContext(LogicalBinaryOpContext.class,0);
		}
		public List<LogicalExprContext> logicalExpr() {
			return getRuleContexts(LogicalExprContext.class);
		}
		public LogicalExprContext logicalExpr(int i) {
			return getRuleContext(LogicalExprContext.class,i);
		}
		public LogicalUnaryOpContext logicalUnaryOp() {
			return getRuleContext(LogicalUnaryOpContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode VAR() { return getToken(PrefixExprParser.VAR, 0); }
		public LogicalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterLogicalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitLogicalExpr(this);
		}
	}

	public final LogicalExprContext logicalExpr() throws RecognitionException {
		LogicalExprContext _localctx = new LogicalExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_logicalExpr);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case XOR:
			case AND:
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				((LogicalExprContext)_localctx).logicalBinaryOp = logicalBinaryOp();
				setState(92);
				((LogicalExprContext)_localctx).left = logicalExpr();
				setState(93);
				((LogicalExprContext)_localctx).right = logicalExpr();

				        ((LogicalExprContext)_localctx).str =  ((LogicalExprContext)_localctx).left.str + " " + ((LogicalExprContext)_localctx).logicalBinaryOp.str + " " + ((LogicalExprContext)_localctx).right.str;
				    
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				((LogicalExprContext)_localctx).logicalUnaryOp = logicalUnaryOp();
				setState(97);
				((LogicalExprContext)_localctx).logicalExpr = logicalExpr();

				        ((LogicalExprContext)_localctx).str =  ((LogicalExprContext)_localctx).logicalUnaryOp.str + "(" + ((LogicalExprContext)_localctx).logicalExpr.str + ")";
				    
				}
				break;
			case GE:
			case GT:
			case LE:
			case LT:
			case EQ:
			case NEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				((LogicalExprContext)_localctx).comparison = comparison();

				        ((LogicalExprContext)_localctx).str =  ((LogicalExprContext)_localctx).comparison.str;
				    
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				((LogicalExprContext)_localctx).VAR = match(VAR);

				        ((LogicalExprContext)_localctx).str =  (((LogicalExprContext)_localctx).VAR!=null?((LogicalExprContext)_localctx).VAR.getText():null);
				    
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
	public static class LogicalBinaryOpContext extends ParserRuleContext {
		public String str;
		public TerminalNode XOR() { return getToken(PrefixExprParser.XOR, 0); }
		public TerminalNode OR() { return getToken(PrefixExprParser.OR, 0); }
		public TerminalNode AND() { return getToken(PrefixExprParser.AND, 0); }
		public LogicalBinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalBinaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterLogicalBinaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitLogicalBinaryOp(this);
		}
	}

	public final LogicalBinaryOpContext logicalBinaryOp() throws RecognitionException {
		LogicalBinaryOpContext _localctx = new LogicalBinaryOpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_logicalBinaryOp);
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case XOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(XOR);

				        ((LogicalBinaryOpContext)_localctx).str =  "xor";
				    
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(OR);

				        ((LogicalBinaryOpContext)_localctx).str =  "or";
				    
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(AND);

				        ((LogicalBinaryOpContext)_localctx).str =  "and";
				    
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
	public static class LogicalUnaryOpContext extends ParserRuleContext {
		public String str;
		public TerminalNode NOT() { return getToken(PrefixExprParser.NOT, 0); }
		public LogicalUnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalUnaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterLogicalUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitLogicalUnaryOp(this);
		}
	}

	public final LogicalUnaryOpContext logicalUnaryOp() throws RecognitionException {
		LogicalUnaryOpContext _localctx = new LogicalUnaryOpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logicalUnaryOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(NOT);

			    ((LogicalUnaryOpContext)_localctx).str =  "not";

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
	public static class ComparisonContext extends ParserRuleContext {
		public String str;
		public ComparisonOpContext comparisonOp;
		public ArithmeticExprContext left;
		public ArithmeticExprContext right;
		public ComparisonOpContext comparisonOp() {
			return getRuleContext(ComparisonOpContext.class,0);
		}
		public List<ArithmeticExprContext> arithmeticExpr() {
			return getRuleContexts(ArithmeticExprContext.class);
		}
		public ArithmeticExprContext arithmeticExpr(int i) {
			return getRuleContext(ArithmeticExprContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			((ComparisonContext)_localctx).comparisonOp = comparisonOp();
			setState(119);
			((ComparisonContext)_localctx).left = arithmeticExpr();
			setState(120);
			((ComparisonContext)_localctx).right = arithmeticExpr();

			    ((ComparisonContext)_localctx).str =  ((ComparisonContext)_localctx).left.str + " " + ((ComparisonContext)_localctx).comparisonOp.str + " " + ((ComparisonContext)_localctx).right.str;

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
	public static class ComparisonOpContext extends ParserRuleContext {
		public String str;
		public Token GE;
		public Token GT;
		public Token LE;
		public Token LT;
		public Token EQ;
		public Token NEQ;
		public TerminalNode GE() { return getToken(PrefixExprParser.GE, 0); }
		public TerminalNode GT() { return getToken(PrefixExprParser.GT, 0); }
		public TerminalNode LE() { return getToken(PrefixExprParser.LE, 0); }
		public TerminalNode LT() { return getToken(PrefixExprParser.LT, 0); }
		public TerminalNode EQ() { return getToken(PrefixExprParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(PrefixExprParser.NEQ, 0); }
		public ComparisonOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterComparisonOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitComparisonOp(this);
		}
	}

	public final ComparisonOpContext comparisonOp() throws RecognitionException {
		ComparisonOpContext _localctx = new ComparisonOpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comparisonOp);
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GE:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				((ComparisonOpContext)_localctx).GE = match(GE);

				        ((ComparisonOpContext)_localctx).str =  (((ComparisonOpContext)_localctx).GE!=null?((ComparisonOpContext)_localctx).GE.getText():null);
				    
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				((ComparisonOpContext)_localctx).GT = match(GT);

				        ((ComparisonOpContext)_localctx).str =  (((ComparisonOpContext)_localctx).GT!=null?((ComparisonOpContext)_localctx).GT.getText():null);
				    
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				((ComparisonOpContext)_localctx).LE = match(LE);

				        ((ComparisonOpContext)_localctx).str =  (((ComparisonOpContext)_localctx).LE!=null?((ComparisonOpContext)_localctx).LE.getText():null);
				    
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				((ComparisonOpContext)_localctx).LT = match(LT);

				        ((ComparisonOpContext)_localctx).str =  (((ComparisonOpContext)_localctx).LT!=null?((ComparisonOpContext)_localctx).LT.getText():null);
				    
				}
				break;
			case EQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(131);
				((ComparisonOpContext)_localctx).EQ = match(EQ);

				        ((ComparisonOpContext)_localctx).str =  (((ComparisonOpContext)_localctx).EQ!=null?((ComparisonOpContext)_localctx).EQ.getText():null);
				    
				}
				break;
			case NEQ:
				enterOuterAlt(_localctx, 6);
				{
				setState(133);
				((ComparisonOpContext)_localctx).NEQ = match(NEQ);

				        ((ComparisonOpContext)_localctx).str =  (((ComparisonOpContext)_localctx).NEQ!=null?((ComparisonOpContext)_localctx).NEQ.getText():null);
				    
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
	public static class ArithmeticExprContext extends ParserRuleContext {
		public String str;
		public ArithmeticBinaryOpContext arithmeticBinaryOp;
		public ArithmeticExprContext left;
		public ArithmeticExprContext right;
		public ArithmeticUnaryOpContext arithmeticUnaryOp;
		public ArithmeticExprContext arithmeticExpr;
		public Token NUMBER;
		public Token VAR;
		public ArithmeticBinaryOpContext arithmeticBinaryOp() {
			return getRuleContext(ArithmeticBinaryOpContext.class,0);
		}
		public List<ArithmeticExprContext> arithmeticExpr() {
			return getRuleContexts(ArithmeticExprContext.class);
		}
		public ArithmeticExprContext arithmeticExpr(int i) {
			return getRuleContext(ArithmeticExprContext.class,i);
		}
		public ArithmeticUnaryOpContext arithmeticUnaryOp() {
			return getRuleContext(ArithmeticUnaryOpContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(PrefixExprParser.NUMBER, 0); }
		public TerminalNode VAR() { return getToken(PrefixExprParser.VAR, 0); }
		public ArithmeticExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterArithmeticExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitArithmeticExpr(this);
		}
	}

	public final ArithmeticExprContext arithmeticExpr() throws RecognitionException {
		ArithmeticExprContext _localctx = new ArithmeticExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arithmeticExpr);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				((ArithmeticExprContext)_localctx).arithmeticBinaryOp = arithmeticBinaryOp();
				setState(138);
				((ArithmeticExprContext)_localctx).left = arithmeticExpr();
				setState(139);
				((ArithmeticExprContext)_localctx).right = arithmeticExpr();

				        ((ArithmeticExprContext)_localctx).str =  "(" + ((ArithmeticExprContext)_localctx).left.str + " " + ((ArithmeticExprContext)_localctx).arithmeticBinaryOp.str + " " + ((ArithmeticExprContext)_localctx).right.str + ")";
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				((ArithmeticExprContext)_localctx).arithmeticUnaryOp = arithmeticUnaryOp();
				setState(143);
				((ArithmeticExprContext)_localctx).arithmeticExpr = arithmeticExpr();

				        ((ArithmeticExprContext)_localctx).str =  ((ArithmeticExprContext)_localctx).arithmeticUnaryOp.str + "(" + ((ArithmeticExprContext)_localctx).arithmeticExpr.str + ")";
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				((ArithmeticExprContext)_localctx).NUMBER = match(NUMBER);

				        ((ArithmeticExprContext)_localctx).str =  (((ArithmeticExprContext)_localctx).NUMBER!=null?((ArithmeticExprContext)_localctx).NUMBER.getText():null);
				    
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(148);
				((ArithmeticExprContext)_localctx).VAR = match(VAR);

				        ((ArithmeticExprContext)_localctx).str =  (((ArithmeticExprContext)_localctx).VAR!=null?((ArithmeticExprContext)_localctx).VAR.getText():null);
				    
				}
				break;
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
	public static class ArithmeticBinaryOpContext extends ParserRuleContext {
		public String str;
		public Token PLUS;
		public Token MINUS;
		public Token MUL;
		public Token DIV;
		public TerminalNode PLUS() { return getToken(PrefixExprParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PrefixExprParser.MINUS, 0); }
		public TerminalNode MUL() { return getToken(PrefixExprParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PrefixExprParser.DIV, 0); }
		public ArithmeticBinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticBinaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterArithmeticBinaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitArithmeticBinaryOp(this);
		}
	}

	public final ArithmeticBinaryOpContext arithmeticBinaryOp() throws RecognitionException {
		ArithmeticBinaryOpContext _localctx = new ArithmeticBinaryOpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arithmeticBinaryOp);
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				((ArithmeticBinaryOpContext)_localctx).PLUS = match(PLUS);

				        ((ArithmeticBinaryOpContext)_localctx).str =  (((ArithmeticBinaryOpContext)_localctx).PLUS!=null?((ArithmeticBinaryOpContext)_localctx).PLUS.getText():null);
				    
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				((ArithmeticBinaryOpContext)_localctx).MINUS = match(MINUS);

				        ((ArithmeticBinaryOpContext)_localctx).str =  (((ArithmeticBinaryOpContext)_localctx).MINUS!=null?((ArithmeticBinaryOpContext)_localctx).MINUS.getText():null);
				    
				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				((ArithmeticBinaryOpContext)_localctx).MUL = match(MUL);

				        ((ArithmeticBinaryOpContext)_localctx).str =  (((ArithmeticBinaryOpContext)_localctx).MUL!=null?((ArithmeticBinaryOpContext)_localctx).MUL.getText():null);
				    
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				((ArithmeticBinaryOpContext)_localctx).DIV = match(DIV);

				        ((ArithmeticBinaryOpContext)_localctx).str =  (((ArithmeticBinaryOpContext)_localctx).DIV!=null?((ArithmeticBinaryOpContext)_localctx).DIV.getText():null);
				    
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
	public static class ArithmeticUnaryOpContext extends ParserRuleContext {
		public String str;
		public Token MINUS;
		public TerminalNode MINUS() { return getToken(PrefixExprParser.MINUS, 0); }
		public ArithmeticUnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticUnaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterArithmeticUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitArithmeticUnaryOp(this);
		}
	}

	public final ArithmeticUnaryOpContext arithmeticUnaryOp() throws RecognitionException {
		ArithmeticUnaryOpContext _localctx = new ArithmeticUnaryOpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arithmeticUnaryOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			((ArithmeticUnaryOpContext)_localctx).MINUS = match(MINUS);

			    ((ArithmeticUnaryOpContext)_localctx).str =  (((ArithmeticUnaryOpContext)_localctx).MINUS!=null?((ArithmeticUnaryOpContext)_localctx).MINUS.getText():null);

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
	public static class PrintContext extends ParserRuleContext {
		public String str;
		public Token PRINT;
		public PrintOperandsContext printOperands;
		public TerminalNode PRINT() { return getToken(PrefixExprParser.PRINT, 0); }
		public PrintOperandsContext printOperands() {
			return getRuleContext(PrintOperandsContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitPrint(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			((PrintContext)_localctx).PRINT = match(PRINT);
			setState(166);
			((PrintContext)_localctx).printOperands = printOperands();

			        ((PrintContext)_localctx).str =  (((PrintContext)_localctx).PRINT!=null?((PrintContext)_localctx).PRINT.getText():null) + "(" + ((PrintContext)_localctx).printOperands.str + ")";
			    
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
	public static class PrintOperandsContext extends ParserRuleContext {
		public String str;
		public ExprContext expr;
		public PrintOperandsContext printOperands;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode BOTH() { return getToken(PrefixExprParser.BOTH, 0); }
		public PrintOperandsContext printOperands() {
			return getRuleContext(PrintOperandsContext.class,0);
		}
		public PrintOperandsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printOperands; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterPrintOperands(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitPrintOperands(this);
		}
	}

	public final PrintOperandsContext printOperands() throws RecognitionException {
		PrintOperandsContext _localctx = new PrintOperandsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_printOperands);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				((PrintOperandsContext)_localctx).expr = expr();

				        ((PrintOperandsContext)_localctx).str =  ((PrintOperandsContext)_localctx).expr.str;
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				match(BOTH);
				setState(174);
				((PrintOperandsContext)_localctx).expr = expr();
				setState(175);
				((PrintOperandsContext)_localctx).printOperands = printOperands();

				        ((PrintOperandsContext)_localctx).str =  ((PrintOperandsContext)_localctx).expr.str + ", " + ((PrintOperandsContext)_localctx).printOperands.str;
				    
				}
				break;
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
	public static class AssignmentContext extends ParserRuleContext {
		public String str;
		public Token ASSIGNMENT;
		public Token VAR;
		public ExprContext expr;
		public TerminalNode ASSIGNMENT() { return getToken(PrefixExprParser.ASSIGNMENT, 0); }
		public TerminalNode VAR() { return getToken(PrefixExprParser.VAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrefixExprListener ) ((PrefixExprListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			((AssignmentContext)_localctx).ASSIGNMENT = match(ASSIGNMENT);
			setState(181);
			((AssignmentContext)_localctx).VAR = match(VAR);
			setState(182);
			((AssignmentContext)_localctx).expr = expr();

			    ((AssignmentContext)_localctx).str =  (((AssignmentContext)_localctx).VAR!=null?((AssignmentContext)_localctx).VAR.getText():null) + " " + (((AssignmentContext)_localctx).ASSIGNMENT!=null?((AssignmentContext)_localctx).ASSIGNMENT.getText():null) + " " + ((AssignmentContext)_localctx).expr.str;

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
		"\u0004\u0001\u0015\u00ba\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0003\u0000)\b\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u00013\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002;\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003C\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005R\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006Z\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007j\b\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\br\b\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0003\u000b\u0088\b\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u0097\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u00a1\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u00b3\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0000\u0000\u0012\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"\u0000\u0000\u00c0\u0000(\u0001\u0000\u0000\u0000\u00022\u0001\u0000\u0000"+
		"\u0000\u0004:\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000\b"+
		"D\u0001\u0000\u0000\u0000\nQ\u0001\u0000\u0000\u0000\fY\u0001\u0000\u0000"+
		"\u0000\u000ei\u0001\u0000\u0000\u0000\u0010q\u0001\u0000\u0000\u0000\u0012"+
		"s\u0001\u0000\u0000\u0000\u0014v\u0001\u0000\u0000\u0000\u0016\u0087\u0001"+
		"\u0000\u0000\u0000\u0018\u0096\u0001\u0000\u0000\u0000\u001a\u00a0\u0001"+
		"\u0000\u0000\u0000\u001c\u00a2\u0001\u0000\u0000\u0000\u001e\u00a5\u0001"+
		"\u0000\u0000\u0000 \u00b2\u0001\u0000\u0000\u0000\"\u00b4\u0001\u0000"+
		"\u0000\u0000$%\u0003\u0002\u0001\u0000%&\u0006\u0000\uffff\uffff\u0000"+
		"&)\u0001\u0000\u0000\u0000\')\u0005\u0000\u0000\u0001($\u0001\u0000\u0000"+
		"\u0000(\'\u0001\u0000\u0000\u0000)\u0001\u0001\u0000\u0000\u0000*+\u0003"+
		"\u0004\u0002\u0000+,\u0006\u0001\uffff\uffff\u0000,3\u0001\u0000\u0000"+
		"\u0000-.\u0005\u0011\u0000\u0000./\u0003\u0004\u0002\u0000/0\u0003\u0002"+
		"\u0001\u000001\u0006\u0001\uffff\uffff\u000013\u0001\u0000\u0000\u0000"+
		"2*\u0001\u0000\u0000\u00002-\u0001\u0000\u0000\u00003\u0003\u0001\u0000"+
		"\u0000\u000045\u0003\b\u0004\u000056\u0006\u0002\uffff\uffff\u00006;\u0001"+
		"\u0000\u0000\u000078\u0003\u0006\u0003\u000089\u0006\u0002\uffff\uffff"+
		"\u00009;\u0001\u0000\u0000\u0000:4\u0001\u0000\u0000\u0000:7\u0001\u0000"+
		"\u0000\u0000;\u0005\u0001\u0000\u0000\u0000<=\u0003\"\u0011\u0000=>\u0006"+
		"\u0003\uffff\uffff\u0000>C\u0001\u0000\u0000\u0000?@\u0003\u001e\u000f"+
		"\u0000@A\u0006\u0003\uffff\uffff\u0000AC\u0001\u0000\u0000\u0000B<\u0001"+
		"\u0000\u0000\u0000B?\u0001\u0000\u0000\u0000C\u0007\u0001\u0000\u0000"+
		"\u0000DE\u0005\u000f\u0000\u0000EF\u0003\u000e\u0007\u0000FG\u0003\u0002"+
		"\u0001\u0000GH\u0003\n\u0005\u0000HI\u0006\u0004\uffff\uffff\u0000I\t"+
		"\u0001\u0000\u0000\u0000JR\u0006\u0005\uffff\uffff\u0000KL\u0003\b\u0004"+
		"\u0000LM\u0006\u0005\uffff\uffff\u0000MR\u0001\u0000\u0000\u0000NO\u0003"+
		"\u0002\u0001\u0000OP\u0006\u0005\uffff\uffff\u0000PR\u0001\u0000\u0000"+
		"\u0000QJ\u0001\u0000\u0000\u0000QK\u0001\u0000\u0000\u0000QN\u0001\u0000"+
		"\u0000\u0000R\u000b\u0001\u0000\u0000\u0000ST\u0003\u000e\u0007\u0000"+
		"TU\u0006\u0006\uffff\uffff\u0000UZ\u0001\u0000\u0000\u0000VW\u0003\u0018"+
		"\f\u0000WX\u0006\u0006\uffff\uffff\u0000XZ\u0001\u0000\u0000\u0000YS\u0001"+
		"\u0000\u0000\u0000YV\u0001\u0000\u0000\u0000Z\r\u0001\u0000\u0000\u0000"+
		"[\\\u0003\u0010\b\u0000\\]\u0003\u000e\u0007\u0000]^\u0003\u000e\u0007"+
		"\u0000^_\u0006\u0007\uffff\uffff\u0000_j\u0001\u0000\u0000\u0000`a\u0003"+
		"\u0012\t\u0000ab\u0003\u000e\u0007\u0000bc\u0006\u0007\uffff\uffff\u0000"+
		"cj\u0001\u0000\u0000\u0000de\u0003\u0014\n\u0000ef\u0006\u0007\uffff\uffff"+
		"\u0000fj\u0001\u0000\u0000\u0000gh\u0005\u0012\u0000\u0000hj\u0006\u0007"+
		"\uffff\uffff\u0000i[\u0001\u0000\u0000\u0000i`\u0001\u0000\u0000\u0000"+
		"id\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000j\u000f\u0001\u0000"+
		"\u0000\u0000kl\u0005\u0006\u0000\u0000lr\u0006\b\uffff\uffff\u0000mn\u0005"+
		"\b\u0000\u0000nr\u0006\b\uffff\uffff\u0000op\u0005\u0007\u0000\u0000p"+
		"r\u0006\b\uffff\uffff\u0000qk\u0001\u0000\u0000\u0000qm\u0001\u0000\u0000"+
		"\u0000qo\u0001\u0000\u0000\u0000r\u0011\u0001\u0000\u0000\u0000st\u0005"+
		"\u0005\u0000\u0000tu\u0006\t\uffff\uffff\u0000u\u0013\u0001\u0000\u0000"+
		"\u0000vw\u0003\u0016\u000b\u0000wx\u0003\u0018\f\u0000xy\u0003\u0018\f"+
		"\u0000yz\u0006\n\uffff\uffff\u0000z\u0015\u0001\u0000\u0000\u0000{|\u0005"+
		"\t\u0000\u0000|\u0088\u0006\u000b\uffff\uffff\u0000}~\u0005\n\u0000\u0000"+
		"~\u0088\u0006\u000b\uffff\uffff\u0000\u007f\u0080\u0005\u000b\u0000\u0000"+
		"\u0080\u0088\u0006\u000b\uffff\uffff\u0000\u0081\u0082\u0005\f\u0000\u0000"+
		"\u0082\u0088\u0006\u000b\uffff\uffff\u0000\u0083\u0084\u0005\r\u0000\u0000"+
		"\u0084\u0088\u0006\u000b\uffff\uffff\u0000\u0085\u0086\u0005\u000e\u0000"+
		"\u0000\u0086\u0088\u0006\u000b\uffff\uffff\u0000\u0087{\u0001\u0000\u0000"+
		"\u0000\u0087}\u0001\u0000\u0000\u0000\u0087\u007f\u0001\u0000\u0000\u0000"+
		"\u0087\u0081\u0001\u0000\u0000\u0000\u0087\u0083\u0001\u0000\u0000\u0000"+
		"\u0087\u0085\u0001\u0000\u0000\u0000\u0088\u0017\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0003\u001a\r\u0000\u008a\u008b\u0003\u0018\f\u0000\u008b"+
		"\u008c\u0003\u0018\f\u0000\u008c\u008d\u0006\f\uffff\uffff\u0000\u008d"+
		"\u0097\u0001\u0000\u0000\u0000\u008e\u008f\u0003\u001c\u000e\u0000\u008f"+
		"\u0090\u0003\u0018\f\u0000\u0090\u0091\u0006\f\uffff\uffff\u0000\u0091"+
		"\u0097\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0013\u0000\u0000\u0093"+
		"\u0097\u0006\f\uffff\uffff\u0000\u0094\u0095\u0005\u0012\u0000\u0000\u0095"+
		"\u0097\u0006\f\uffff\uffff\u0000\u0096\u0089\u0001\u0000\u0000\u0000\u0096"+
		"\u008e\u0001\u0000\u0000\u0000\u0096\u0092\u0001\u0000\u0000\u0000\u0096"+
		"\u0094\u0001\u0000\u0000\u0000\u0097\u0019\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0005\u0001\u0000\u0000\u0099\u00a1\u0006\r\uffff\uffff\u0000\u009a"+
		"\u009b\u0005\u0002\u0000\u0000\u009b\u00a1\u0006\r\uffff\uffff\u0000\u009c"+
		"\u009d\u0005\u0003\u0000\u0000\u009d\u00a1\u0006\r\uffff\uffff\u0000\u009e"+
		"\u009f\u0005\u0004\u0000\u0000\u009f\u00a1\u0006\r\uffff\uffff\u0000\u00a0"+
		"\u0098\u0001\u0000\u0000\u0000\u00a0\u009a\u0001\u0000\u0000\u0000\u00a0"+
		"\u009c\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1"+
		"\u001b\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\u0002\u0000\u0000\u00a3"+
		"\u00a4\u0006\u000e\uffff\uffff\u0000\u00a4\u001d\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0005\u0010\u0000\u0000\u00a6\u00a7\u0003 \u0010\u0000\u00a7"+
		"\u00a8\u0006\u000f\uffff\uffff\u0000\u00a8\u001f\u0001\u0000\u0000\u0000"+
		"\u00a9\u00b3\u0001\u0000\u0000\u0000\u00aa\u00ab\u0003\f\u0006\u0000\u00ab"+
		"\u00ac\u0006\u0010\uffff\uffff\u0000\u00ac\u00b3\u0001\u0000\u0000\u0000"+
		"\u00ad\u00ae\u0005\u0011\u0000\u0000\u00ae\u00af\u0003\f\u0006\u0000\u00af"+
		"\u00b0\u0003 \u0010\u0000\u00b0\u00b1\u0006\u0010\uffff\uffff\u0000\u00b1"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b2\u00a9\u0001\u0000\u0000\u0000\u00b2"+
		"\u00aa\u0001\u0000\u0000\u0000\u00b2\u00ad\u0001\u0000\u0000\u0000\u00b3"+
		"!\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005\u0014\u0000\u0000\u00b5\u00b6"+
		"\u0005\u0012\u0000\u0000\u00b6\u00b7\u0003\f\u0006\u0000\u00b7\u00b8\u0006"+
		"\u0011\uffff\uffff\u0000\u00b8#\u0001\u0000\u0000\u0000\f(2:BQYiq\u0087"+
		"\u0096\u00a0\u00b2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}