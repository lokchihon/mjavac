grammar MiniJava;

Program
    : MainClass ( ClassDeclaration )* EOF
;

MainClass
    : 'class' IDENTIFIER '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' IDENTIFIER ')' '{' Statement '}' '}'
;

ClassDeclaration
    : 'class' IDENTIFIER ( 'extends' IDENTIFIER )? '{' ( VarDeclaration )* ( MethodDeclaration )* '}'
;

VarDeclaration
    : Type IDENTIFIER ';'
;

MethodDeclaration
    : 'public' Type IDENTIFIER '(' ( Type IDENTIFIER ( ',' Type IDENTIFIER )* )? ')' '{' ( VarDeclaration )* ( Statement )* 'return' Expression ';' '}'
;

Type
    : 'int' '[' ']'
    | 'boolean'
    | 'int'
    | IDENTIFIER
;

Statement
    : '{' ( Statement )* '}'
    | 'if' '(' Expression ')' Statement 'else' Statement
    | 'while' '(' Expression ')' Statement
    | 'System.out.println' '(' Expression ')' ';'
    | IDENTIFIER '=' Expression ';'
    | IDENTIFIER '[' Expression ']' '=' Expression ';'
;

Expression
    : Factor '&&' Expression
    | ArrayAccess
    | ArrayLength
    | MethodCall
;

Factor
    : Term '<' Factor
;

Term
    : Atom ('+' Term)*
;

Atom
    : Nucleus ('-' Atom)*
;

Nucleus
    : Proton ('*' Nucleus)*
;

Proton
    : '!' Quark
    | Quark
;

Quark
    : BOOLEAN_LITERAL
    | INTEGER_LITERAL
    | THIS
    | NewArray
    | NewObject
    | IDENTIFIER
    | '(' Expression ')'
;

NewArray
    : 'new' 'int' '[' Expression ']'
;

NewObject
    : 'new' IDENTIFIER '(' ')'
;

ArrayAccess
    : Quark '[' Expression ']'
;

ArrayLength
    : Quark '.' 'length'
;

MethodCall
    : Quark '.' IDENTIFIER '(' ( Expression ( ',' Expression )* )? ')'
;

IDENTIFIER
    : [a-zA-Z_$] [a-zA-Z0-9_$]*
;

THIS
    : 'this'
;

INTEGER_LITERAL
    : [0-9]+
;

BOOLEAN_LITERAL
    : 'true'
    | 'false'
;

WS
    : [ \r\t\n]+ -> skip
;
