package com.nvankempen.csc444.mjava;

import org.antlr.v4.runtime.*;

public class ErrorListener extends BaseErrorListener {

    private boolean error = false;

    public boolean hasSyntaxErrors() {
        return error;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        error = true;
        System.out.printf("ERROR [%d : %d] : %s %n", line, charPositionInLine, msg);
    }
}
