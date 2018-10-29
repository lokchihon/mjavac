package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

public class Formal {
    private Type type;
    private Identifier name;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public Formal(Type type, Identifier name, Token start, Token stop) {
        this.type = type;
        this.name = name;
        this.start = start;
        this.stop = stop;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
