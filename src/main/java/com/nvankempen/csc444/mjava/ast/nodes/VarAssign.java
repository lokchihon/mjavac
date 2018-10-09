package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class VarAssign extends Statement {
    private Identifier variable;
    private Expression value;

    public VarAssign(Identifier variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    public Identifier getVariable() {
        return variable;
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
