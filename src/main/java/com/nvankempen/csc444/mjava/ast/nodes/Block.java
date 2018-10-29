package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Block extends Statement {
    private List<Statement> statements;

    public Block(List<Statement> statements, Token start, Token stop) {
        super(start, stop);
        this.statements = statements;
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
