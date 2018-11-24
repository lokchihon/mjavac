package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

public abstract class VarDeclaration {
    private Identifier name;
    private Token start, stop;

    public boolean hasValue() {
        return this instanceof UntypedVarDeclaration;
    }

    public Expression getValue() {
        return ((UntypedVarDeclaration) this).getValue();
    }

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public VarDeclaration(Identifier name, Token start, Token stop) {
        this.name = name;
        this.start = start;
        this.stop = stop;
    }

    public Identifier getName() {
        return name;
    }

    public abstract Type getType();

    public abstract void setType(Type type);

    public abstract void accept(Visitor v);

    public abstract Type accept(TypeVisitor v);

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof VarDeclaration) && name.equals(((VarDeclaration) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
