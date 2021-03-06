package com.nvankempen.csc444.mjava.ast.analysis;

import org.antlr.v4.runtime.Token;

public class DefaultErrorHandler implements ErrorHandler {

    private boolean error = false;

    public boolean hasErrors() {
        return error;
    }

    public void error(Token start, Token stop, String format, Object... args) {
        if (stop == null || start.getLine() == stop.getLine()) {
            error(start, format, args);
        } else {
            error = true;
            System.out.printf(String.format("[%d:%d - %d:%d] ERROR %s %n",
                    start.getLine(), start.getCharPositionInLine(),
                    stop.getLine(), stop.getCharPositionInLine(),
                    format
            ), args);
        }
    }

    public void error(Token token, String format, Object... args) {
        error = true;
        System.out.printf(String.format("[%d:%d] ERROR %s %n",
                token.getLine(),
                token.getCharPositionInLine(),
                format
        ), args);
    }
}
