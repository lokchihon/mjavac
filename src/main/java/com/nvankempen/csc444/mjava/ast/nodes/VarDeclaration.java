package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class VarDeclaration {
    private Type type;
    private Identifier name;

    public VarDeclaration(Type type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Identifier getName() {
        return name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
