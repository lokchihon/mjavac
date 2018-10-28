package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;

public abstract class Expression {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
