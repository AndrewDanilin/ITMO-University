package expression.parser;

import expression.*;

public class ExpressionParser implements Parser{
    public TripleExpression parse(String expression) {
        return parse(new StringSource(expression));
    }

    public TripleExpression parse(CharSource source) {
        return new ExpParser(source).parseExpression();
    }

    private static class ExpParser extends BaseParser {
        private String signOfOp;
        

        public ExpParser(final CharSource source) {
            super(source);
            nextChar();
        }

        public TripleExpression parseExpression() {
            final TripleExpression result = parseExpr();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        public ExpressionInterface parseExpr() {
            ExpressionInterface first = parseXor();

            while (!eof()) {
                if (signOfOp.equals("|")) {
                    first = new Or(first, parseXor());
                } else {
                    return first;
                }
            }
            return first;
        }

        public ExpressionInterface parseXor() {
            ExpressionInterface first = parseAnd();

            while (!eof()) {
                if (signOfOp.equals("^")) {
                    first = new Xor(first, parseAnd());
                } else {
                    return first;
                }
            }
            return first;
        }

        public ExpressionInterface parseAnd() {
            ExpressionInterface first = parseAddOrSub();

            while (!eof()) {
                if (signOfOp.equals("&")) {
                    first = new And(first, parseAddOrSub());
                } else {
                    return first;
                }
            }
            return first;
        }


        public ExpressionInterface parseAddOrSub() {
            ExpressionInterface first = parseTerm();

            while (!eof()) {
                if (signOfOp.equals("+")) {
                    first = new Add(first, parseTerm());
                } else if (signOfOp.equals("-")) {
                    first = new Subtract(first, parseTerm());
                } else {
                    return first;
                }
            }
            return first;
        }

        public ExpressionInterface parseTerm() {
            ExpressionInterface first = parsePrim();

            while (!eof()) {
                if (signOfOp.equals("*")) {
                    first = new Multiply(first, parsePrim());
                } else if (signOfOp.equals("/")) {
                    first = new Divide(first, parsePrim());
                } else {
                    return first;
                }
            }
            return first;
        }

        public ExpressionInterface parsePrim() {
            skipWhitespace();
            boolean negative = false;
            while (ch == '-') {
                negative = !negative;
                nextChar();
                skipWhitespace();
            }
            ExpressionInterface value;
            if (test('(')) {
                skipWhitespace();
                value = negative ? new UnaryMinus(parseExpr()) : parseExpr();
                skipWhitespace();
                if (test(')')) {
                    skipWhitespace();
                    return value;
                }
            } else {
                if (between('0', '9')) {
                    value = parseConst(negative);
                } else {
                    value = negative ? new UnaryMinus(parseVariable()) : parseVariable();
                }
            }
            skipWhitespace();
            signOfOp = parseSignOfOp();
            skipWhitespace();
            return value;
        }


        public String parseSignOfOp() {
            String sign = String.valueOf(ch);
            nextChar();
            return sign;
        }


        public ExpressionInterface parseConst(boolean negative) {
            StringBuilder sb = new StringBuilder();
            skipWhitespace();
            do {
                sb.append(ch);
                nextChar();
            } while (between('0', '9'));
            return new Const(Integer.parseInt(negative ? "-" + sb.toString() : sb.toString()));
        }

        public ExpressionInterface parseVariable() {
            StringBuilder sb = new StringBuilder();
            skipWhitespace();
            do {
                sb.append(ch);
                nextChar();
            } while (Character.isAlphabetic(ch));
            return new Variable(sb.toString());
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
            }
        }
    }
}
