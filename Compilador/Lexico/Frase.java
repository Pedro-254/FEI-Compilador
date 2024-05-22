package Lexico;
import java.text.CharacterIterator;

public class Frase extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        if(code.current() == '|'){
            String frase = "";
            code.next();
            while(!frase.contains("|") && code.current() != CharacterIterator.DONE){
                frase += code.current();
                code.next();
            }
            frase = "|"+frase;

            return new Token("FRASE", frase);
        }
        return null;
    }
    
}
