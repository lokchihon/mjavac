package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class MethodDeclaration {
    private Type type;
    private Identifier name;
    private FormalList parameters;
    private VarDeclarationList variables;
    private StatementList statements;
    private Expression returnStatement;

    public MethodDeclaration(Type type, Identifier name, FormalList parameters, VarDeclarationList variables, StatementList statements, Expression returnStatement) {
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.variables = variables;
        this.statements = statements;
        this.returnStatement = returnStatement;
    }

    public Type getType() {
        return type;
    }

    public Identifier getName() {
        return name;
    }

    public FormalList getParameters() {
        return parameters;
    }

    public VarDeclarationList getVariables() {
        return variables;
    }

    public StatementList getStatements() {
        return statements;
    }

    public Expression getReturn() {
        return returnStatement;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
