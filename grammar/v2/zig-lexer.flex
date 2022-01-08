package org.ziglang.parsing.v2;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.ziglang.parsing.v2.psi.ZigTypes;
import org.ziglang.parsing.v2.ZigTokenType;

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

NUM_SUFFIX=-?\d+
P_SUFFIX=[pP]{NUM_SUFFIX}
E_SUFFIX=[eE]{NUM_SUFFIX}
HEXDIGIT=[a-fA-F0-9]
HEX_NUM=0[xX]{HEXDIGIT}+({P_SUFFIX}|{E_SUFFIX})?
OCT_NUM=0[oO][0-7]+
BIN_NUM=0[bB][01]+
DEC_NUM=\d+{E_SUFFIX}?
INTEGER={HEX_NUM}|{OCT_NUM}|{BIN_NUM}|{DEC_NUM}
FLOAT=((\d+\.\d*)|(\d*\.\d+))({E_SUFFIX}|{P_SUFFIX})?

//SYMBOL_CHAR=[a-zA-Z_] // TODO
//SYMBOL={SYMBOL_CHAR}({SYMBOL_CHAR}|\d)*

INCOMPLETE_STRING=c?\"([^\"\\\n]|\\[^])*
STRING_LITERAL={INCOMPLETE_STRING}\"

INCOMPLETE_CHAR='([^'\\\n]|\\[^])*
CHAR_LITERAL={INCOMPLETE_CHAR}'

BUILTIN_IDENTIFIER=@[A-Za-z_][A-Za-z0-9_]*
IDENTIFIER=[A-Za-z_][A-Za-z0-9_]*

CONTAINER_DOC_COMMENT=\/\/\!([^\n]*[\n]*)+
DOC_COMMENT=\/\/\/([^\n]*[ \n]*)+
LINE_COMMENT=\/\/[^\n]*

//
//eof=!.
//bin=[01]
//oct=[0-7]
//hex=[0-9a-fA-F]
//dec=[0-9]
//
//ox80_oxBF=[\200-\277]
//oxF4='\364'
//ox80_ox8F=[\200-\217]
//oxF1_oxF3=[\361-\363]
//oxF0='\360'
//ox90_0xBF=[\220-\277]
//oxEE_oxEF=[\356-\357]
//oxED='\355'
//ox80_ox9F=[\200-\237]
//oxE1_oxEC=[\341-\354]
//oxE0='\340'
//oxA0_oxBF=[\240-\277]
//oxC2_oxDF=[\302-\337]
//
//ascii_char_not_nl_slash_squote=[\000-\011\013-\046\050-\133\135-\177]

%state AFTER_AT

%%

{CONTAINER_DOC_COMMENT} { return ZigTypes.CONTAINER_DOC_COMMENT; }
{DOC_COMMENT} { return ZigTypes.DOC_COMMENT; }
{LINE_COMMENT} { return ZigTypes.LINE_COMMENT; }
{WHITESPACE} { return TokenType.WHITE_SPACE; }

