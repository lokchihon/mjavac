package com.nvankempen.csc444.mjava.ast.analysis;

import com.nvankempen.csc444.mjava.ast.nodes.*;
import com.nvankempen.csc444.mjava.ast.utils.*;
import org.antlr.v4.runtime.Token;

import java.util.*;
import java.util.stream.Collectors;

public class TypeCheckVisitor implements TypeVisitor {

    private ClassDeclaration current;
    private Map<Identifier, Type> instance;
    private Map<Identifier, Type> method;
    private Map<Identifier, Type> parameters;

    private void setUnknown(Identifier name) {
        if (parameters.containsKey(name)) {
            parameters.put(name, new UnknownType());
        }

        if (method.containsKey(name)) {
            method.put(name, new UnknownType());
        }

        instance.put(name, new UnknownType());
    }

    private void error(Token start, Token stop, String format, Object... args) {
        if (stop == null || start.getLine() == stop.getLine()) {
            error(start, format, args);
        } else {
            System.out.printf(String.format("[%d:%d - %d:%d] %s %n",
                    start.getLine(), start.getCharPositionInLine(),
                    stop.getLine(), stop.getCharPositionInLine(),
                    format
            ), args);
        }
    }

    private void error(Token token, String format, Object... args) {
        System.out.printf(String.format("[%d:%d] %s %n",
                token.getLine(),
                token.getCharPositionInLine(),
                format
        ), args);
    }

    @Override
    public Type visit(Program program) {
        // TODO We need to build a type table or something.
        // TODO Check for duplicate classes. Maybe do so in the visit(ClassDeclaration) by saving program as instance?
        program.getMainClass().accept(this);
        program.getClasses().forEach(c -> c.accept(this));
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
        Type condition = statement.getCondition().accept(this);
        if (!condition.isBoolean()) {
            error(statement.getCondition().getStart(), statement.getCondition().getStop(), "Expected a boolean. Got a(n) %s.", condition.getName());
        }

        statement.getTrueStatement().accept(this);
        if (statement.hasFalseStatement()) statement.getFalseStatement().accept(this);

        return null;
    }

    @Override
    public Type visit(While statement) {
        Type condition = statement.getCondition().accept(this);
        if (!condition.isBoolean()) {
            error(statement.getCondition().getStart(), statement.getCondition().getStop(), "Expected a boolean. Got a(n) %s.", condition.getName());
        }

        statement.getStatement().accept(this);
        return null;
    }

    @Override
    public Type visit(Print statement) {
        Type type = statement.getExpression().accept(this);
        if (!type.isInt()) {
            error(statement.getExpression().getStart(), statement.getExpression().getStop(), "Expected an integer. Got a(n) %s.", type.getName());
        }

        return null;
    }

    @Override
    public Type visit(VarAssign statement) {
        Type variable = statement.getVariable().accept(this);
        Type expression = statement.getValue().accept(this);

        if (!variable.equals(expression)) {
            error(statement.getStart(), statement.getStop(), "Type mismatch. Trying to assign a(n) %s to a(n) %s variable.", expression.getName(), variable.getName());
            setUnknown(statement.getVariable());
        }

        return null;
    }

    @Override
    public Type visit(ArrayAssign statement) {
        Type array = statement.getArray().accept(this);
        Type index = statement.getIndex().accept(this);
        Type value = statement.getValue().accept(this);

        if (!array.isIntArray()) {
            error(statement.getArray().getStart(), statement.getArray().getStop(), "Expected an integer array. Got a(n) %s.", array.getName());
        }
        if (!index.isInt()) {
            error(statement.getIndex().getStart(), statement.getIndex().getStop(), "Expected an integer. Got a(n) %s.", index.getName());
        }
        if (!value.isInt()) {
            error(statement.getValue().getStart(), statement.getValue().getStop(), "Expected an integer. Got a(n) %s.", value.getName());
        }

        return null;
    }

