package Lexico;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.text.CharacterIterator;

/**
 *
 * @author unifmursilva
 */
public class Reservada extends AFD{
    @Override
    public Token evaluate(CharacterIterator codigo){
        if(Character.isLetter(codigo.current())){
            String plv = readReservada(codigo);
            if (endReservada(codigo)){
                switch(plv){
                    case"totum":
                        return new Token("INT",plv);
                    case"miseratio":
                        return new Token("FLOAT",plv);
                    case"reditus":
                        return new Token("RETURN",plv);
                    case"conditio":
                        return new Token("BOOLEAN",plv);
                    case"filum":
                        return new Token("STRING",plv);
                    case"auditio":
                        return new Token("INPUT",plv);
                    case"fauditio":
                        return new Token("STRINGINPUT",plv);
                    case"fi":
                        return new Token("FI",plv);
                    case"inanis":
                        return new Token("NULL",plv);
                    case"dicere":
                        return new Token("PRINT",plv);
                    case"fdicere":
                        return new Token("STRINGPRINT",plv);
                    case"dum":
                        return new Token("WHILE",plv);
                    case"propositum":
                        return new Token("FOR",plv);
                    case"si":
                        return new Token("IF",plv);
                    case"oppositum":
                        return new Token("ELSE",plv);
                    case"nintendum":
                        return new Token("SWITCH",plv);
                    case"confractus":
                        return new Token("BREAK",plv);
                    case"wii":
                        return new Token("CASE",plv);
                    case"vexillum":
                        return new Token("DEFAULT",plv);
                    case"verum":
                        return new Token("TRUE",plv);
                    case"falsum":
                        return new Token("FALSE",plv);
                    default:
                        return null;
                }
            }
        }
        return null;
    }
    
    private String readReservada(CharacterIterator codigo){
        String plv = "";
        while (Character.isLetter(codigo.current())){
            plv += codigo.current();
            codigo.next();
        }
        return plv;
    }
    
    private boolean endReservada(CharacterIterator codigo){
        return codigo.current() == ' ' ||
        codigo.current() == '\n' ||
        codigo.current() == ';' ||
        codigo.current() == '+' ||
        codigo.current() == '-' ||
        codigo.current() == '*' ||
        codigo.current() == '/' ||
        codigo.current() == '=' ||
        codigo.current() == ')' ||
        codigo.current() == '(' ||
        codigo.current() == '}' ||
        codigo.current() == '{' ||
        codigo.current() == '.' ||
        codigo.current() == '?' ||
        codigo.current() == CharacterIterator.DONE;
    }
}