//<AFTER_AT> {SYMBOL} { yybegin(YYINITIAL); return ZigTypes.BUILTINFUNCTION; }
<AFTER_AT> [^] { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

c { return ZigTypes.LETTERC; }

\<\<= { return ZigTypes.LARROW2EQUAL; } // <<=
>>= { return ZigTypes.RARROW2EQUAL; }
=> { return ZigTypes.EQUALRARROW; }
-> { return ZigTypes.MINUSRARROW; }

\+%= { return ZigTypes.PLUSPERCENTEQUAL; }
\-%= { return ZigTypes.MINUSPERCENTEQUAL; }
\*%= { return ZigTypes.ASTERISKPERCENTEQUAL; }
//\/%= { return ZigTypes.DIV_MOD_ASSIGN_SYM; }

\+% { return ZigTypes.PLUSPERCENT; }
\-% { return ZigTypes.MINUSPERCENT; }
\*% { return ZigTypes.ASTERISKPERCENT; }

\<< { return ZigTypes.LARROW2; }
\>> { return ZigTypes.RARROW2; }

\+= { return ZigTypes.PLUSEQUAL; }
\-= { return ZigTypes.MINUSEQUAL; }
\*= { return ZigTypes.ASTERISKEQUAL; }
\/= { return ZigTypes.SLASHEQUAL; }
\^= { return ZigTypes.CARETEQUAL; }
\|= { return ZigTypes.PIPEEQUAL; }
\!= { return ZigTypes.EXCLAMATIONMARKEQUAL; }
\>= { return ZigTypes.RARROWEQUAL; }
\<= { return ZigTypes.LARROWEQUAL; }
&= { return ZigTypes.AMPERSANDEQUAL; }
%= { return ZigTypes.PERCENTEQUAL; }
== { return ZigTypes.EQUALEQUAL; }

\+\+ { return ZigTypes.PLUS2; }
// \-\- { return ZigTypes.DEC_SYM; }
\*\* { return ZigTypes.ASTERISK2; }
\|\| { return ZigTypes.PIPE2; }

\> { return ZigTypes.RARROW2; }
\< { return ZigTypes.LARROW; }
\+ { return ZigTypes.PLUS; }
\- { return ZigTypes.MINUS; }
\* { return ZigTypes.ASTERISK; }
\/ { return ZigTypes.SLASH; }
\^ { return ZigTypes.CARET; }
\| { return ZigTypes.PIPE; }
\! { return ZigTypes.EXCLAMATIONMARK; }
\? { return ZigTypes.QUESTIONMARK; }
\~ { return ZigTypes.TILDE; }
& { return ZigTypes.AMPERSAND; }
% { return ZigTypes.PERCENT; }
= { return ZigTypes.EQUAL; }
// @ { yybegin(AFTER_AT); return ZigTypes.AT_SYM; }

, { return ZigTypes.COMMA; }
{SEMICOLON} { return ZigTypes.SEMICOLON; }
: { return ZigTypes.COLON; }
\( { return ZigTypes.LPAREN; }
\) { return ZigTypes.RPAREN; }
\{ { return ZigTypes.LBRACE; }
\} { return ZigTypes.RBRACE; }
\[ { return ZigTypes.LBRACKET; }
\] { return ZigTypes.RBRACKET; }

\.\.\. { return ZigTypes.DOT3; }
\.\. { return ZigTypes.DOT2; }
\. { return ZigTypes.DOT; }
\.\? { return ZigTypes.DOTQUESTIONMARK; }
\.\* { return ZigTypes.DOTASTERISK; }

{INCOMPLETE_STRING} { return TokenType.BAD_CHARACTER; }
{CHAR_LITERAL} { return ZigTypes.CHAR_LITERAL; }
{INCOMPLETE_CHAR} { return TokenType.BAD_CHARACTER; }

align { return ZigTypes.KEYWORD_ALIGN; }
allowzero { return ZigTypes.KEYWORD_ALLOWZERO; } // new
and { return ZigTypes.KEYWORD_AND; }
anyframe { return ZigTypes.KEYWORD_ANYFRAME; } // new
anytype { return ZigTypes.KEYWORD_ANYTYPE; } // new
asm { return ZigTypes.KEYWORD_ASM; }
async { return ZigTypes.KEYWORD_ASYNC; }
await { return ZigTypes.KEYWORD_AWAIT; }
break { return ZigTypes.KEYWORD_BREAK; }
callconv { return ZigTypes.KEYWORD_CALLCONV; } // new
catch { return ZigTypes.KEYWORD_CATCH; }
comptime { return ZigTypes.KEYWORD_COMPTIME; }
const { return ZigTypes.KEYWORD_CONST; }
continue { return ZigTypes.KEYWORD_CONTINUE; }
defer { return ZigTypes.KEYWORD_DEFER; }
else { return ZigTypes.KEYWORD_ELSE; }
enum { return ZigTypes.KEYWORD_ENUM; }
errdefer { return ZigTypes.KEYWORD_ERRDEFER; }
error { return ZigTypes.KEYWORD_ERROR; }
export { return ZigTypes.KEYWORD_EXPORT; }
extern { return ZigTypes.KEYWORD_EXTERN; }
fn { return ZigTypes.KEYWORD_FN; }
for { return ZigTypes.KEYWORD_FOR; }
if { return ZigTypes.KEYWORD_IF; }
inline { return ZigTypes.KEYWORD_INLINE; }
linksection { return ZigTypes.KEYWORD_LINKSECTION; } // new
noalias { return ZigTypes.KEYWORD_NOALIAS; } // new
nosuspend { return ZigTypes.KEYWORD_NOSUSPEND; } // new
noinline { return ZigTypes.KEYWORD_NOINLINE; } // new
opaque { return ZigTypes.KEYWORD_OPAQUE; } // new
or { return ZigTypes.KEYWORD_OR; }
orelse { return ZigTypes.KEYWORD_ORELSE; }
packed { return ZigTypes.KEYWORD_PACKED; }
pub { return ZigTypes.KEYWORD_PUB; }
resume { return ZigTypes.KEYWORD_RESUME; }
return { return ZigTypes.KEYWORD_RETURN; }
struct { return ZigTypes.KEYWORD_STRUCT; }
suspend { return ZigTypes.KEYWORD_SUSPEND; }
switch { return ZigTypes.KEYWORD_SWITCH; }
test { return ZigTypes.KEYWORD_TEST; }
threadlocal { return ZigTypes.KEYWORD_THREADLOCAL; } // new
try { return ZigTypes.KEYWORD_TRY; }
union { return ZigTypes.KEYWORD_UNION; }
unreachable { return ZigTypes.KEYWORD_UNREACHABLE; }
usingnamespace { return ZigTypes.KEYWORD_USINGNAMESPACE; } // new
var { return ZigTypes.KEYWORD_VAR; }
volatile { return ZigTypes.KEYWORD_VOLATILE; }
while { return ZigTypes.KEYWORD_WHILE; }

//section { return ZigTypes.SECTION_KEYWORD; }
//use { return ZigTypes.USE_KEYWORD; }
//nakedcc { return ZigTypes.NAKEDCC_KEYWORD; }
//stdcallcc { return ZigTypes.STDCALLCC_KEYWORD; }
//section { return ZigTypes.SECTION_KEYWORD; }
//cancel { return ZigTypes.CANCEL_KEYWORD; }
//this { return ZigTypes.THIS_KEYWORD; }

//false { return ZigTypes.FALSE_KEYWORD; }
//true { return ZigTypes.TRUE_KEYWORD; }
//null { return ZigTypes.NULL_KEYWORD; }
//undefined { return ZigTypes.UNDEFINED_KEYWORD; }

{STRING_LITERAL} { return ZigTypes.STRING_LITERAL; }
{IDENTIFIER} { return ZigTypes.IDENTIFIER; }
{BUILTIN_IDENTIFIER} { return ZigTypes.BUILTINIDENTIFIER; }
{INTEGER} { return ZigTypes.INTEGER; }
{FLOAT} { return ZigTypes.FLOAT; }

{OTHERWISE} { return TokenType.BAD_CHARACTER; }
