package org.ziglang.parsing.v1;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.ziglang.parsing.v1.psi.ZigTypes;
import org.ziglang.parsing.v1.ZigTokenType;

%%

%{
  public ZigLexer() { this((java.io.Reader) null); }
%}

%class ZigLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

OTHERWISE=[^]
WHITESPACE=[\s\n\f\r\t]+
SEMICOLON=;
COMMENT=\/\/[^\n]*

EOL=\R

ascii_char_not_nl_slash_squote=[\000-\011\013-\046\050-\133\135-\177]

ID=[A-Za-z_][A-Za-z0-9_]* | "@\"" {STRING_CHAR}* \"
BUILTIN_IDENTIFIER="@"[A-Za-z_][A-Za-z0-9_]*
CHAR_ESCAPE=\\x{hex}{hex}|\\u\{{hex}+}|\\[nrt\'\\\"]
char_char=[a-zA-Z_\U0000A0-\U10ffff] | {CHAR_ESCAPE} | {ascii_char_not_nl_slash_squote}
CHAR_LITERAL=\' {char_char} \'
STRING_CHAR={CHAR_ESCAPE}|[^\"\n]
STRING_LITERAL_SINGLE=\"{STRING_CHAR}*\"
STRING_MULTILINE=(\\\\ [^\n]* [ \n]*)*

bin= [01]
hex = [0-9a-fA-F]
bin_= '_'? {bin}
oct = [0-7]
oct_ = '_'? {oct}
hex_ = '_'? {hex}
dec = [0-9]
dec_ = '_'? {dec}

bin_int = [01] [01_]*
oct_int = [0-7] [0-7_]*
dec_int = [0-9] [0-9_]*
hex_int = [0-9a-fA-F] [0-9a-fA-F_]*

FLOAT=
 "0x" {hex_int} "." {hex_int} ([pP] [-+]? {dec_int})?
 | {dec_int} "." {dec_int} ([eE] [-+]? {dec_int})?
 | "0x" {hex_int} [pP] [-+]? {dec_int}
 | {dec_int} [eE] [-+]? {dec_int}

INTEGER= "0b" {bin_int} | "0o" {oct_int} | "0x" {hex_int} | {dec_int}

SYMBOL_CHAR=[a-zA-Z_] // TODO
SYMBOL={SYMBOL_CHAR}({SYMBOL_CHAR}|\d)*

INCOMPLETE_STRING=c?\"([^\"\\\n]|\\[^])*
STRING_LITERAL={INCOMPLETE_STRING}\"

INCOMPLETE_CHAR='([^'\\\n]|\\[^])*
CHAR_LITERAL={INCOMPLETE_CHAR}'

%state AFTER_AT

%%

{COMMENT} { return ZigTokenType.LINE_COMMENT; }
{WHITESPACE} { return TokenType.WHITE_SPACE; }

