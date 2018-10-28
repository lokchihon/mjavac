package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

public class MainClass {
    private Identifier name;
    private Statement statement;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public MainClass(Identifier name, Statement statement, Token start, Token stop) {
        this.name = name;
        this.statement = statement;
        this.start = start;
        this.stop = stop;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public Identifier getName() {
        return name;
    }

    public Statement getStatement() {
        return statement;
    }
}
