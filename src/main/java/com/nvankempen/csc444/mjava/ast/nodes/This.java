package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

public class This extends Expression {
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public This(Token start, Token stop) {
        this.start = start;
        this.stop = stop;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
