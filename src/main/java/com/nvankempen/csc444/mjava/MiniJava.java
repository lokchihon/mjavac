package com.nvankempen.csc444.mjava;

import com.nvankempen.csc444.mjava.ast.nodes.Program;
import com.nvankempen.csc444.mjava.parser.ListenerOrientedParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class MiniJava {

    public static void main(String[] args) throws IOException {
        ListenerOrientedParser parser = new ListenerOrientedParser();
        Program p = parser.parse(CharStreams.fromStream(System.in));

//        ErrorListener errorListener = new ErrorListener();
//        parser.removeErrorListeners();
//        parser.addErrorListener(errorListener);
//
//        ParseTree tree = parser.program();
//
//        if (!errorListener.hasSyntaxErrors()) {
//            System.out.println(tree.toStringTree(parser));
//        }
    }
}
