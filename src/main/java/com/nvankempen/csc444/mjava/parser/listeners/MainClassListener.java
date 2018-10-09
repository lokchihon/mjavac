package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.Identifier;
import com.nvankempen.csc444.mjava.ast.nodes.MainClass;

public class MainClassListener extends MiniJavaParserBaseListener {
    private MainClass main;

    public MainClass getMainClass() {
        return main;
    }

    @Override
    public void enterMainClass(MiniJavaParser.MainClassContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        StatementListener statementL = new StatementListener();
        ctx.mainMethod().statement().enterRule(statementL);

        main = new MainClass(new Identifier(name), statementL.getStatement());
    }
}
