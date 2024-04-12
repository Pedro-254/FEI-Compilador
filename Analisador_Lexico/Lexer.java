import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

  private List<Token> tokens;
  private List<AFD> afds;
  private CharacterIterator code;
  private int linha;
  private int coluna;

  public Lexer(String code) {
    tokens = new ArrayList<>();
    this.code = new StringCharacterIterator(code);
    this.linha = 1;
    this.coluna = 0;
    afds = new ArrayList<>();
    afds.add(new Delimitador());
    afds.add(new Comentario());
    afds.add(new Reservada());
    afds.add(new Id());
    afds.add(new Frase());
    afds.add(new Operador());
    afds.add(new Number());
  }

  public void skipWhiteSpace() {
    while (Character.isWhitespace(code.current())){
      if (code.current() == '\n' || code.current() == '\r'){
        if (code.current() == '\r' && code.next() != '\n'){
          code.previous();
        }
        this.linha+=1;
        this.coluna = code.getIndex();
      }
      code.next();

    }
  }

  // public void skipLine() {
  //   while (code.current() == '\n' || code.current() == '\r'){
  //     this.linha+=1;
  //     this.coluna = code.getIndex();
  //     code.next();
  //   }
  // }

  public List<Token> getTokens() {
    boolean accepted;
    while (code.current() != CharacterIterator.DONE) {
      // System.out.println(code.current());
      this.coluna++;
      accepted = false;
      skipWhiteSpace();
      // skipLine();
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
        "Error: Token not recognized: " + code.current() + " in index: " + (code.getIndex() - this.coluna) + " in line: " + this.linha
      );
    }
    tokens.add(new Token("EOF", "$"));
    return tokens;
  }
} 
