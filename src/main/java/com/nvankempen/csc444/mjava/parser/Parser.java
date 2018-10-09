package com.nvankempen.csc444.mjava.parser;

import com.nvankempen.csc444.mjava.ast.nodes.Program;
import org.antlr.v4.runtime.CharStream;

import java.io.IOException;

public abstract class Parser {
    public abstract Program parse(CharStream input) throws IOException;
}
