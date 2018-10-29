package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Call extends Expression {
    private Expression object;
    private Identifier method;
    private List<Expression> arguments;

    public Call (Expression object, Identifier method, List<Expression> arguments, Token start, Token stop) {
        super(start, stop);
        this.object = object;
        this.method = method;
        this.arguments = arguments;
    }

    public Expression getObject() {
        return object;
    }

    public Identifier getMethod() {
        return method;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
