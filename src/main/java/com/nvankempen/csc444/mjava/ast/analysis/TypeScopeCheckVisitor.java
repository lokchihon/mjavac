package com.nvankempen.csc444.mjava.ast.analysis;

import com.nvankempen.csc444.mjava.ast.nodes.*;
import java.util.*;
import java.util.stream.Collectors;

public class TypeScopeCheckVisitor implements TypeVisitor {

    private ClassDeclaration current;
    private Map<Identifier, Type> instance;
    private Map<Identifier, Type> method;
    private Map<Identifier, Type> parameters;

    @Override
    public Type visit(Program program) {
        // TODO We need to build a type table or something.
        // TODO Check for duplicate classes. Maybe do so in the visit(ClassDeclaration) by saving program as instance?
        return null;
    }

    @Override
    public Type visit(ClassDeclaration declaration) {
        // TODO Validate superclass

        current = declaration;
        instance = new HashMap<>();

        // TODO Check for duplicate instance variables.
        for (VarDeclaration variable : declaration.getVariables()) {
            variable.accept(this);
            instance.put(variable.getName(), variable.getType());
        }

        // TODO Check for duplicate methods
        for (MethodDeclaration method : declaration.getMethods()) {
            method.accept(this);
        }

        return null;
    }

    @Override
    public Type visit(RegularVarDeclaration declaration) {
        // TODO Check if type exists.
        return null;
    }

    @Override
    public Type visit(UntypedVarDeclaration declaration) {
        declaration.setType(declaration.getValue().accept(this));
        return null;
    }

    @Override
    public Type visit(MethodDeclaration declaration) {
        method = new HashMap<>();
        parameters = new HashMap<>();

        // TODO Check for duplicate methods.
        for (Formal parameter : declaration.getParameters()) {
            parameter.accept(this);
            parameters.put(parameter.getName(), parameter.getType());
        }

        // TODO Check for duplicate variables
        // TODO Check if variable not already in Parameters
        for (VarDeclaration variable : declaration.getVariables()) {
            variable.accept(this);
            method.put(variable.getName(), variable.getType());
        }

        for (Statement statement : declaration.getStatements()) {
            statement.accept(this);
        }

        Type ret = declaration.getReturn().accept(this);
        // TODO Check if ret matches declared type.

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
        // TODO Check if condition is a boolean

        statement.getTrueStatement().accept(this);
        if (statement.hasFalseStatement()) statement.getFalseStatement().accept(this);

        return null;
    }

    @Override
    public Type visit(While statement) {
        // TODO Check if condition is a boolean

        statement.getStatement().accept(this);
        return null;
    }

    @Override
    public Type visit(Print statement) {
        // TODO Check if expression is an integer
        return null;
    }

    @Override
    public Type visit(VarAssign statement) {
        Type variable = statement.getVariable().accept(this);
        Type expression = statement.getValue().accept(this);
        // TODO Check if types match
        return null;
    }

    @Override
    public Type visit(ArrayAssign statement) {
        Type variable = statement.getArray().accept(this);
        // TODO check if variable is int[]
        Type index = statement.getIndex().accept(this);
        Type value = statement.getValue().accept(this);
        // TODO Check if both are ints.
        return null;
    }

    @Override
    public Type visit(And expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        // TODO Check if both are booleans
        return new BooleanType();
    }

    @Override
    public Type visit(Not expression) {
        Type type = expression.getExpression().accept(this);
        // TODO Check if type is boolean
        return new BooleanType();
    }

    @Override
    public Type visit(LessThan expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        // TODO Check if both are ints
        return new BooleanType();
    }

    @Override
    public Type visit(Plus expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        // TODO Check if both are ints
        return new IntegerType();
    }

    @Override
    public Type visit(Minus expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        // TODO Check if both are ints
        return new IntegerType();
    }

    @Override
    public Type visit(Times expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        // TODO Check if both are ints
        return new IntegerType();
    }

    @Override
    public Type visit(ArrayLookup expression) {
        Type array = expression.getArray().accept(this);
        Type index = expression.getIndex().accept(this);
        // TODO Check if array is int[], index is int.
        // We only have int arrays.
        return new IntegerType();
    }

    @Override
    public Type visit(ArrayLength expression) {
        // TODO Check if expression is int
        return new IntegerType();
    }

    @Override
    public Type visit(Call expression) {
        Type exp = expression.getObject().accept(this);
        List<Type> arguments = expression.getArguments().stream().map(e -> e.accept(this)).collect(Collectors.toList());

        // TODO Check if method matches
        // TODO Return whatever it matches with
        return new UnknownType();
    }

    @Override
    public Type visit(IntegerLiteral expression) {
        return new IntegerType();
    }

    @Override
    public Type visit(BooleanLiteral expression) {
        return new BooleanType();
    }

    @Override
    public Type visit(NewArray expression) {
        Type length = expression.accept(this);
        // TODO Check length is an int
        return new IntegerArrayType();
    }

    @Override
    public Type visit(NewObject expression) {
        // TODO Check if class exists
        return new IdentifierType(expression.getClassName());
    }

    @Override
    public Type visit(This expression) {
        return new IdentifierType(current.getName());
    }

    @Override
    public Type visit(Identifier identifier) {
        // TODO Lookup identifier and return type.
        return new UnknownType();
    }

    @Override
    public Type visit(Formal formal) {
        // TODO Check if type exists
        return null;
    }

    @Override
    public Type visit(MainClass main) {
        instance = new HashMap<>();
        method = new HashMap<>();
        parameters = new HashMap<>();

        main.getStatement().accept(this);
        return null;
    }

    @Override
    public Type visit(Type type) {
        return null;
    }
}
