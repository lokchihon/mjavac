package com.nvankempen.csc444.mjava.parser;

import static com.nvankempen.csc444.mjava.MiniJavaParser.*;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseVisitor;
import com.nvankempen.csc444.mjava.ast.AST;
import com.nvankempen.csc444.mjava.ast.nodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VisitorParser extends Parser {
    @Override
    public AST parse(ParseTree input) throws IOException {
        ProgramVisitor visitor = new ProgramVisitor();
        Program program = visitor.visit(input);
        return new AST(program);
    }

    private static final class ProgramVisitor extends MiniJavaParserBaseVisitor<Program> {
        @Override
        public Program visitProgram(ProgramContext ctx) {
            MainClass main = ctx.mainClass().accept(new MainClassVisitor());

            List<ClassDeclaration> classes = ctx.classDeclaration()
                    .stream().map(declaration -> declaration.accept(new ClassDeclarationVisitor()))
                    .collect(Collectors.toList());

            return new Program(main, new ClassDeclarationList(classes));
        }
    }

    private static final class MainClassVisitor extends MiniJavaParserBaseVisitor<MainClass> {
        @Override
        public MainClass visitMainClass(MainClassContext ctx) {
            Identifier name = new Identifier(ctx.IDENTIFIER().getText());
            Statement statement = ctx.mainMethod().statement().accept(new StatementVisitor());

            return new MainClass(name, statement);
        }
    }

    private static final class ClassDeclarationVisitor extends MiniJavaParserBaseVisitor<ClassDeclaration> {
        @Override
        public ClassDeclaration visitClassDeclaration(ClassDeclarationContext ctx) {
            Identifier name = new Identifier(ctx.IDENTIFIER(0).getText());
            List<VarDeclaration> variables = ctx.varDeclaration().stream()
                    .map(declaration -> declaration.accept(new VarDeclarationVisitor()))
                    .collect(Collectors.toList());

            List<MethodDeclaration> methods = ctx.methodDeclaration().stream()
                    .map(declaration -> declaration.accept(new MethodDeclarationVisitor()))
                    .collect(Collectors.toList());

            if (ctx.EXTENDS() == null) {
                return new ClassDeclaration(name, new VarDeclarationList(variables), new MethodDeclarationList(methods));
            }

            Identifier superclass = new Identifier(ctx.IDENTIFIER(1).getText());
            return new ClassDeclaration(name, superclass, new VarDeclarationList(variables), new MethodDeclarationList(methods));
        }
    }

    private static final class MethodDeclarationVisitor extends MiniJavaParserBaseVisitor<MethodDeclaration> {
        @Override
        public MethodDeclaration visitMethodDeclaration(MethodDeclarationContext ctx) {
            Type type = ctx.type().accept(new TypeVisitor());
            Identifier name = new Identifier(ctx.IDENTIFIER().getText());

            List<Formal> parameters = new ArrayList<>();
            for (int i = 0; i < ctx.parameters().type().size(); ++i) {
                parameters.add(new Formal(
                        ctx.parameters().type(i).accept(new TypeVisitor()),
                        new Identifier(ctx.parameters().IDENTIFIER(i).getText())
                ));
            }

            List<VarDeclaration> variables = ctx.varDeclaration().stream()
                    .map(declaration -> declaration.accept(new VarDeclarationVisitor()))
                    .collect(Collectors.toList());

            List<Statement> statements = ctx.statement().stream()
                    .map(statement -> statement.accept(new StatementVisitor()))
                    .collect(Collectors.toList());

            Expression ret = ctx.expression().accept(new ExpressionVisitor());

            return new MethodDeclaration(
                    type, name,
                    new FormalList(parameters),
                    new VarDeclarationList(variables),
                    new StatementList(statements),
                    ret
            );
        }
    }

    private static final class VarDeclarationVisitor extends MiniJavaParserBaseVisitor<VarDeclaration> {
        @Override
        public VarDeclaration visitTypedDeclaration(TypedDeclarationContext ctx) {
            Type type = ctx.type().accept(new TypeVisitor());
            Identifier name = new Identifier(ctx.IDENTIFIER().getText());
            return new VarDeclaration(type, name);
        }

        @Override
        public VarDeclaration visitUnTypedDeclaration(UnTypedDeclarationContext ctx) {
            Identifier name = new Identifier(ctx.IDENTIFIER().getText());
            Expression value = ctx.expression().accept(new ExpressionVisitor());
            return new UnTypedVarDeclaration(name, value);
        }
    }

    private static final class StatementVisitor extends MiniJavaParserBaseVisitor<Statement> {
        @Override
        public Statement visitStatementBlock(StatementBlockContext ctx) {
            return new Block(new StatementList(
                    ctx.statement().stream()
                            .map(statement -> statement.accept(new StatementVisitor()))
                            .collect(Collectors.toList())
            ));
        }

        @Override
        public Statement visitIfStatement(IfStatementContext ctx) {
            Expression condition = ctx.expression().accept(new ExpressionVisitor());
            Statement statement1 = ctx.statement(0).accept(new StatementVisitor());

            if (ctx.ELSE() == null) {
                return new If(condition, statement1);
            }

            Statement statement2 = ctx.statement(1).accept(new StatementVisitor());

            return new If(condition, statement1, statement2);
        }

        @Override
        public Statement visitWhileStatement(WhileStatementContext ctx) {
            Expression condition = ctx.expression().accept(new ExpressionVisitor());
            Statement statement = ctx.statement().accept(new StatementVisitor());
            return new While(condition, statement);
        }

        @Override
        public Statement visitPrintStatement(PrintStatementContext ctx) {
            Expression expression = ctx.expression().accept(new ExpressionVisitor());
            return new Print(expression);
        }

        @Override
        public Statement visitVarAssignStatement(VarAssignStatementContext ctx) {
            Identifier variable = new Identifier(ctx.IDENTIFIER().getText());
            Expression value = ctx.expression().accept(new ExpressionVisitor());
            return new VarAssign(variable, value);
        }

        @Override
        public Statement visitArrayAssignStatement(ArrayAssignStatementContext ctx) {
            Identifier variable = new Identifier(ctx.IDENTIFIER().getText());
            Expression index = ctx.expression(0).accept(new ExpressionVisitor());
            Expression value = ctx.expression(1).accept(new ExpressionVisitor());

            return new ArrayAssign(variable, index, value);
        }
    }

    private static final class ExpressionVisitor extends MiniJavaParserBaseVisitor<Expression> {
        @Override
        public Expression visitArrayLookup(ArrayLookupContext ctx) {
            Expression array = ctx.expression(0).accept(new ExpressionVisitor());
            Expression index = ctx.expression(1).accept(new ExpressionVisitor());
            return new ArrayLookup(array, index);
        }

        @Override
        public Expression visitArrayLength(ArrayLengthContext ctx) {
            Expression array = ctx.expression().accept(new ExpressionVisitor());
            return new ArrayLength(array);
        }

        @Override
        public Expression visitMethodCall(MethodCallContext ctx) {
            Expression variable = ctx.expression(0).accept(new ExpressionVisitor());
            Identifier method = new Identifier(ctx.IDENTIFIER().getText());

            List<Expression> arguments = ctx.expression().stream().skip(1)
                    .map(argument -> argument.accept(new ExpressionVisitor()))
                    .collect(Collectors.toList());

            return new Call(variable, method, new ExpressionList(arguments));
        }

        @Override
        public Expression visitNot(NotContext ctx) {
            Expression expression = ctx.expression().accept(new ExpressionVisitor());
            return new Not(expression);
        }

        @Override
        public Expression visitNewArray(NewArrayContext ctx) {
            Expression length = ctx.expression().accept(new ExpressionVisitor());
            return new NewArray(length);
        }

        @Override
        public Expression visitNewObject(NewObjectContext ctx) {
            Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
            return new NewObject(identifier);
        }

        @Override
        public Expression visitTimes(TimesContext ctx) {
            Expression exp1 = ctx.expression(0).accept(new ExpressionVisitor());
            Expression exp2 = ctx.expression(1).accept(new ExpressionVisitor());
            return new Times(exp1, exp2);
        }

        @Override
        public Expression visitPlus(PlusContext ctx) {
            Expression exp1 = ctx.expression(0).accept(new ExpressionVisitor());
            Expression exp2 = ctx.expression(1).accept(new ExpressionVisitor());
            return new Plus(exp1, exp2);
        }

        @Override
        public Expression visitMinus(MinusContext ctx) {
            Expression exp1 = ctx.expression(0).accept(new ExpressionVisitor());
            Expression exp2 = ctx.expression(1).accept(new ExpressionVisitor());
            return new Minus(exp1, exp2);
        }

        @Override
        public Expression visitLessThan(LessThanContext ctx) {
            Expression exp1 = ctx.expression(0).accept(new ExpressionVisitor());
            Expression exp2 = ctx.expression(1).accept(new ExpressionVisitor());
            return new LessThan(exp1, exp2);
        }

        @Override
        public Expression visitAnd(AndContext ctx) {
            Expression exp1 = ctx.expression(0).accept(new ExpressionVisitor());
            Expression exp2 = ctx.expression(1).accept(new ExpressionVisitor());
            return new And(exp1, exp2);
        }

        @Override
        public Expression visitInteger(IntegerContext ctx) {
            return new IntegerLiteral(Integer.parseInt(
                    ctx.INTEGER_LITERAL().getText()
            ));
        }

        @Override
        public Expression visitBoolean(BooleanContext ctx) {
            return new BooleanLiteral(Boolean.parseBoolean(
                    ctx.BOOLEAN_LITERAL().getText()
            ));
        }

        @Override
        public Expression visitIdentifier(IdentifierContext ctx) {
            return new Identifier(ctx.IDENTIFIER().getText());
        }

        @Override
        public Expression visitThis(ThisContext ctx) {
            return new This();
        }

        @Override
        public Expression visitParenthesis(ParenthesisContext ctx) {
            Expression expression = ctx.expression().accept(new ExpressionVisitor());
            return expression;
        }

    }

    private static final class TypeVisitor extends MiniJavaParserBaseVisitor<Type> {
        @Override
        public Type visitIntArrayType(IntArrayTypeContext ctx) {
            return new IntegerArrayType();
        }

        @Override
        public Type visitBooleanType(BooleanTypeContext ctx) {
            return new BooleanType();
        }

        @Override
        public Type visitIntType(IntTypeContext ctx) {
            return new IntegerType();
        }

        @Override
        public Type visitIdentifierType(IdentifierTypeContext ctx) {
            return new IdentifierType(new Identifier(ctx.IDENTIFIER().getText()));
        }
    }
}
