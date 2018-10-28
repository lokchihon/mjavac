package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;

public class IdentifierType extends Type {

    private Identifier type;

    public IdentifierType(Identifier type) {
        this.type = type;
    }

    public Identifier getIdentifier() {
        return type;
    }

    @Override
    public String getName() {
        return getIdentifier().getName();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
