package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class VarListener extends MiniJavaParserBaseListener {
    private List<VarDeclaration> variables = new ArrayList<>();

    @Override
    public void enterTypedDeclaration(MiniJavaParser.TypedDeclarationContext ctx) {
        TypeListener type = new TypeListener();
        ctx.type().enterRule(type);

        String name = ctx.IDENTIFIER().getText();
        variables.add(new VarDeclaration(type.getType(), new Identifier(name)));
    }

    @Override
    public void enterUnTypedDeclaration(MiniJavaParser.UnTypedDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        ExpressionListener expression = new ExpressionListener();
        ctx.expression().enterRule(expression);

        variables.add(new UnTypedVarDeclaration(
            new Identifier(name), 
            expression.getExpression()
        ));
    }

    public VarDeclarationList getVariableDeclarations() {
        return new VarDeclarationList(variables);
    }
}