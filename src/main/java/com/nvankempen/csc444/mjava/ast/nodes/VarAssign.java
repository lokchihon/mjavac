package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

public class VarAssign extends Statement {
    private Identifier variable;
    private Expression value;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public VarAssign(Identifier variable, Expression value, Token start, Token stop) {
        this.variable = variable;
        this.value = value;
        this.start = start;
        this.stop = stop;
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
