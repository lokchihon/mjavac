package com.nvankempen.csc444.mjava;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class MiniJava {

    public static void main(String[] args) throws IOException {

        CharStream stream = CharStreams.fromStream(System.in);
        MiniJavaLexer lexer = new MiniJavaLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniJavaParser parser = new MiniJavaParser(tokens);
        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
    }
}
