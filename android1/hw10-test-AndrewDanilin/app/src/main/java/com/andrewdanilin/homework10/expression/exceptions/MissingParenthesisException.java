package com.andrewdanilin.homework10.expression.exceptions;

import com.andrewdanilin.homework10.expression.parser.ParseException;

public class MissingParenthesisException extends ParseException {
    public MissingParenthesisException(String e) {
        super(e);
    }
}
