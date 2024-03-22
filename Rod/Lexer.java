import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

  private List<Token> tokens;
  private List<AFD> afds;
  private CharacterIterator code;
  private int linha;

  public Lexer(String code, int linha) {
    tokens = new ArrayList<>();
    this.code = new StringCharacterIterator(code);
    this.linha = linha;
    afds = new ArrayList<>();
    afds.add(new Delimitador());
    afds.add(new Reservada());
    afds.add(new Comentario());
    afds.add(new Id());
    afds.add(new Operador());
    afds.add(new Number());

    
  }

  public void skipWhiteSpace() {
    while (code.current() == ' ' || code.current() == '\n') {
      code.next();
    }
  }

  public List<Token> getTokens() {
    boolean accepted;
    while (code.current() != CharacterIterator.DONE) {
      accepted = false;
      skipWhiteSpace();
      if (code.current() == CharacterIterator.DONE) break;
      for (AFD afd : afds) {
        int pos = code.getIndex();
        Token t = afd.evaluate(code);
        if (t != null) {
          accepted = true;
          tokens.add(t);
          break;
        } else {
          code.setIndex(pos);
        }
      }
      if (accepted) continue;
      throw new RuntimeException(
        "Error: Token not recognized: " + code.current() + " in index " + code.getIndex() + " in line " + linha
      );
    }
    tokens.add(new Token("EOF", "$"));
    return tokens;
  }
} 
