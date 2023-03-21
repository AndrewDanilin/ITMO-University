package com.andrewdanilin.homework1.expression.parser;

public interface CharSource {
    boolean hasNext();
    char next();
    ParseException error(final String message);
    int getPos();
}
