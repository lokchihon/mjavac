package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class UnTypedVarDeclaration extends VarDeclaration {
    private Expression value;

    public UnTypedVarDeclaration(Identifier name, Expression value) {
        super(null, name);
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
