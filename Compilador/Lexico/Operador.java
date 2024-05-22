package Lexico;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.text.CharacterIterator;


public class Operador extends AFD {
    @Override
    public Token evaluate(CharacterIterator codigo){
        switch(codigo.current()){
            case'+':
                codigo.next();
                return new Token("SOMA","+");
            case'-':
                codigo.next();
                return new Token("MENOS", "-");
            case'*':
                codigo.next();
                return new Token("MULTI", "*");
            case '<':
                codigo.next();
                if (codigo.current() == '-'){
                    codigo.next();
                    return new Token("ATRIBUICAO", "<-");
                }
                if (codigo.current() == '='){
                    codigo.next();
                    if(codigo.current() == '>'){
                        codigo.next();
                        return new Token("IGUAL", "<=>");
                    }
                    return new Token("MENORIGUAL", "<=");
                }
                if (codigo.current() == '>'){
                    codigo.next();
                    return new Token("DIFERENTE", "<>");
                }
                return new Token("MENOR", "<");
            case '>':
                codigo.next();
                if (codigo.current() == '='){
                    codigo.next();
                    return new Token("MAIORIGUAL", ">=");
                }
                return new Token("MAIOR", ">");
            case'/':
                codigo.next();
                return new Token("DIV", "/");
            case'|':
                codigo.next();
                return new Token("ASPAS", " \" ");
            case')':
                codigo.next();
                return new Token("RPAREN",")");
            case'(':
                codigo.next();
                return new Token("LPAREN","(");
            case'}':
                codigo.next();
                return new Token("RCHAVE","}");
            case'{':
                codigo.next();
                return new Token("LCHAVE","{");
            case'?':
                codigo.next();
                return new Token("FIM", "?");
            default:
                return null;
        }
    }
}
