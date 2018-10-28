package com.nvankempen.csc444.mjava.parser;

import com.nvankempen.csc444.mjava.ast.nodes.Program;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Parser {
    public abstract Program parse(ParseTree input);
}
