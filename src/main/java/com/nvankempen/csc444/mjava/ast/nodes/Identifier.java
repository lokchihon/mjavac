package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

public class Identifier extends Expression {
    private String name;
    private Token token;

    public Token getToken() {
        return token;
    }

    public Identifier(String name) {
        this.name = name;
    }

    public Identifier(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Identifier) && name.equals(((Identifier) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
