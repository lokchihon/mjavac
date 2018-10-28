package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Block extends Statement {
    private List<Statement> statements;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public Block(List<Statement> statements, Token start, Token stop) {
        this.statements = statements;
        this.start = start;
        this.stop = stop;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
