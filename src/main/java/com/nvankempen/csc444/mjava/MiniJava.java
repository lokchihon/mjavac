package com.nvankempen.csc444.mjava;

import com.nvankempen.csc444.mjava.ast.analysis.PrintVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.TypeScopeCheckVisitor;
import com.nvankempen.csc444.mjava.ast.nodes.Program;
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
            Program program = (new VisitorParser()).parse(tree);
            PrintVisitor print = new PrintVisitor(System.out);
            print.visit(program);
            TypeScopeCheckVisitor check = new TypeScopeCheckVisitor();
            check.visit(program);
        }
    }
}
