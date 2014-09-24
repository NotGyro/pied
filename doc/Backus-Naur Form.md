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

statement ::= (expression | expression block) ';' .

expression ::= operator* term | (terms? operator terms) | term .

group ::= '(' terms ')' .

terms ::= term (',' term)* .

term ::= identifier | declaration | call | literal | block | expression | group .

call ::= identifier group .

signature ::= '(' (declaration (',' declaration)*)? ')' .
declaration ::= operator* identifier identifier signature? .

identifier ::= letter alphanumeric+ ('.' letter alphanumeric+)* .

literal ::= number | quote <anything> quote

operator ::= <host-defined>
(known to include '=', 'return', 'if', and others)

alphanumeric ::= letter | digit .

quote ::= " | '
letter ::= 'A' | 'B' | 'C' ... 'Z' | 'a' | 'b' | 'c' ... 'z' | '_' .
number ::= integer | integer '.' digit+ . 
integer ::= '-'? digit+ .
digit ::= '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' .
