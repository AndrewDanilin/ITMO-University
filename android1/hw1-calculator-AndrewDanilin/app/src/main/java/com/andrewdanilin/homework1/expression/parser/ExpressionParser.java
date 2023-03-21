package com.andrewdanilin.homework1.expression.parser;

import com.andrewdanilin.homework1.expression.*;
import com.andrewdanilin.homework1.expression.exceptions.*;


import java.math.BigDecimal;

public class ExpressionParser implements Parser {

    public Expression parse(String expression) {
        return parse(new StringSource(expression));
    }

    public Expression parse(CharSource source) {
        return new ExpParser(source).parseExpression();
    }


    private static class ExpParser extends BaseParser {
        private String signOfOp;
        private CharSource source;

        public ExpParser(final CharSource source) {
            super(source);
            this.source = source;
            nextChar();
        }

        public Expression parseExpression() throws ParseException {
            final Expression result = parseAddOrSub();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        public Expression parseAddOrSub() throws ParseException {
            Expression first = parseTerm();

            while (!eof()) {
                if (test('+')) {
                    first = new CheckedAdd(first, parseTerm());
                } else if (test('-')) {
                    first = new CheckedSubtract(first, parseTerm());
                } else {
                    return first;
                }
            }
            return first;
        }

        public Expression parseTerm() throws ParseException {
            Expression first = parsePrim();

            while (!eof()) {
                if (test('*')) {
                    first = new CheckedMultiply(first, parsePrim());
                } else if (test('/')) {
                    first = new CheckedDivide(first, parsePrim());
                } else {
                    return first;
                }
            }
            return first;
        }

        public Expression parsePrim() throws ParseException {
            Expression value;
            skipWhitespace();
            if (test('(')) {
                value = parseAddOrSub();
                if (!test(')')) {
                    throw new MissingParenthesisException("No closing parenthesis: " + getCurrentPos());
                }
            } else if (test('-')) {
                if (between('0', '9')) {
                    value = parseConst(true);
                } else {
                    value = new CheckedNegate(parsePrim());
                }
            } else if (between('0', '9')) {
                value = parseConst(false);
            } else {
                throw new ArgumentException("No argument: " + getCurrentPos());
            }
            skipWhitespace();
            return value;
        }

        public Const parseConst(boolean negative) throws ParseException {
            StringBuilder sb = new StringBuilder();
            if (negative) {
                sb.append('-');
            }
            skipWhitespace();
            do {
                sb.append(ch);
                nextChar();
            } while (between('0', '9') || equalChar('.'));
            try {
                return new Const(BigDecimal.valueOf(Double.parseDouble(sb.toString())));
            } catch (NumberFormatException e) {
                throw new ConstFormatException("Not valid const format at: " + getCurrentPos());
            }
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
            }
        }
    }
}
