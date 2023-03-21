package com.andrewdanilin.homework1.expression.parser;

public class StringSource implements CharSource {
    private final String data;
    protected int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ParseException error(final String message) {
        return new ParseException(pos + ": " + message);
    }
}
