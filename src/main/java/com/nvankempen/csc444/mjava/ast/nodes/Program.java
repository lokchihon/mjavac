package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Program {
    private MainClass main;
    private List<ClassDeclaration> classes;
    private Token start, stop;

    public Token getStart() {
        return start;
    }

    public Token getStop() {
        return stop;
    }

    public Program(MainClass main, List<ClassDeclaration> classes, Token start, Token stop) {
        this.main = main;
        this.classes = classes;
        this.start = start;
        this.stop = stop;
    }

    public MainClass getMainClass() {
        return main;
    }

    public List<ClassDeclaration> getClasses() {
        return classes;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
