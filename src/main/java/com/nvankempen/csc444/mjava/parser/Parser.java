package com.nvankempen.csc444.mjava.parser;

import com.nvankempen.csc444.mjava.ast.AST;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public abstract class Parser {
    public abstract AST parse(ParseTree input) throws IOException;
}
