import java.text.CharacterIterator;

public class Delimitador extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        switch (code.current()) {
            case ')':
                code.next();
                return new Token("RPAREN", ")");
            case '(':
                code.next();
                return new Token("LPAREN", "(");
            case '}':
                code.next();
                return new Token("RCHAVE", "}");
            case '{':         
                code.next();
                return new Token("LCHAVE", "{");
            case ',':         
                code.next();
                return new Token("VIRGULA", ",");
            case '.':         
                code.next();
                return new Token("PONTO", ".");
            case '?':
                code.next();
                return new Token("FIM", "?");
            default:
                return null;
        }
    }
} 