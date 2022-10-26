package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.modes.Mode;

import java.util.Set;

public class GenericExpressionParser<T extends Number> implements GenericParser<T> {

    public GenericTripleExpression<T> parse(String expression, Mode<T> mode) throws ParseException {
        return parse(new StringSource(expression), mode);
    }

    public GenericTripleExpression<T> parse(CharSource source, Mode<T> mode) throws ParseException {
        return new ExpParser<T>(source, mode).parseExpression();
    }


    private static class ExpParser<T extends Number> extends BaseParser {
        private final Mode<T> mode;

        private final Set<String> variables = Set.of("x", "y", "z");

        private ExpParser(CharSource source, Mode<T> mode) {
            super(source);
            nextChar();
            this.mode = mode;
        }

        private GenericTripleExpression<T> parseExpression() throws ParseException {
            final GenericExpressionInterface<T> result = parseAddOrSub();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        private GenericExpressionInterface<T> parseAddOrSub() throws ParseException {
            GenericExpressionInterface<T> first = parseTerm();

            while (!eof()) {
                if (test('+')) {
                    first = new GenericAdd<>(first, parseTerm(), mode);
                } else if (test('-')) {
                    first = new GenericSubtract<>(first, parseTerm(), mode);
                } else {
                    return first;
                }
            }
            return first;
        }

        private GenericExpressionInterface<T> parseTerm() throws ParseException {
            GenericExpressionInterface<T> first = parsePrim();

            while (!eof()) {
                if (test('*')) {
                    first = new GenericMultiply<>(first, parsePrim(), mode);
                } else if (test('/')) {
                    first = new GenericDivide<>(first, parsePrim(), mode);
                } else {
                    return first;
                }
            }
            return first;
        }

        private GenericExpressionInterface<T> parsePrim() throws ParseException {
            GenericExpressionInterface<T> value;
            skipWhitespace();
            if (test('(')) {
                value = parseAddOrSub();
                if (!test(')')) {
                    throw new MissingParenthesisException("No closing parenthesis: " + getCurrentPos());
                }
            } else if (test('-')) {
                if (between('0', '9') || ch == ',') {
                    value = parseConst(true);
                } else {
                    value = new GenericNegate<>(parsePrim(), mode);
                }
            } else if (between('0', '9') || ch == ',') {
                value = parseConst(false);
            } else if (Character.isAlphabetic(ch)) {
                value = parseVariable();
                if (!variables.contains(value.toString())) {
                    throw new VariableException("Not valid variable name: " + value.toString() + " at " + getCurrentPos());
                }
            } else {
                throw new OperandException("No operand: " + getCurrentPos());
            }
            skipWhitespace();
            return value;
        }

        private GenericConst<T> parseConst(boolean negative) throws ParseException {
            StringBuilder sb = new StringBuilder();
            if (negative) {
                sb.append('-');
            }
            skipWhitespace();
            do {
                sb.append(ch);
                nextChar();
            } while (between('0', '9') || ch == ',');
            try {
                return new GenericConst<>(mode.getValue(sb.toString()));
            } catch (NumberFormatException e) {
                throw new ConstFormatException("Not valid format of const: " + getCurrentPos());
            }
        }

        private GenericVariable<T> parseVariable() {
            StringBuilder sb = new StringBuilder();
            skipWhitespace();
            do {
                sb.append(ch);
                nextChar();
            } while (Character.isAlphabetic(ch));
            return new GenericVariable<>(sb.toString());
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
            }
        }
    }
}
