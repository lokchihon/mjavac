package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class MainClass {
    private Identifier name;
    private Statement statement;

    public MainClass(Identifier name, Statement statement) {
        this.name = name;
        this.statement = statement;
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
