package com.andrewdanilin.homework10.expression.exceptions;

import com.andrewdanilin.homework10.expression.parser.ParseException;

public class ArgumentException extends ParseException {
    public ArgumentException(String e) {
        super(e);
    }
}
