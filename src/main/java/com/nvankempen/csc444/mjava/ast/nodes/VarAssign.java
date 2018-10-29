package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

public class VarAssign extends Statement {
    private Identifier variable;
    private Expression value;

    public VarAssign(Identifier variable, Expression value, Token start, Token stop) {
        super(start, stop);
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
