package com.nvankempen.csc444.mjava.codegen;

import com.nvankempen.csc444.mjava.ast.nodes.*;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import org.antlr.v4.runtime.misc.Pair;
import soot.util.JasminOutputStream;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CodeGenVisitor implements CodeGenerationVisitor {

    // Language constants
    private static final int METHOD_MAX_STACK_SIZE = 200;

    // Code generation constants
    private static final String INDENT = "\t";

    private Path outputdir;
    private PrintStream out;
    private HashMap<Identifier, Pair<Integer, Type>> locals = new HashMap<>();

    // References
    private ClassDeclaration cclass;
    private Program cprogram;

    // counters
    private int indent;
    private int labels;
    private int local;

    public CodeGenVisitor(Path outputdir) {
        this.outputdir = outputdir;
    }

    private void println(String format, Object... args) {
        for (int i = 0; i < indent; ++i) {
            out.print(INDENT);
        }

        out.println(String.format(format, args));
    }

    @Override
    public void visit(ArrayLookup expression) {
        expression.getArray().accept(this);
        expression.getIndex().accept(this);
        println("iaload");
    }

    @Override
    public void visit(ArrayLength expression) {
        expression.getArray().accept(this);
        println("arraylength");
    }

    @Override
    public void visit(Call expression) {
        expression.getObject().accept(this);
        expression.getArguments().forEach(a -> a.accept(this));
        println("invokevirtual %s/%s(%s)%s",
                expression.getObjectType().getName(),
                expression.getDeclaration().getName(),
                expression.getDeclaration().getParameters().stream().map(f -> f.getType().toDescriptor()).reduce((a, b) -> a + b).orElse(""),
                expression.getDeclaration().getType().toDescriptor()
        );
    }

    @Override
    public void visit(Not expression) {
        expression.getExpression().accept(this);

        int lab1 = ++labels;
        int lab2 = ++labels;
        int lab3 = ++labels;

        println("ifeq label%d", lab1);
        println("goto label%d", lab2);
        println("label%d:", lab1);
        ++indent; println("ldc 1"); println("goto label%d", lab3); --indent;
        println("label%d:", lab2);
        ++indent; println("ldc 0"); println("goto label%d", lab3); --indent;
        println("label%d:", lab3);
    }

    @Override
    public void visit(NewArray expression) {
        expression.getLength().accept(this);
        println("newarray int");
    }

    @Override
    public void visit(NewObject expression) {
        println("new %s", expression.getClassName().getName());
        println("dup");
        println("invokespecial %s/<init>()V", expression.getClassName().getName());
    }

    @Override
    public void visit(Times expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        println("imul");
    }

    @Override
    public void visit(Plus expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        println("iadd");
    }

    @Override
    public void visit(Minus expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        println("isub");
    }

    @Override
    public void visit(LessThan expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        int lab1 = ++labels;
        int lab2 = ++labels;
        int lab3 = ++labels;

        println("if_icmplt label%d", lab1);
        println("goto label%d", lab2);
        println("label%d:", lab1);
        ++indent; println("ldc 1"); println("goto label%d", lab3); --indent;
        println("label%d:", lab2);
        ++indent; println("ldc 0"); println("goto label%d", lab3); --indent;
        println("label%d:", lab3);
    }

    @Override
    public void visit(And expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        println("iand");
    }

    @Override
    public void visit(IntegerLiteral expression) {
        println("ldc %s", expression.getValue());
    }

    @Override
    public void visit(BooleanLiteral expression) {
        println("ldc %s", expression.getValue() ? "1" : "0");
    }

    @Override
    public void visit(Identifier expression) {
        if (locals.get(expression) != null) {
            if (locals.get(expression).b.isInt() || locals.get(expression).b.isBoolean()) {
                println("iload %d", locals.get(expression).a);
            } else {
                println("aload %d", locals.get(expression).a);
            }
        } else {
            ClassDeclaration c = cclass;
            while (c != null) {
                for (VarDeclaration variable : c.getVariables()) {
                    if (variable.getName().equals(expression)) {
                        println("aload_0");
                        println("getfield %s/%s %s", c.getName().getName(), expression.getName(), variable.getType().toDescriptor());
                        return;
                    }
                }

                if (c.hasSuperClass()) {
                    for (ClassDeclaration declaration : cprogram.getClasses()) {
                        if (c.getSuperclass().equals(declaration.getName())) {
                            c = declaration;
                            break;
                        }
                    }
                } else {
                    c = null;
                }
            }
        }
    }

    @Override
    public void visit(This expression) {
        println("aload_0");
    }

    @Override
    public void visit(Block statement) {
        ++indent;
        statement.getStatements().forEach(s -> s.accept(this));
        --indent;
    }

    @Override
    public void visit(If statement) {
        statement.getCondition().accept(this);

        int iflab   = ++labels;
        int elselab = ++labels;
        int donelab = ++labels;

        println("ifne label%d", iflab);
        println("goto label%d", elselab);

        println("label%d:", iflab);
        ++indent;
            statement.getTrueStatement().accept(this);
            println("goto label%d", donelab);
        --indent;

        println("label%d:", elselab);
        ++indent;
            statement.getFalseStatement().accept(this);
            println("goto label%d", donelab);
        --indent;

        println("label%d:", donelab);
    }

    @Override
    public void visit(While statement) {
        int whilelab = ++labels;
        int donelab  = ++labels;

        println("label%d:", whilelab);
        ++indent;
            statement.getCondition().accept(this);
            println("ifeq label%d", donelab);
            statement.getStatement().accept(this);
            println("goto label%d", whilelab);
        --indent;

        println("label%d:", donelab);
    }

    @Override
    public void visit(Print statement) {
        println("getstatic java/lang/System/out Ljava/io/PrintStream;");
        statement.getExpression().accept(this);
        println("invokevirtual java/io/PrintStream/println(I)V");
    }

    @Override
    public void visit(VarAssign statement) {
        if (locals.get(statement.getVariable()) != null) {
            statement.getValue().accept(this);
            if (locals.get(statement.getVariable()).b.isInt() || locals.get(statement.getVariable()).b.isBoolean()) {
                println("istore %d", locals.get(statement.getVariable()).a);
            } else {
                println("astore %d", locals.get(statement.getVariable()).a);
            }
        } else {
            ClassDeclaration c = cclass;
            while (c != null) {
                for (VarDeclaration variable : c.getVariables()) {
                    if (variable.getName().equals(statement.getVariable())) {
                        println("aload_0");
                        statement.getValue().accept(this);
                        println("putfield %s/%s %s", c.getName().getName(), variable.getName().getName(), variable.getType().toDescriptor());
                        return;
                    }
                }

                if (c.hasSuperClass()) {
                    for (ClassDeclaration declaration : cprogram.getClasses()) {
                        if (c.getSuperclass().equals(declaration.getName())) {
                            c = declaration;
                            break;
                        }
                    }
                } else {
                    c = null;
                }
            }
        }
    }

    @Override
    public void visit(ArrayAssign statement) {
        if (locals.get(statement.getArray()) != null) {
            println("aload %d", locals.get(statement.getArray()).a);
            statement.getIndex().accept(this);
            statement.getValue().accept(this);
            println("iastore");
        } else {
            ClassDeclaration c = cclass;
            while (c != null) {
                for (VarDeclaration variable : c.getVariables()) {
                    if (variable.getName().equals(statement.getArray())) {
                        println("aload_0");
                        println("getfield %s/%s %s", c.getName().getName(), variable.getName().getName(), variable.getType().toDescriptor());
                        statement.getIndex().accept(this);
                        statement.getValue().accept(this);
                        println("iastore");
                        return;
                    }
                }

                if (c.hasSuperClass()) {
                    for (ClassDeclaration declaration : cprogram.getClasses()) {
                        if (c.getSuperclass().equals(declaration.getName())) {
                            c = declaration;
                            break;
                        }
                    }
                } else {
                    c = null;
                }
            }
        }
    }

    @Override
    public void visit(Program declaration) {
        cprogram = declaration;

        try {
            Path file = new File(outputdir.toFile(), declaration.getMainClass().getName().getName() + ".class").toPath();
            out = new PrintStream(new JasminOutputStream(new FileOutputStream(file.toFile())));
            declaration.getMainClass().accept(this);
            out.flush();
            out.close();
            for (ClassDeclaration c : declaration.getClasses()) {
                file = new File(outputdir.toFile(), c.getName().getName() + ".class").toPath();
                out = new PrintStream(new JasminOutputStream(new FileOutputStream(file.toFile())));
                c.accept(this);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(MainClass declaration) {
        println(".class %s", declaration.getName().getName());
        println(".super java/lang/Object");

        println(".method public <init>()V");
        ++indent;
            println("aload_0");
            println("invokespecial java/lang/Object/<init>()V");
            println("return");
        --indent;
        println(".end method");

        println(".method public static main([Ljava/lang/String;)V");
        ++indent;
            println(".limit stack %d", METHOD_MAX_STACK_SIZE);
            println(".limit locals 1");
            declaration.getStatement().accept(this);
            println("return");
        --indent;
        println(".end method");
    }

    @Override
    public void visit(ClassDeclaration declaration) {
        cclass = declaration;
        println(".class %s", declaration.getName().getName());
        println(".super %s", declaration.hasSuperClass() ? declaration.getSuperclass().getName() : "java/lang/Object");

        // Fields
        declaration.getVariables().forEach(v -> println(".field %s %s", v.getName(), v.getType().toDescriptor()));

        // Constructor
        println(".method public <init>()V");
        ++indent;
            println(".limit stack %d", METHOD_MAX_STACK_SIZE);
            println(".limit locals 1");
            println("aload_0");
            println("invokespecial %s/<init>()V", declaration.hasSuperClass() ? declaration.getSuperclass().getName() : "java/lang/Object");
            declaration.getVariables().forEach(v -> {
                if (v.hasValue()) {
                    println("aload_0");
                    v.getValue().accept(this);
                    println("putfield %s/%s %s", declaration.getName().getName(), v.getName().getName(), v.getType().toDescriptor());
                }
            });
            println("return");
        --indent;
        println(".end method");

        // Methods
        declaration.getMethods().forEach(m -> m.accept(this));
    }

    @Override
    public void visit(MethodDeclaration declaration) {
        locals.clear();
        local = 0;

        println(".method public %s(%s)%s",
                declaration.getName().getName(),
                declaration.getParameters().stream().map(p -> p.getType().toDescriptor()).reduce((a, b) -> a + b).orElse(""),
                declaration.getType().toDescriptor()
        );

        ++indent;
        println(".limit stack %d", METHOD_MAX_STACK_SIZE);
        println(".limit locals %d", declaration.getParameters().size() + declaration.getVariables().size() + 1);

        int methodlab = ++labels;
        int donelab   = ++labels;

        // Parameters
        declaration.getParameters().forEach(f -> {
            locals.put(f.getName(), new Pair<>(++local, f.getType()));
        });

        // Variable declarations
        declaration.getVariables().forEach(v -> {
            println(".var %d is %s %s from label%d to label%d", ++local, v.getName().getName(), v.getType().toDescriptor(), methodlab, donelab);
            if (v.hasValue()) {
                v.getValue().accept(this);
                if (v.getType().isInt() || v.getType().isBoolean()) {
                    println("istore %d", local);
                } else {
                    println("astore %d", local);
                }
            }
            locals.put(v.getName(), new Pair<>(local, v.getType()));
        });
        println("goto label%d", methodlab);

        // Statements
        println("label%d:", methodlab);
        ++indent;
            declaration.getStatements().forEach(s -> s.accept(this));
            println("goto label%d", donelab);
        --indent;

        // Return
        println("label%d:", donelab);
        ++indent;
            declaration.getReturn().accept(this);
            if (declaration.getType().isInt() || declaration.getType().isBoolean()) {
                println("ireturn");
            } else {
                println("areturn");
            }
        --indent;
        --indent;

        println(".end method");
    }
}
