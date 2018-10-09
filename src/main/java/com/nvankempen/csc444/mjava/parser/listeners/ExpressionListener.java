package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.Expression;

public class ExpressionListener extends MiniJavaParserBaseListener {
    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

}
