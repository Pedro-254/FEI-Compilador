import java.text.CharacterIterator;

public class Comentario extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {
    if (code.current()=='/') {
        code.next();
        if (code.current()=='/') {
            code.next();
            String plv = "//";
            while (code.current() != '\n' && code.current() != CharacterIterator.DONE) {
                plv += code.current();
                code.next();
            }
            return new Token("COMENTARIO", plv);
        }
    }
    return null;
  }
}