    @Override
    public Type visit(And expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);

        if (!left.isBoolean()) {
            error(expression.getLeft().getStart(), expression.getLeft().getStop(), "Expected a boolean. Got a(n) %s.", left.getName());
        }

        if (!right.isBoolean()) {
            error(expression.getRight().getStart(), expression.getRight().getStop(), "Expected a boolean. Got a(n) %s.", right.getName());
        }

        if (right.isBoolean() && left.isBoolean()) {
            return new BooleanType();
        }

        return new UnknownType();
    }

    @Override
    public Type visit(Not expression) {
        Type type = expression.getExpression().accept(this);
        if (!type.isBoolean()) {
            error(expression.getExpression().getStart(), expression.getExpression().getStop(), "Expected a boolean. Got a(n) %s.", type.getName());
            return new UnknownType();
        }
        return new BooleanType();
    }

    @Override
    public Type visit(LessThan expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);

        if (!left.isInt()) {
            error(expression.getLeft().getStart(), expression.getLeft().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (!right.isInt()) {
            error(expression.getRight().getStart(), expression.getRight().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (right.isBoolean() && left.isBoolean()) {
            return new BooleanType();
        }

        return new UnknownType();
    }

    @Override
    public Type visit(Plus expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);

        if (!left.isInt()) {
            error(expression.getLeft().getStart(), expression.getLeft().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (!right.isInt()) {
            error(expression.getRight().getStart(), expression.getRight().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (right.isBoolean() && left.isBoolean()) {
            return new IntegerType();
        }

        return new UnknownType();
    }

    @Override
    public Type visit(Minus expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);

        if (!left.isInt()) {
            error(expression.getLeft().getStart(), expression.getLeft().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (!right.isInt()) {
            error(expression.getRight().getStart(), expression.getRight().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (right.isBoolean() && left.isBoolean()) {
            return new IntegerType();
        }

        return new UnknownType();
    }

    @Override
    public Type visit(Times expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);

        if (!left.isInt()) {
            error(expression.getLeft().getStart(), expression.getLeft().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (!right.isInt()) {
            error(expression.getRight().getStart(), expression.getRight().getStop(), "Expected an integer. Got a(n) %s.", left.getName());
        }

        if (right.isBoolean() && left.isBoolean()) {
            return new IntegerType();
        }

        return new UnknownType();
    }

    @Override
    public Type visit(ArrayLookup expression) {
        Type array = expression.getArray().accept(this);
        Type index = expression.getIndex().accept(this);

        if (!array.isIntArray()) {
            error(expression.getArray().getStart(), expression.getArray().getStop(), "Expected an integer array. Got a(n) %s.", array.getName());
        }

        if (!index.isInt()) {
            error(expression.getIndex().getStart(), expression.getIndex().getStop(), "Expected an integer. Got a(n) %s.", index.getName());
        }

        if (array.isIntArray() && index.isInt()) {
            return new IntegerType();
        }

        return new UnknownType();
    }

    @Override
    public Type visit(ArrayLength expression) {
        Type array = expression.getArray().accept(this);

        if (!array.isIntArray()) {
            error(expression.getArray().getStart(), expression.getArray().getStop(), "Expected an integer array. Got a(n) %s.", array.getName());
            return new UnknownType();
        }

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
        Type length = expression.getLength().accept(this);

        if (!length.isInt()) {
            error(expression.getLength().getStart(), expression.getLength().getStop(), "Expected an integer. Got a(n) %s.", length.getName());
        }

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
        Type type;

        if (parameters.containsKey(identifier)) {
            type = parameters.get(identifier);
        } else if (method.containsKey(identifier)) {
            type = method.get(identifier);
        } else {
            type = instance.get(identifier);
        }

        if (type == null) {
            error(identifier.getStart(), "The variable %s has not been declared.", identifier);
            return new UnknownType();
        }

        return type;
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
}
