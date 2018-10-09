package com.nvankempen.csc444.mjava.ast.nodes;

import java.util.List;

public class ExpressionList {
    private List<Expression> expressions;

    public ExpressionList(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
