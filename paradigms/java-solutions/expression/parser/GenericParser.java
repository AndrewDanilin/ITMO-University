package expression.parser;

import expression.GenericTripleExpression;
import expression.modes.Mode;

public interface GenericParser<T extends Number> {
    GenericTripleExpression<T> parse(String expression, Mode<T> mode);
}
