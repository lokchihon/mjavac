package com.nvankempen.csc444.mjava.ast;

import com.nvankempen.csc444.mjava.ast.nodes.*;

public class DepthFirstTypeVisitor implements TypeVisitor {
    @Override
    public Type visit(Program program) {
        program.getMainClass().accept(this);
        for (ClassDeclaration declaration : program.getClasses()) {
            declaration.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(ClassDeclaration declaration) {
        declaration.getName().accept(this);
        if (declaration.hasSuperClass()) {
            declaration.getSuperclass().accept(this);
        }
        for (VarDeclaration variable : declaration.getVariables()) {
            variable.accept(this);
        }

        for (MethodDeclaration method : declaration.getMethods()) {
            method.accept(this);
        }

        return null;
    }

    @Override
    public Type visit(VarDeclaration declaration) {
        declaration.getType().accept(this);
        declaration.getName().accept(this);
        return null;
    }

    @Override
    public Type visit(MethodDeclaration declaration) {
        declaration.getType().accept(this);
        declaration.getName().accept(this);

        for (Formal parameter : declaration.getParameters()) {
            parameter.accept(this);
        }

        for (VarDeclaration variable : declaration.getVariables()) {
            variable.accept(this);
        }

        for (Statement statement : declaration.getStatements()) {
            statement.accept(this);
        }

        declaration.getReturn().accept(this);

        return null;
    }

    @Override
    public Type visit(Block block) {
        for (Statement statement : block.getStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Type visit(If statement) {
        statement.getCondition().accept(this);
        statement.getTrueStatement().accept(this);
        statement.getFalseStatement().accept(this);
        return null;
    }

    @Override
    public Type visit(While statement) {
        statement.getCondition().accept(this);
        statement.getStatement().accept(this);
        return null;
    }

    @Override
    public Type visit(Print statement) {
        statement.getExpression().accept(this);
        return null;
    }

    @Override
    public Type visit(VarAssign statement) {
        statement.getVariable().accept(this);
        statement.getVariable().accept(this);
        return null;
    }

    @Override
    public Type visit(ArrayAssign statement) {
        statement.getArray().accept(this);
        statement.getIndex().accept(this);
        statement.getValue().accept(this);
        return null;
    }

    @Override
    public Type visit(And expression) {
        return null;
    }

    @Override
    public Type visit(Not expression) {
        return null;
    }

    @Override
    public Type visit(LessThan expression) {
        return null;
    }

    @Override
    public Type visit(Plus expression) {
        return null;
    }

    @Override
    public Type visit(Minus expression) {
        return null;
    }

    @Override
    public Type visit(Times expression) {
        return null;
    }

    @Override
    public Type visit(ArrayLookup expression) {
        return null;
    }

    @Override
    public Type visit(ArrayLength expression) {
        return null;
    }

    @Override
    public Type visit(Call expression) {
        return null;
    }

    @Override
    public Type visit(IntegerLiteral expression) {
        return null;
    }

    @Override
    public Type visit(BooleanLiteral expression) {
        return null;
    }

    @Override
    public Type visit(NewArray expression) {
        return null;
    }

    @Override
    public Type visit(NewObject expression) {
        return null;
    }

    @Override
    public Type visit(This expression) {
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        return null;
    }

    @Override
    public Type visit(Formal formal) {
        formal.getType().accept(this);
        formal.getName().accept(this);
        return null;
    }

    @Override
    public Type visit(MainClass main) {
        return null;
    }
}
