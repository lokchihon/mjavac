package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionListener extends MiniJavaParserBaseListener {
    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void enterArrayLookup(MiniJavaParser.ArrayLookupContext ctx) {
        ExpressionListener array = new ExpressionListener();
        ctx.expression(0).enterRule(array);

        ExpressionListener index = new ExpressionListener();
        ctx.expression(1).enterRule(index);

        expression = new ArrayLookup(array.getExpression(), index.getExpression());
    }

    @Override
    public void enterArrayLength(MiniJavaParser.ArrayLengthContext ctx) {
        ExpressionListener array = new ExpressionListener();
        ctx.expression().enterRule(array);

        expression = new ArrayLength(array.getExpression());
    }

    @Override
    public void enterMethodCall(MiniJavaParser.MethodCallContext ctx) {
        ExpressionListener array = new ExpressionListener();
        ctx.expression(0).enterRule(array);

        String method = ctx.IDENTIFIER().getText();

        List<Expression> arguments = new ArrayList<>();
        ctx.expression().stream().skip(1).forEach(expression -> {
            ExpressionListener argument = new ExpressionListener();
            expression.enterRule(argument);
            arguments.add(argument.getExpression());
        });

        expression = new Call(array.getExpression(), new Identifier(method), new ExpressionList(arguments));
    }

    @Override
    public void enterNot(MiniJavaParser.NotContext ctx) {
        ExpressionListener exp = new ExpressionListener();
        ctx.expression().enterRule(exp);

        expression = new Not(exp.getExpression());
    }

    @Override
    public void enterNewArray(MiniJavaParser.NewArrayContext ctx) {
        ExpressionListener length = new ExpressionListener();
        ctx.expression().enterRule(length);

        expression = new NewArray(length.getExpression());
    }

    @Override
    public void enterNewObject(MiniJavaParser.NewObjectContext ctx) {
        expression = new NewObject(new Identifier(ctx.IDENTIFIER().getText()));
    }

    @Override
    public void enterTimes(MiniJavaParser.TimesContext ctx) {
        ExpressionListener exp1 = new ExpressionListener();
        ctx.expression(0).enterRule(exp1);

        ExpressionListener exp2 = new ExpressionListener();
        ctx.expression(0).enterRule(exp2);

        expression = new Times(exp1.getExpression(), exp2.getExpression());
    }

    @Override
    public void enterPlus(MiniJavaParser.PlusContext ctx) {
        ExpressionListener exp1 = new ExpressionListener();
        ctx.expression(0).enterRule(exp1);

        ExpressionListener exp2 = new ExpressionListener();
        ctx.expression(0).enterRule(exp2);

        expression = new Plus(exp1.getExpression(), exp2.getExpression());
    }

    @Override
    public void enterMinus(MiniJavaParser.MinusContext ctx) {
        ExpressionListener exp1 = new ExpressionListener();
        ctx.expression(0).enterRule(exp1);

        ExpressionListener exp2 = new ExpressionListener();
        ctx.expression(0).enterRule(exp2);

        expression = new Minus(exp1.getExpression(), exp2.getExpression());
    }

    @Override
    public void enterLessThan(MiniJavaParser.LessThanContext ctx) {
        ExpressionListener exp1 = new ExpressionListener();
        ctx.expression(0).enterRule(exp1);

        ExpressionListener exp2 = new ExpressionListener();
        ctx.expression(0).enterRule(exp2);

        expression = new LessThan(exp1.getExpression(), exp2.getExpression());
    }

    @Override
    public void enterAnd(MiniJavaParser.AndContext ctx) {
        ExpressionListener exp1 = new ExpressionListener();
        ctx.expression(0).enterRule(exp1);

        ExpressionListener exp2 = new ExpressionListener();
        ctx.expression(0).enterRule(exp2);

        expression = new And(exp1.getExpression(), exp2.getExpression());
    }

    @Override
    public void enterInteger(MiniJavaParser.IntegerContext ctx) {
        expression = new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
    }

    @Override
    public void enterBoolean(MiniJavaParser.BooleanContext ctx) {
        expression = new BooleanLiteral(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText()));
    }

    @Override
    public void enterIdentifier(MiniJavaParser.IdentifierContext ctx) {
        expression = new Identifier(ctx.IDENTIFIER().getText());
    }

    @Override
    public void enterThis(MiniJavaParser.ThisContext ctx) {
        expression = new This();
    }

    @Override
    public void enterParenthesis(MiniJavaParser.ParenthesisContext ctx) {
        ExpressionListener exp = new ExpressionListener();
        ctx.expression().enterRule(exp);

        expression = exp.getExpression();
    }
}
