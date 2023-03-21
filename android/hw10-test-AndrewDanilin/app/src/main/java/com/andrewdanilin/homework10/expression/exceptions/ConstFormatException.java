package com.andrewdanilin.homework10.expression.exceptions;

import com.andrewdanilin.homework10.expression.parser.ParseException;

public class ConstFormatException extends ParseException {
    public ConstFormatException(String e) {
        super(e);
    }
}
