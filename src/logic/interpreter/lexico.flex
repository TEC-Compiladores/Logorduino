/* ############## Codigo de usuario ############## */

package logic.interpreter;

import java_cup.runtime.*;
import java.io.Reader;


/* ############## Declaración de opciones de JFlex ############## */
%%

%class LexicalParser

%line
%column

%cup 


/* ############## Codigo que sera copiado ############## */
%{
  
    StringBuffer string = new StringBuffer();
    
    
    public LexicalParser() {
  	}

    private Symbol symbol(int pType) {
        return new Symbol(pType, yyline, yycolumn);
    }
    

    private Symbol symbol(int pType, Object pValue) {
        return new Symbol(pType, yyline, yycolumn, pValue);
    }
    
    
    public void setReader(java.io.Reader pReader) {
    	this.zzReader = pReader;
  	}
  
%}


/* Macro declaraciones */

Salto = \r|\n|\r\n
   
Espacio = {Salto} | [ \t\f]

Entero = 0 | [1-9][0-9]*
Flotante = 0 | 0\.{Entero} | {Entero}\.{Entero}

Identificador = [A-Z|a-z]+[Entero]*

Variable = \"{Identificador}
Parametro = :{Identificador}



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
  
  "haz"					{ return symbol(sym.HAZ); }
  
  "subelapiz"				{ return symbol(sym.SB_LAPIZ); }
  "sl"					{ return symbol(sym.SB_LAPIZ); }
  
  "bajalapiz"				{ return symbol(sym.BJ_LAPIZ); }
  "bl"					{ return symbol(sym.BJ_LAPIZ); }
  
  "["					{ return symbol(sym.PC_IZQ); }
  "]"					{ return symbol(sym.PC_DER); }
  
  {Parametro}					{ return symbol(sym.PARAM, new String( yytext().substring(1, yytext().length()))); }
  
  {Variable}					{ return symbol(sym.VAR, new String( yytext().substring(1, yytext().length()))); }
  
  {Espacio}				{ return symbol(sym.ESPACIO); }
  
  {Flotante}				{ return symbol(sym.FLOAT, new Float(Float.parseFloat(yytext()))); }
  
  {Entero}				{ return symbol(sym.ENTERO, new Integer(yytext())); }
  
  {Identificador}			{ return symbol(sym.FUNC, new String(yytext())); }  
  
}


[^]					{ System.err.println("Se desconoce el caracter <" + yytext() + ">");
					  System.err.println("En la fila " + yyline + " columna " + yycolumn);
					  System.exit(1); }


