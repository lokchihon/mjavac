package com.nvankempen.csc444.mjava.ast.analysis;

import org.antlr.v4.runtime.Token;

public interface ErrorHandler {
    boolean hasErrors();
    void error(Token start, Token stop, String format, Object... args);
    void error(Token token, String format, Object... args);
}
