package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import com.nvankempen.csc444.mjava.codegen.CodeGenerationVisitor;
import org.antlr.v4.runtime.Token;

public class ArrayLookup extends Expression {
    private Expression array, index;

    public ArrayLookup(Expression array, Expression index, Token start, Token stop) {
        super(start, stop);
        this.array = array;
        this.index = index;
    }

    public Expression getArray() {
        return array;
    }

    public Expression getIndex() {
        return index;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(CodeGenerationVisitor v) {
        v.visit(this);
    }
}
