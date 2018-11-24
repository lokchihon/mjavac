package com.nvankempen.csc444.mjava.codegen;

import com.nvankempen.csc444.mjava.ast.nodes.*;

public interface CodeGenerationVisitor {

    // Expressions
    void visit(ArrayLookup           expression);
    void visit(ArrayLength           expression);
    void visit(Call                  expression);
    void visit(Not                   expression);
    void visit(NewArray              expression);
    void visit(NewObject             expression);
    void visit(Times                 expression);
    void visit(Plus                  expression);
    void visit(Minus                 expression);
    void visit(LessThan              expression);
    void visit(And                   expression);
    void visit(IntegerLiteral        expression);
    void visit(BooleanLiteral        expression);
    void visit(Identifier            expression);
    void visit(This                  expression);

    // Statements
    void visit(Block                statement  );
    void visit(If                   statement  );
    void visit(While                statement  );
    void visit(Print                statement  );
    void visit(VarAssign            statement  );
    void visit(ArrayAssign          statement  );

    // Declarations
    void visit(Program              declaration);
    void visit(MainClass            declaration);
    void visit(ClassDeclaration     declaration);
    void visit(MethodDeclaration    declaration);
}
