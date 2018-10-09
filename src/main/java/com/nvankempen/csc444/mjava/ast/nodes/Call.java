package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class Call extends Expression {
    private Expression object;
    private Identifier method;
    private ExpressionList arguments;

    public Call (Expression object, Identifier method, ExpressionList arguments) {
        this.object = object;
        this.method = method;
        this.arguments = arguments;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
