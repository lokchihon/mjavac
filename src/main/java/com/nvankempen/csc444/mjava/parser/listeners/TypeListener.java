package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.*;

public class TypeListener extends MiniJavaParserBaseListener {

    private Type type;

    public Type getType() {
        return type; //TODO: fix this
    }

    @Override
    public void enterIntArrayType(MiniJavaParser.IntArrayTypeContext ctx) {
        type = new IntegerArrayType();
    }

    @Override
    public void enterIntType(MiniJavaParser.IntTypeContext ctx) {
        type = new IntegerType();
    }

    @Override
    public void enterBooleanType(MiniJavaParser.BooleanTypeContext ctx) {
        type = new BooleanType();
    }

    @Override
    public void enterIdentifierType(MiniJavaParser.IdentifierTypeContext ctx) {
        type = new IdentifierType();
    }
}
