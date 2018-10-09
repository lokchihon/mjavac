package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.Program;

public class ProgramListener extends MiniJavaParserBaseListener {
    private Program program;

    public Program getProgram() {
        return program;
    }

    @Override
    public void enterProgram(MiniJavaParser.ProgramContext ctx) {
        MainClassListener mainL = new MainClassListener();
        ctx.mainClass().enterRule(mainL);

        ClassListener classL = new ClassListener();
        ctx.classDeclaration().forEach(classDeclaration -> classDeclaration.enterRule(classL));
        program = new Program(mainL.getMainClass(), classL.getClasses());
    }
}
