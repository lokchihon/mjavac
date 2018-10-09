package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class ClassListener extends MiniJavaParserBaseListener {
    private List<ClassDeclaration> classes = new ArrayList<>();

    @Override
    public void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        String name = ctx.IDENTIFIER(0).getText();
        String superclass = (ctx.EXTENDS() != null) ? ctx.IDENTIFIER(1).getText() : null;

        VarListener variables = new VarListener();
        ctx.varDeclaration().forEach(variable -> variable.enterRule(variables));

        MethodListener methods = new MethodListener();
        ctx.methodDeclaration().forEach(method -> method.enterRule(methods));

        classes.add(new ClassDeclaration(
            new Identifier(name), 
            new Identifier(superclass), 
            variables.getVariableDeclarations(), 
            methods.getMethodDeclarations()
        ));
    }

    public ClassDeclarationList getClasses() {
        return new ClassDeclarationList(classes);
    }
}
