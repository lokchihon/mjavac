package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import com.nvankempen.csc444.mjava.codegen.CodeGenerationVisitor;
import org.antlr.v4.runtime.Token;

public abstract class Statement {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public Statement(Token start, Token stop) {
        this.start = start;
        this.stop = stop;
    }

    public abstract void accept(CodeGenerationVisitor v);
}
