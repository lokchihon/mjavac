package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.Identifier;
import com.nvankempen.csc444.mjava.ast.nodes.MethodDeclaration;
import com.nvankempen.csc444.mjava.ast.nodes.MethodDeclarationList;
import com.nvankempen.csc444.mjava.ast.nodes.StatementList;

import java.util.ArrayList;
import java.util.List;

public class MethodListener extends MiniJavaParserBaseListener {
    private List<MethodDeclaration> methods = new ArrayList<>();

    public MethodDeclarationList getMethodDeclarations() {
        return new MethodDeclarationList(methods);
    }

    @Override
    public void enterMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        TypeListener type = new TypeListener();
        ctx.type().enterRule(type);

        String name = ctx.IDENTIFIER().getText();

        ParameterListener parameters = new ParameterListener();
        ctx.parameters().enterRule(parameters);

        VarListener variables = new VarListener();
        ctx.varDeclaration().forEach(variable -> variable.enterRule(variables));


        StatementList statements = new StatementList();

        for (MiniJavaParser.StatementContext statement : ctx.statement()) {
            StatementListener statementL = new StatementListener();
            statement.enterRule(statementL);
            statements.add(statementL.getStatement());
        }


        ExpressionListener returnExp = new ExpressionListener();
        ctx.expression().enterRule(returnExp);

        methods.add(new MethodDeclaration(
                type.getType(),
                new Identifier(name),
                parameters.getParameters(),
                variables.getVariableDeclarations(),
                statements,
                returnExp.getExpression()
        ));
    }
}
