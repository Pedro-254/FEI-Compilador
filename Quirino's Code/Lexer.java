/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author unifgscopel
 */
public class Lexer {
    private List<AFD> afds;
    private List<Token> tokens;
    private CharacterIterator codigo;

    public Lexer(String codigo){
        this.codigo = new StringCharacterIterator(codigo);
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        afds.add(new Operador());
        //afds.add(new Rparen());
        afds.add(new Comentario());
    }
    
    public void skipWhiteSpace(){
        while (codigo.current() == ' '|| codigo.current() == '\n'){
            codigo.next();
        }
    }
    
    public List<Token> getTokens(){
        boolean accepted;
        while(codigo.current() != CharacterIterator.DONE){
            accepted = false;
            skipWhiteSpace();
        
        if(codigo.current() == CharacterIterator.DONE) break;
            
        for(AFD afd:afds){
            int pos = codigo.getIndex();
            Token t = afd.evaluate(codigo);
            if(t != null){
                accepted = true;
                tokens.add(t);
                break;
            }else{
                codigo.setIndex(pos);
            }
        }
        if(accepted) continue;
        throw new RuntimeException("Error: Token not recognized: "+codigo.current());
        }
        tokens.add(new Token("EOF","$"));
        return tokens;
    }
}
