package com.nvankempen.csc444.mjava;

import com.nvankempen.csc444.mjava.ast.AST;
import com.nvankempen.csc444.mjava.parser.VisitorParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class MiniJava {

    public static void main(String[] args) throws IOException {

        MiniJavaParser parser = new MiniJavaParser(new CommonTokenStream(new MiniJavaLexer(CharStreams.fromStream(System.in))));

        ErrorListener errorListener = new ErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.program();

        if (!errorListener.hasSyntaxErrors()) {
            AST ast = (new VisitorParser()).parse(tree);
        }
    }
}
