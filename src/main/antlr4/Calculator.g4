grammar Calculator;

@header {
    package com.senspond.exp.alntlr;
}
expression :
    expression op=(MUL | DIV) expression #mulDiv
    | expression op=(ADD | SUB) expression #addSub
    | n #num
    | '(' expression ')' #parens
;

n : NUMBER;
MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';
NUMBER : ('0'..'9')+ ('.' ('0'..'9')+)?;
WS : [ \t\r\n]+ -> skip;