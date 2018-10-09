parser grammar MiniJavaParser;

options {
    tokenVocab = MiniJavaLexer;
}

program
    : mainClass ( classDeclaration )* EOF
;

mainClass
    : CLASS IDENTIFIER LC mainMethod RC
;

mainMethod
    : PUBLIC STATIC VOID MAIN LP STRING LB RB IDENTIFIER RP LC statement RC
;

classDeclaration
    : CLASS IDENTIFIER ( EXTENDS IDENTIFIER )? LC ( varDeclaration )* ( methodDeclaration )* RC
;

varDeclaration
    : type IDENTIFIER SEMICOLON                 # TypedDeclaration
    | VAR IDENTIFIER EQ expression SEMICOLON    # UnTypedDeclaration
;

methodDeclaration
    : PUBLIC type IDENTIFIER LP parameters RP LC ( varDeclaration )* ( statement )* RETURN expression SEMICOLON RC
;

parameters
    : ( type IDENTIFIER ( COMMA type IDENTIFIER )* )?
;

type
    : INT LB RB     # IntArrayType
    | BOOLEAN       # BooleanType
    | INT           # IntType
    | IDENTIFIER    # IdentifierType
;

statement
    : LC ( statement )* RC                                      # StatementBlock
    | IF LP expression RP statement ( ELSE statement )?         # IfStatement
    | WHILE LP expression RP statement                          # WhileStatement
    | PRINT LP expression RP SEMICOLON                          # PrintStatement
    | IDENTIFIER EQ expression SEMICOLON                        # VarAssignStatement
    | IDENTIFIER LB expression RB EQ expression SEMICOLON       # ArrayAssignStatement
;

// Having variables is this order makes ANTLR figure out precedence and left recursion.
// We use labels on each expression so that we can differentiate them later.
expression
    : expression LB expression RB                                               # ArrayLookup
    | expression DOT LENGTH                                                     # ArrayLength
    | expression DOT IDENTIFIER LP ( expression ( COMMA expression )* )? RP     # MethodCall
    | NOT expression                                                            # Not
    | NEW INT LB expression RB                                                  # NewArray
    | NEW IDENTIFIER LP RP                                                      # NewObject
    | expression TIMES expression                                               # Times
    | expression PLUS expression                                                # Plus
    | expression MINUS expression                                               # Minus
    | expression LT expression                                                  # LessThan
    | expression AND expression                                                 # And
    | INTEGER_LITERAL                                                           # Integer
    | BOOLEAN_LITERAL                                                           # Boolean
    | IDENTIFIER                                                                # Identifier
    | THIS                                                                      # This
    | LP expression RP                                                          # Parenthesis
;