<AFTER_AT> {SYMBOL} { yybegin(YYINITIAL); return ZigTypes.BUILTIN_FUNCTION; }
<AFTER_AT> [^] { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

\<\<= { return ZigTypes.SHL_ASSIGN_SYM; } // <<=
>>= { return ZigTypes.SHR_ASSIGN_SYM; }
=> { return ZigTypes.ARROW_SYM; }
-> { return ZigTypes.SMALL_ARROW_SYM; }

\+%= { return ZigTypes.PLUS_MOD_ASSIGN_SYM; }
\-%= { return ZigTypes.MINUS_MOD_ASSIGN_SYM; }
\*%= { return ZigTypes.STAR_MOD_ASSIGN_SYM; }
//\/%= { return ZigTypes.DIV_MOD_ASSIGN_SYM; }

\+% { return ZigTypes.PLUS_MOD_SYM; }
\-% { return ZigTypes.MINUS_MOD_SYM; }
\*% { return ZigTypes.STAR_SYM; }

\<< { return ZigTypes.SHL_SYM; }
\>> { return ZigTypes.SHR_SYM; }

\+= { return ZigTypes.PLUS_ASSIGN_SYM; }
\-= { return ZigTypes.MINUS_ASSIGN_SYM; }
\*= { return ZigTypes.STAR_ASSIGN_SYM; }
\/= { return ZigTypes.DIV_ASSIGN_SYM; }
\^= { return ZigTypes.EXPONENT_ASSIGN_SYM; }
\|= { return ZigTypes.OR_ASSIGN_SYM; }
\!= { return ZigTypes.UNEQUAL_SYM; }
\>= { return ZigTypes.GE_SYM; }
\<= { return ZigTypes.LE_SYM; }
&= { return ZigTypes.AND_ASSIGN_SYM; }
%= { return ZigTypes.MOD_ASSIGN_SYM; }
== { return ZigTypes.EQUAL_SYM; }

\+\+ { return ZigTypes.INC_SYM; }
// \-\- { return ZigTypes.DEC_SYM; }
\*\* { return ZigTypes.STAR_STAR_SYM; }
\|\| { return ZigTypes.SEP_SEP_SYM; }

\> { return ZigTypes.GT_SYM; }
\< { return ZigTypes.LT_SYM; }
\+ { return ZigTypes.PLUS_SYM; }
\- { return ZigTypes.MINUS_SYM; }
\* { return ZigTypes.STAR_SYM; }
\/ { return ZigTypes.DIV_SYM; }
\^ { return ZigTypes.EXPONENT_SYM; }
\| { return ZigTypes.SEP_SYM; }
\! { return ZigTypes.NOT_SYM; }
\?\? { return ZigTypes.VERY_QUESTION_SYM; } // 敲皮
\? { return ZigTypes.QUESTION_SYM; }
\~ { return ZigTypes.BITWISE_NOT_SYM; }
& { return ZigTypes.AND_SYM; }
% { return ZigTypes.MOD_SYM; }
= { return ZigTypes.EQ_SYM; }
@ { yybegin(AFTER_AT); return ZigTypes.AT_SYM; }

, { return ZigTypes.COMMA_SYM; }
{SEMICOLON} { return ZigTypes.SEMICOLON_SYM; }
: { return ZigTypes.COLON_SYM; }
\( { return ZigTypes.LEFT_PAREN; }
\) { return ZigTypes.RIGHT_PAREN; }
\{ { return ZigTypes.LEFT_BRACE; }
\} { return ZigTypes.RIGHT_BRACE; }
\[ { return ZigTypes.LEFT_BRACKET; }
\] { return ZigTypes.RIGHT_BRACKET; }

\.\.\. { return ZigTypes.RANGE_SYM; }
\.\. { return ZigTypes.SLICE_SYM; }
\. { return ZigTypes.DOT_SYM; }

{STRING_LITERAL} { return ZigTypes.STR; }
{INCOMPLETE_STRING} { return TokenType.BAD_CHARACTER; }
{CHAR_LITERAL} { return ZigTypes.CHAR_LITERAL; }
{INCOMPLETE_CHAR} { return TokenType.BAD_CHARACTER; }

{STRING_MULTILINE} { return ZigTypes.STR_MULTILINE; }

align { return ZigTypes.ALIGN_KEYWORD; }
allowzero { return ZigTypes.ALLOWZERO_KEYWORD; } // new
and { return ZigTypes.AND_KEYWORD; }
anyframe { return ZigTypes.ANYFRAME_KEYWORD; } // new
anytype { return ZigTypes.ANYTYPE_KEYWORD; } // new
asm { return ZigTypes.ASM_KEYWORD; }
async { return ZigTypes.ASYNC_KEYWORD; }
await { return ZigTypes.AWAIT_KEYWORD; }
break { return ZigTypes.BREAK_KEYWORD; }
callconv { return ZigTypes.CALLCONV_KEYWORD; } // new
catch { return ZigTypes.CATCH_KEYWORD; }
comptime { return ZigTypes.COMPTIME_KEYWORD; }
const { return ZigTypes.CONST_KEYWORD; }
continue { return ZigTypes.CONTINUE_KEYWORD; }
defer { return ZigTypes.DEFER_KEYWORD; }
else { return ZigTypes.ELSE_KEYWORD; }
enum { return ZigTypes.ENUM_KEYWORD; }
errdefer { return ZigTypes.DEFERROR_KEYWORD; }
error { return ZigTypes.ERROR_KEYWORD; }
export { return ZigTypes.EXPORT_KEYWORD; }
extern { return ZigTypes.EXTERN_KEYWORD; }
fn { return ZigTypes.FN_KEYWORD; }
for { return ZigTypes.FOR_KEYWORD; }
if { return ZigTypes.IF_KEYWORD; }
inline { return ZigTypes.INLINE_KEYWORD; }
linksection { return ZigTypes.LINKSECTION_KEYWORD; } // new
noalias { return ZigTypes.NOALIAS_KEYWORD; } // new
nosuspend { return ZigTypes.NOSUSPEND_KEYWORD; } // new
noinline { return ZigTypes.NOINLINE_KEYWORD; } // new
opaque { return ZigTypes.OPAQUE_KEYWORD; } // new
or { return ZigTypes.OR_KEYWORD; }
orelse { return ZigTypes.ORELSE_KEYWORD; }
packed { return ZigTypes.PACKED_KEYWORD; }
pub { return ZigTypes.PUB_KEYWORD; }
resume { return ZigTypes.RESUME_KEYWORD; }
return { return ZigTypes.RETURN_KEYWORD; }
struct { return ZigTypes.STRUCT_KEYWORD; }
suspend { return ZigTypes.SUSPEND_KEYWORD; }
switch { return ZigTypes.SWITCH_KEYWORD; }
test { return ZigTypes.TEST_KEYWORD; }
threadlocal { return ZigTypes.THREADLOCAL_KEYWORD; } // new
try { return ZigTypes.TRY_KEYWORD; }
union { return ZigTypes.UNION_KEYWORD; }
unreachable { return ZigTypes.UNREACHABLE_KEYWORD; }
usingnamespace { return ZigTypes.USINGNAMESPACE_KEYWORD; } // new
var { return ZigTypes.VAR_KEYWORD; }
volatile { return ZigTypes.VOLATILE_KEYWORD; }
while { return ZigTypes.WHILE_KEYWORD; }

section { return ZigTypes.SECTION_KEYWORD; }
use { return ZigTypes.USE_KEYWORD; }
nakedcc { return ZigTypes.NAKEDCC_KEYWORD; }
stdcallcc { return ZigTypes.STDCALLCC_KEYWORD; }
section { return ZigTypes.SECTION_KEYWORD; }
cancel { return ZigTypes.CANCEL_KEYWORD; }
this { return ZigTypes.THIS_KEYWORD; }

false { return ZigTypes.FALSE_KEYWORD; }
true { return ZigTypes.TRUE_KEYWORD; }
null { return ZigTypes.NULL_KEYWORD; }
undefined { return ZigTypes.UNDEFINED_KEYWORD; }

{SYMBOL} { return ZigTypes.SYM; }
{INTEGER} { return ZigTypes.INT_LITERAL; }
{FLOAT} { return ZigTypes.FLOAT_LITERAL; }

{OTHERWISE} { return TokenType.BAD_CHARACTER; }
