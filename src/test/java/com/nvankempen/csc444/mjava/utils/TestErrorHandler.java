package com.nvankempen.csc444.mjava.utils;

import com.nvankempen.csc444.mjava.ast.analysis.DefaultErrorHandler;
import org.antlr.v4.runtime.Token;

import java.util.HashSet;
import java.util.Set;

public class TestErrorHandler extends DefaultErrorHandler {

    private Set<Integer> errorLines = new HashSet<>();

    @Override
    public void error(Token start, Token stop, String format, Object... args) {
        super.error(start, stop, format, args);
        errorLines.add(start.getLine());
    }

    @Override
    public void error(Token token, String format, Object... args) {
        super.error(token, format, args);
        errorLines.add(token.getLine());
    }

    public int nb() {
        return errorLines.size();
    }

    public boolean atLine(int line) {
        return errorLines.contains(line);
    }
}