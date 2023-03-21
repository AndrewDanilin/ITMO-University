package com.andrewdanilin.homework1.expression.exceptions;

import com.andrewdanilin.homework1.expression.parser.ParseException;

public class ArgumentException extends ParseException {
    public ArgumentException(String e) {
        super(e);
    }
}
