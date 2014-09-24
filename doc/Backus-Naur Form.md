Pied syntax
====
Described in Extended Backus-Naur Form, as understood by an amateur.

This is not here for rigour but more as a part of the planning documentation -
a more formal way to keep track of what the hell I was trying to do.

I have no idea how to describe "any arbitrary unicode character that
is not already a symbol" in EBNF. Help me Github, you're my only hope.

____________________________________
Still a Work In Progress
----

block ::= '{' statement* '}' .

statement ::= (expression | operator ('(' statement* ')')? operator? ) ';' .

expression ::= list? operator+ list | term .

group ::= '(' list ')' .

list ::= term (',' term)* .

//An expression is anything that can be simplified to a value or one action, and
a term is anything that can be used in an expression - so *a block of code can
be a term since it is a first-class citizen in this language*.

term ::= declaration | identifier | call | literal | block | expression | group .

declaration ::= identifier identifier (',' identifier)*

call ::= identifier group .

identifier ::= letter alphanumeric+ ('.' letter alphanumeric+)* .

literal ::= number | quote <anything> quote .

operator ::= <host-defined> .
(known to include '=', 'return', 'if', and others)

alphanumeric ::= letter | digit .

quote ::= " | ' .

letter ::= 'A' | 'B' | 'C' ... 'Z' | 'a' | 'b' | 'c' ... 'z' | '_' .

number ::= integer | integer '.' digit+ . 

integer ::= '-'? digit+ .

digit ::= '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' .
