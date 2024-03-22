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
                return new Token("PLUS","+");
            case'-':
                codigo.next();
                return new Token("LESS", "-");
            case'*':
                codigo.next();
                return new Token("MULTI", "*");
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
