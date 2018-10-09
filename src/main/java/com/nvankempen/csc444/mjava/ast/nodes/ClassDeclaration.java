package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class ClassDeclaration {
    private Identifier name, superclass;
    private VarDeclarationList variables;
    private MethodDeclarationList methods;

    public ClassDeclaration(Identifier name, Identifier superclass, VarDeclarationList variables, MethodDeclarationList methods) {
        this.name = name;
        this.superclass = superclass;
        this.variables = variables;
        this.methods = methods;
    }

    public ClassDeclaration(Identifier name, VarDeclarationList variables, MethodDeclarationList methods) {
        this(name, null, variables, methods);
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

    public boolean hasSuperClass() {
        return superclass == null;
    }

    public VarDeclarationList getVariables() {
        return variables;
    }

    public MethodDeclarationList getMethods() {
        return methods;
    }
}
