package com.nvankempen.csc444.mjava.ast.analysis;

import com.nvankempen.csc444.mjava.ast.nodes.*;

import java.io.OutputStream;
import java.io.PrintStream;

public class PrintVisitor implements Visitor {

    private final PrintStream out;
    private int indent = 0;
    private String indentation = "\t";

    public PrintVisitor(OutputStream out) {
        this.out = new PrintStream(out);
    }

    private void print(String format, Object... args) {
        for (int i = 0; i < indent; ++i) {
            out.print(indentation);
        }

        out.printf(format, args);
    }

    private void printni(String format, Object... args) {
        out.printf(format, args);
    }

    private void printlnni(String format, Object... args) {
        out.printf(format, args);
        out.println();
    }

    private void println(String format, Object... args) {
        for (int i = 0; i < indent; ++i) {
            out.print(indentation);
        }

        out.printf(format, args);
        out.println();
    }

    @Override
    public void visit(Program program) {
        program.getMainClass().accept(this);
        program.getClasses().forEach(d -> d.accept(this));
    }

    @Override
    public void visit(ClassDeclaration declaration) {
        print("public class %s ", declaration.getName());
        if (declaration.hasSuperClass()) {
            printni("extends %s ", declaration.getSuperclass().getName());
        }
        printlnni("{");

        ++indent;
        for (VarDeclaration variable : declaration.getVariables()) {
            variable.accept(this);
        }

        for (MethodDeclaration method : declaration.getMethods()) {
            method.accept(this);
        }

        --indent;
        println("}");
    }

    @Override
    public void visit(RegularVarDeclaration declaration) {
        println("%s %s;", declaration.getType().getName(), declaration.getName());
    }

    @Override
    public void visit(UntypedVarDeclaration declaration) {
        print("var %s = ", declaration.getName());
        declaration.getValue().accept(this);
        printlnni(";");
    }

    @Override
    public void visit(MethodDeclaration declaration) {
        print("public %s %s (", declaration.getType().getName(), declaration.getName());
        printni("params");
        printlnni(") {");

        ++indent;
        for (VarDeclaration variable : declaration.getVariables()) {
            variable.accept(this);
        }

        for (Statement statement : declaration.getStatements()) {
            statement.accept(this);
        }

        print("return ");
        declaration.getReturn().accept(this);
        printlnni(";");
        --indent;

        println("}");
    }

    @Override
    public void visit(Block block) {
        println("{");
        ++indent;
        for (Statement statement : block.getStatements()) {
            statement.accept(this);
        }
        --indent;
        println("}");
    }

    @Override
    public void visit(If statement) {
        print("if(");
        statement.getCondition().accept(this);
        printlnni(")");
        ++indent;
        statement.getTrueStatement().accept(this);
        --indent;

        if (statement.hasFalseStatement()) {
            println("else ");
            ++indent;
            statement.getFalseStatement().accept(this);
            --indent;
        }
    }

    @Override
    public void visit(While statement) {
        print("while(");
        statement.getCondition().accept(this);
        printlnni(") ");

        statement.getStatement().accept(this);
    }

    @Override
    public void visit(Print statement) {
        print("System.out.println(");
        statement.getExpression().accept(this);
        printlnni(");");
    }

    @Override
    public void visit(VarAssign statement) {
        print("%s = ", statement.getVariable());
        statement.getValue().accept(this);
        printlnni(";");
    }

    @Override
    public void visit(ArrayAssign statement) {
        print("%s[", statement.getArray().getName());
        statement.getIndex().accept(this);
        printni("] = ");
        statement.getValue().accept(this);
        printlnni(";");
    }

    @Override
    public void visit(And expression) {
        expression.getLeft().accept(this);
        printni("&& ");
        expression.getRight().accept(this);
    }

    @Override
    public void visit(Not expression) {
        printni("!");
        expression.getExpression().accept(this);
    }

    @Override
    public void visit(LessThan expression) {
        expression.getLeft().accept(this);
        printni(" < ");
        expression.getRight().accept(this);
    }

    @Override
    public void visit(Plus expression) {
        expression.getLeft().accept(this);
        printni(" + ");
        expression.getRight().accept(this);
    }

    @Override
    public void visit(Minus expression) {
        expression.getLeft().accept(this);
        printni(" - ");
        expression.getRight().accept(this);
    }

    @Override
    public void visit(Times expression) {
        expression.getLeft().accept(this);
        printni(" * ");
        expression.getRight().accept(this);
    }

    @Override
    public void visit(ArrayLookup expression) {
        expression.getArray().accept(this);
        printni("[");
        expression.getIndex().accept(this);
        printni("]");
    }

    @Override
    public void visit(ArrayLength expression) {
        expression.getArray().accept(this);
        printni(".length");
    }

    @Override
    public void visit(Call expression) {
        expression.getObject().accept(this);
        printni(".%s(", expression.getMethod());
        int n = expression.getArguments().size();
        for (Expression argument : expression.getArguments()) {
            argument.accept(this);
            if (--n != 0) {
                printni(", ");
            }
        }
        printni(")");
    }

    @Override
    public void visit(IntegerLiteral expression) {
        printni(String.valueOf(expression.getValue()));
    }

    @Override
    public void visit(BooleanLiteral expression) {
        printni(String.valueOf(expression.getValue()));
    }

    @Override
    public void visit(NewArray expression) {
        printni("new int[");
        expression.getLength().accept(this);
        printni("]");
    }

    @Override
    public void visit(NewObject expression) {
        printni("new %s()", expression.getClassName());
    }

    @Override
    public void visit(This expression) {
        printni("this");
    }

    @Override
    public void visit(Identifier identifier) {
        printni("%s", identifier.getName());
    }

    @Override
    public void visit(Formal formal) {
        printni("%s %s", formal.getType().getName(), formal.getName());
    }

    @Override
    public void visit(MainClass main) {
        println("public class %s {", main.getName());

        ++indent;
        println("public static void main(String[] args) {");
        ++indent;
        main.getStatement().accept(this);
        --indent;
        println("}");
        --indent;
        println("}");
    }

    @Override
    public void visit(Type type) {
        printni(type.getName());
    }
}
