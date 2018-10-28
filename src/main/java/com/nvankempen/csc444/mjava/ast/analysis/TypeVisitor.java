package com.nvankempen.csc444.mjava.ast.analysis;

import com.nvankempen.csc444.mjava.ast.nodes.*;

public interface TypeVisitor {
    Type visit(Program program);
    Type visit(ClassDeclaration declaration);
    Type visit(RegularVarDeclaration declaration);
    Type visit(UntypedVarDeclaration declaration);
    Type visit(MethodDeclaration declaration);
    Type visit(Block block);
    Type visit(If statement);
    Type visit(While statement);
    Type visit(Print statement);
    Type visit(VarAssign statement);
    Type visit(ArrayAssign statement);
    Type visit(And expression);
    Type visit(Not expression);
    Type visit(LessThan expression);
    Type visit(Plus expression);
    Type visit(Minus expression);
    Type visit(Times expression);
    Type visit(ArrayLookup expression);
    Type visit(ArrayLength expression);
    Type visit(Call expression);
    Type visit(IntegerLiteral expression);
    Type visit(BooleanLiteral expression);
    Type visit(NewArray expression);
    Type visit(NewObject expression);
    Type visit(This expression);
    Type visit(Identifier identifier);
    Type visit(Formal formal);
    Type visit(MainClass main);
    Type visit(Type type);
}
