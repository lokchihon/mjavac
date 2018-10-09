package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.ClassDeclaration;
import com.nvankempen.csc444.mjava.ast.nodes.ClassDeclarationList;

import java.util.ArrayList;
import java.util.List;

public class ClassListener extends MiniJavaParserBaseListener {
    private List<ClassDeclaration> classes = new ArrayList<>();

    @Override
    public void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {

    }

    public ClassDeclarationList getClasses() {
        return new ClassDeclarationList(classes);
    }
}
