import java.text.CharacterIterator;

public class Operador extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        switch (code.current()) {
            case '+':
                code.next();
                return new Token("SOMA", "+");
            case '-':
                code.next();
                return new Token("SUB", "-");
            case '*':         
                code.next();
                return new Token("MULT", "*");
            case '/':
                code.next();
                return new Token("DIV", "/");
            case '=':
                code.next();
                return new Token("ATRIBUIÇÃO", "=");
            default:
                return null;
        }
    }
} 