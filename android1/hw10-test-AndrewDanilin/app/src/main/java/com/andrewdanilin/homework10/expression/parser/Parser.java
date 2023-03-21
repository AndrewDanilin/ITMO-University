package com.andrewdanilin.homework10.expression.parser;

import com.andrewdanilin.homework10.expression.Expression;

public interface Parser {
    Expression parse(String expression);
}
