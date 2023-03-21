package com.andrewdanilin.homework1.expression.exceptions;

import com.andrewdanilin.homework1.expression.parser.ParseException;
import com.andrewdanilin.homework1.expression.Expression;

public interface Parser {
    Expression parse(String expression) throws ParseException;
}
