package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.*;
import com.nvankempen.csc444.mjava.ast.nodes.*;

public class StatementListener extends MiniJavaParserBaseListener {
    private Statement statement;

    public Statement getStatement() {
        return statement;
    }

    @Override
    public void enterStatementBlock(MiniJavaParser.StatementBlockContext ctx) {
        StatementList statements = new StatementList();

        for (MiniJavaParser.StatementContext statement : ctx.statement()) {
            StatementListener statementL = new StatementListener();
            statement.enterRule(statementL);
            statements.add(statementL.getStatement());
        }

        statement = new Block(statements);
    }

    @Override
    public void enterIfStatement(MiniJavaParser.IfStatementContext ctx) {
        ExpressionListener condition = new ExpressionListener();
        ctx.expression().enterRule(condition);

        StatementListener ifS = new StatementListener();
        ctx.statement(0).enterRule(ifS);
        StatementListener elseS = new StatementListener();
        ctx.statement(1).enterRule(elseS);

        statement = new If(condition.getExpression(), ifS.getStatement(), elseS.getStatement());
    }

    @Override
    public void enterWhileStatement(MiniJavaParser.WhileStatementContext ctx) {
        ExpressionListener condition = new ExpressionListener();
        ctx.expression().enterRule(condition);

        StatementListener statementL = new StatementListener();
        ctx.statement().enterRule(statementL);

        statement = new While(condition.getExpression(), statementL.getStatement());
    }

    @Override
    public void enterPrintStatement(MiniJavaParser.PrintStatementContext ctx) {
        ExpressionListener expression = new ExpressionListener();
        ctx.expression().enterRule(expression);

        statement = new Print(expression.getExpression());
    }

    @Override
    public void enterVarAssignStatement(MiniJavaParser.VarAssignStatementContext ctx) {
        String variable = ctx.IDENTIFIER().getText();

        ExpressionListener expression = new ExpressionListener();
        ctx.expression().enterRule(expression);

        statement = new VarAssign(new Identifier(variable), expression.getExpression());
    }

    @Override
    public void enterArrayAssignStatement(MiniJavaParser.ArrayAssignStatementContext ctx) {
        String array = ctx.IDENTIFIER().getText();

        ExpressionListener index = new ExpressionListener();
        ctx.expression(0).enterRule(index);

        ExpressionListener value = new ExpressionListener();
        ctx.expression(1).enterRule(value);

        statement = new ArrayAssign(new Identifier(array), index.getExpression(), value.getExpression());
    }
}
