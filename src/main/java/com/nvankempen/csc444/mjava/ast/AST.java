package com.nvankempen.csc444.mjava.ast;

import com.nvankempen.csc444.mjava.ast.nodes.ClassDeclaration;
import com.nvankempen.csc444.mjava.ast.nodes.Program;

import java.util.Map;

public class AST {
    private Program program;
    private Map<String, ClassDeclaration> types;

    public AST(Program program) {
        this.program = program;
    }
}
