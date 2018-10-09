package com.nvankempen.csc444.mjava.parser;

import com.nvankempen.csc444.mjava.*;
import com.nvankempen.csc444.mjava.ast.nodes.Program;
import com.nvankempen.csc444.mjava.parser.listeners.*;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class ListenerOrientedParser extends Parser {

    @Override
    public Program parse(CharStream input) throws IOException {
        MiniJavaLexer lexer = new MiniJavaLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        MiniJavaParser parser = new MiniJavaParser(tokens);

        ProgramListener program = new ProgramListener();
        parser.program().enterRule(program);
        return program.getProgram();
    }
}
