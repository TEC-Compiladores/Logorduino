/* ############## Codigo de usuario ############## */

package logic;

import java_cup.runtime.*;
import java.io.Reader;


/* ############## Declaración de opciones de JFlex ############## */
%%

%class AnalizadorLexico

%line
%column

%cup 


/* ############## Codigo que sera copiado ############## */
%{
  
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int pType) {
        return new Symbol(pType, yyline, yycolumn);
    }
    

    private Symbol symbol(int pType, Object pValue) {
        return new Symbol(pType, yyline, yycolumn, pValue);
    }
  
%}


/* Macro declaraciones */

Salto = \r|\n|\r\n
   
Espacio = {Salto} | [ \t\f]

Entero = 0 | [1-9][0-9]*

Identificador = [A-Za-z]+[Entero]*

%state VAR
%state STRING

%%

<YYINITIAL> {
  
  "avanza"				{ return symbol(sym.AV); }
  "av"					{ return symbol(sym.AV); }
  
  "retrocede"				{ return symbol(sym.RE); }
  "re"					{ return symbol(sym.RE); }
  
  "giraderecha"				{ return symbol(sym.GD); }
  "gd"					{ return symbol(sym.GD); }
  
  "giraizquierda"			{ return symbol(sym.GI); }
  "gi"					{ return symbol(sym.GI); }
  
  "repite"				{ return symbol(sym.REPITE); }
  
  "para"				{ return symbol(sym.PARA); }
  "fin"					{ return symbol(sym.FIN); }
  
  "ponpos"				{ return symbol(sym.PON_POS); }
  
  "ponrumbo"				{ return symbol(sym.PON_RUMBO); }
  
  "centro"				{ return symbol(sym.CENTRO); }
  
  "subelapiz"				{ return symbol(sym.SB_LAPIZ); }
  "sl"					{ return symbol(sym.SB_LAPIZ); }
  
  "bajalapiz"				{ return symbol(sym.BJ_LAPIZ); }
  "bl"					{ return symbol(sym.BJ_LAPIZ); }
  
  "["					{ return symbol(sym.PC_IZQ); }
  "]"					{ return symbol(sym.PC_DER); }
  
  ":"					{ string.setLength(0); yybegin(STRING); }
  
  "\""					{ string.setLength(0); yybegin(VAR); }
  
  {Espacio}				{ return symbol(sym.ESPACIO); }
  
  {Entero}				{ return symbol(sym.ENTERO, new Integer(yytext())); }
  
  {Identificador}			{ return symbol(sym.FUNC, new String(yytext())); }  
  
}


<STRING> {
  
  {Espacio}				{ yybegin(YYINITIAL); return symbol(sym.PARAM, string.toString()); }
  
  {Identificador}			{ string.append(yytext()); }
}


<VAR> {
  
  {Espacio}				{ yybegin(YYINITIAL); return symbol(sym.VAR, string.toString()); }
  
  {Identificador}			{ string.append(yytext());}
}


[^]					{ System.err.println("Se desconoce el caracter <" + yytext() + ">");
					  System.err.println("En la fila " + yyline + " columna " + yycolumn);
					  System.exit(1); }


