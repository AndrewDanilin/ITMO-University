package gen;// Generated from java-escape by ANTLR 4.11.1



import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrefixExprParser}.
 */
public interface PrefixExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(PrefixExprParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(PrefixExprParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#blocksOfCode}.
	 * @param ctx the parse tree
	 */
	void enterBlocksOfCode(PrefixExprParser.BlocksOfCodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#blocksOfCode}.
	 * @param ctx the parse tree
	 */
	void exitBlocksOfCode(PrefixExprParser.BlocksOfCodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#blockOfCode}.
	 * @param ctx the parse tree
	 */
	void enterBlockOfCode(PrefixExprParser.BlockOfCodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#blockOfCode}.
	 * @param ctx the parse tree
	 */
	void exitBlockOfCode(PrefixExprParser.BlockOfCodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(PrefixExprParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(PrefixExprParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#blockIf}.
	 * @param ctx the parse tree
	 */
	void enterBlockIf(PrefixExprParser.BlockIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#blockIf}.
	 * @param ctx the parse tree
	 */
	void exitBlockIf(PrefixExprParser.BlockIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#blockIfContinue}.
	 * @param ctx the parse tree
	 */
	void enterBlockIfContinue(PrefixExprParser.BlockIfContinueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#blockIfContinue}.
	 * @param ctx the parse tree
	 */
	void exitBlockIfContinue(PrefixExprParser.BlockIfContinueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PrefixExprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PrefixExprParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#logicalExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpr(PrefixExprParser.LogicalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#logicalExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpr(PrefixExprParser.LogicalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#logicalBinaryOp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalBinaryOp(PrefixExprParser.LogicalBinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#logicalBinaryOp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalBinaryOp(PrefixExprParser.LogicalBinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#logicalUnaryOp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalUnaryOp(PrefixExprParser.LogicalUnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#logicalUnaryOp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalUnaryOp(PrefixExprParser.LogicalUnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(PrefixExprParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(PrefixExprParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOp(PrefixExprParser.ComparisonOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOp(PrefixExprParser.ComparisonOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#arithmeticExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpr(PrefixExprParser.ArithmeticExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#arithmeticExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpr(PrefixExprParser.ArithmeticExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#arithmeticBinaryOp}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticBinaryOp(PrefixExprParser.ArithmeticBinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#arithmeticBinaryOp}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticBinaryOp(PrefixExprParser.ArithmeticBinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#arithmeticUnaryOp}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticUnaryOp(PrefixExprParser.ArithmeticUnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#arithmeticUnaryOp}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticUnaryOp(PrefixExprParser.ArithmeticUnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(PrefixExprParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(PrefixExprParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#printOperands}.
	 * @param ctx the parse tree
	 */
	void enterPrintOperands(PrefixExprParser.PrintOperandsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#printOperands}.
	 * @param ctx the parse tree
	 */
	void exitPrintOperands(PrefixExprParser.PrintOperandsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixExprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(PrefixExprParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixExprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(PrefixExprParser.AssignmentContext ctx);
}