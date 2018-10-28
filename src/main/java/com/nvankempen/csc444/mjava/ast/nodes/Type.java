package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;

public abstract class Type {
    public abstract String getName();
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);

    public static Type fromValue(String value) {
        if (value.equals("int")) {
            return new IntegerType();
        }

        if (value.equals("int[]")) {
            return new IntegerArrayType();
        }

        if (value.equals("boolean")) {
            return new BooleanType();
        }

        return new IdentifierType(new Identifier(value));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Type) && this.getName().equals(((Type) obj).getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public boolean is(Type type) {
        return equals(type);
    }

    public boolean is(String type) {
        return this.getName().equals(type);
    }

    public boolean isInt() {
        return this.is("int");
    }

    public boolean isBoolean() {
        return this.is("boolean");
    }

    public boolean isIntArray() {
        return this.is("int[]");
    }
}
