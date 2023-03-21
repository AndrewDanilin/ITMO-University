package com.andrewdanilin.homework10.expression.exceptions;

import com.andrewdanilin.homework10.expression.parser.ParseException;
import com.andrewdanilin.homework10.expression.Expression;

public interface Parser {
    Expression parse(String expression) throws ParseException;
}
