package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

public class NewObject extends Expression {
    private Identifier className;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public NewObject(Identifier className, Token start, Token stop) {
        this.className = className;
        this.start = start;
        this.stop = stop;
    }

    public Identifier getClassName() {
        return className;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
