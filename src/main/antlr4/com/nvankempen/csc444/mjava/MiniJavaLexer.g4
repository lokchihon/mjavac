lexer grammar MiniJavaLexer;

CLASS:              'class';
EXTENDS:            'extends';
RETURN:             'return';
NEW:                'new';

PUBLIC:             'public';
STATIC:             'static';
VOID:               'void';

IF:                 'if';
ELSE:               'else';
WHILE:              'while';

INT:                'int';
BOOLEAN:            'boolean';
VAR:                'var';

LP:                 '(';
RP:                 ')';
LC:                 '{';
RC:                 '}';
LB:                 '[';
RB:                 ']';

DOT:                '.';
SEMICOLON:          ';';
COMMA:              ',';

EQ:                 '=';
AND:                '&&';
LT:                 '<';
PLUS:               '+';
MINUS:              '-';
TIMES:              '*';
NOT:                '!';

MAIN:               'main';
STRING:             'String';
PRINT:              'System.out.println';
LENGTH:             'length';
THIS:               'this';

BOOLEAN_LITERAL:    'true' | 'false';
INTEGER_LITERAL:    [0-9]+;
IDENTIFIER:         [a-zA-Z_$] [a-zA-Z0-9_$]*;

WS:                 [ \t\r\n\u000C]+    -> skip;
COMMENT:            '//' ~[\r\n]*       -> skip;
