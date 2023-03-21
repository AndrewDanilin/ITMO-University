package com.andrewdanilin.homework1.expression.exceptions;

import com.andrewdanilin.homework1.expression.parser.ParseException;

public class ConstFormatException extends ParseException {
    public ConstFormatException(String e) {
        super(e);
    }
}
