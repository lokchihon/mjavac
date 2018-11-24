package com.nvankempen.csc444.mjava.ast.nodes;

import com.nvankempen.csc444.mjava.ast.analysis.TypeVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.Visitor;
import com.nvankempen.csc444.mjava.ast.utils.Type;
import com.nvankempen.csc444.mjava.ast.utils.UnknownType;
import com.nvankempen.csc444.mjava.codegen.CodeGenerationVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Call extends Expression {
    private Expression object;
    private MethodDeclaration declaration;
    private ClassDeclaration objectType;
    private Identifier method;
    private List<Expression> arguments;

    public MethodDeclaration getDeclaration() {
        return declaration;
    }

    public void setDeclaration(MethodDeclaration declaration) {
        this.declaration = declaration;
    }

    public ClassDeclaration getObjectType() {
        return objectType;
    }

    public void setObjectType(ClassDeclaration objectType) {
        this.objectType = objectType;
    }

    public Call (Expression object, Identifier method, List<Expression> arguments, Token start, Token stop) {
        super(start, stop);
        this.object = object;
        this.method = method;
        this.arguments = arguments;
    }

    public Expression getObject() {
        return object;
    }

    public Identifier getMethod() {
        return method;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(CodeGenerationVisitor v) {
        v.visit(this);
    }
}
