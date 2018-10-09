package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class ArrayAssign extends Statement {
    private Identifier array;
    private Expression index, value;

    public ArrayAssign(Identifier array, Expression index, Expression value) {
        this.array = array;
        this.index = index;
        this.value = value;
    }

    public Identifier getArray() {
        return array;
    }

    public Expression getIndex() {
        return index;
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
