package com.andrewdanilin.homework1.expression.exceptions;

import com.andrewdanilin.homework1.expression.parser.ParseException;

public class MissingParenthesisException extends ParseException {
    public MissingParenthesisException(String e) {
        super(e);
    }
}
