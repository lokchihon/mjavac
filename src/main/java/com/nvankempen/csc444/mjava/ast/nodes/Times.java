package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import com.nvankempen.csc444.mjava.codegen.CodeGenerationVisitor;
import org.antlr.v4.runtime.Token;

public class Times extends Expression {
    private Expression left, right;

    public Times(Expression left, Expression right, Token start, Token stop) {
        super(start, stop);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
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
