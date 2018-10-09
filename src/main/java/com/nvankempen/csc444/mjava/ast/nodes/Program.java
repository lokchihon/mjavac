package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.Visitor;

public class Program {
    private MainClass main;
    private ClassDeclarationList classes;

    public Program(MainClass main, ClassDeclarationList classes) {
        this.main = main;
        this.classes = classes;
    }

    public MainClass getMainClass() {
        return main;
    }

    public ClassDeclarationList getClasses() {
        return classes;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
