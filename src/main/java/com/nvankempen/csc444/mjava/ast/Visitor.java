package com.nvankempen.csc444.mjava.ast;

import com.nvankempen.csc444.mjava.ast.nodes.*;

public interface Visitor {
    void visit(Program program);
    void visit(ClassDeclaration declaration);
    void visit(VarDeclaration declaration);
    void visit(MethodDeclaration declaration);
    void visit(Block block);
    void visit(If statement);
    void visit(While statement);
    void visit(Print statement);
    void visit(VarAssign statement);
    void visit(ArrayAssign statement);
    void visit(And expression);
    void visit(Not expression);
    void visit(LessThan expression);
    void visit(Plus expression);
    void visit(Minus expression);
    void visit(Times expression);
    void visit(ArrayLookup expression);
    void visit(ArrayLength expression);
    void visit(Call expression);
    void visit(IntegerLiteral expression);
    void visit(BooleanLiteral expression);
    void visit(NewArray expression);
    void visit(NewObject expression);
    void visit(This expression);
    void visit(Identifier identifier);
    void visit(Formal formal);
    void visit(MainClass main);
    void visit(Type type);
}
