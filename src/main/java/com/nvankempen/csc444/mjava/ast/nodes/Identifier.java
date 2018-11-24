package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import com.nvankempen.csc444.mjava.codegen.CodeGenerationVisitor;
import org.antlr.v4.runtime.Token;

public class Identifier extends Expression {
    private String name;

    public Identifier(String name, Token token) {
        super(token, null);
        this.name = name;
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

    public void accept(CodeGenerationVisitor v) {
        v.visit(this);
    }
}
