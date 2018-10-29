package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class MethodDeclaration {
    private Type type;
    private Identifier name;
    private List<Formal> parameters;
    private List<VarDeclaration> variables;
    private List<Statement> statements;
    private Expression returnStatement;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public MethodDeclaration(Type type, Identifier name, List<Formal> parameters, List<VarDeclaration> variables, List<Statement> statements, Expression returnStatement, Token start, Token stop) {
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.variables = variables;
        this.statements = statements;
        this.returnStatement = returnStatement;
        this.start = start;
        this.stop = stop;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Identifier getName() {
        return name;
    }

    public List<Formal> getParameters() {
        return parameters;
    }

    public List<VarDeclaration> getVariables() {
        return variables;
    }

    public List<Statement> getStatements() {
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
