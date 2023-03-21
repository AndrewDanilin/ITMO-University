package com.andrewdanilin.homework1.expression.parser;

import com.andrewdanilin.homework1.expression.Expression;

public interface Parser {
    Expression parse(String expression);
}
