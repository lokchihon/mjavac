package com.nvankempen.csc444.mjava;

import com.nvankempen.csc444.mjava.ast.analysis.PrintVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.TypeCheckVisitor;
import com.nvankempen.csc444.mjava.ast.nodes.Program;
import com.nvankempen.csc444.mjava.parser.VisitorParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class MiniJava {

    public static void main(String[] args) throws IOException {

        if (args.length > 1) {
            System.out.println("ERROR You may only specify one file to compile at a time.");
            return;
        }

        MiniJavaParser parser;
        if (args.length == 1) {
            parser = new MiniJavaParser(new CommonTokenStream(new MiniJavaLexer(CharStreams.fromFileName(args[0]))));
        } else {
            parser = new MiniJavaParser(new CommonTokenStream(new MiniJavaLexer(CharStreams.fromStream(System.in))));
        }

        ParserErrorListener errorListener = new ParserErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.program();

        if (!errorListener.hasSyntaxErrors()) {
            Program program = (new VisitorParser()).parse(tree);
            PrintVisitor print = new PrintVisitor(System.out);
            print.visit(program);
            TypeCheckVisitor check = new TypeCheckVisitor();
            check.visit(program);
        }
    }
}
