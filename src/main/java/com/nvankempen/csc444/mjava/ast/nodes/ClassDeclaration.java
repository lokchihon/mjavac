package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ClassDeclaration {
    private Identifier name, superclass;
    private List<VarDeclaration> variables;
    private List<MethodDeclaration> methods;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public ClassDeclaration(Identifier name, Identifier superclass, List<VarDeclaration> variables, List<MethodDeclaration> methods, Token start, Token stop) {
        this.name = name;
        this.superclass = superclass;
        this.variables = variables;
        this.methods = methods;
        this.start = start;
        this.stop = stop;
    }

    public ClassDeclaration(Identifier name, List<VarDeclaration> variables, List<MethodDeclaration> methods, Token start, Token stop) {
        this(name, null, variables, methods, start, stop);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public Identifier getName() {
        return name;
    }

    public Identifier getSuperclass() {
        return superclass;
    }

    public void setSuperclass(Identifier superclass) {
        this.superclass = superclass;
    }

    public boolean hasSuperClass() {
        return superclass != null;
    }

    public List<VarDeclaration> getVariables() {
        return variables;
    }

    public List<MethodDeclaration> getMethods() {
        return methods;
    }
}
