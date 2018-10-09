parser grammar MiniJavaParser;

options {
    tokenVocab = MiniJavaLexer;
}

program
    : mainClass ( classDeclaration )* EOF
;

mainClass
    : CLASS IDENTIFIER LC PUBLIC STATIC VOID MAIN LP STRING LB RB IDENTIFIER RP LC statement RC RC
;

classDeclaration
    : CLASS IDENTIFIER ( EXTENDS IDENTIFIER )? LC ( varDeclaration )* ( methodDeclaration )* RC
;

varDeclaration
    : type IDENTIFIER SEMICOLON     # TypedDeclaration
    | VAR IDENTIFIER SEMICOLON      # UnTypedDeclaration
;

methodDeclaration
    : PUBLIC type IDENTIFIER LP ( type IDENTIFIER ( COMMA type IDENTIFIER )* )? RP LC ( varDeclaration )* ( statement )* RETURN expression SEMICOLON RC
;

type
    : INT LB RB
    | BOOLEAN
    | INT
    | IDENTIFIER
;

statement
    : LC ( statement )* RC
    | IF LP expression RP statement ( ELSE statement )?
    | WHILE LP expression RP statement
    | PRINT LP expression RP SEMICOLON
    | IDENTIFIER EQ expression SEMICOLON
    | IDENTIFIER LB expression RB EQ expression SEMICOLON
;

// Having declarations is this order makes ANTLR figure out precedence and left recursion.
// We use labels on each expression so that we can differentiate them later.
expression
    : expression LB expression RB                                               # ArrayAccess
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
