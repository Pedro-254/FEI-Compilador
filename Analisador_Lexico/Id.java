import java.text.CharacterIterator;

public class Id extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {
    if (Character.isLetter(code.current())) {
      String plv = readId(code);
      if (endId(code)) {
        return new Token("ID", plv);
      }
    }
    return null;
  }

    private String readId(CharacterIterator code) {
        String plv = "";
        while (Character.isLetterOrDigit(code.current())) {
            plv += code.current();
            code.next();
        }
        return plv;
    }
  private boolean endId(CharacterIterator code) {
    return (
      code.current() == ' ' ||
      code.current() == '\n' ||
      code.current() == ';' ||
      code.current() == '+' ||
      code.current() == '-' ||
      code.current() == '*' ||
      code.current() == '/' ||
      code.current() == '=' ||
      code.current() == ')' ||
      code.current() == '(' ||
      code.current() == '}' ||
      code.current() == '{' ||
      code.current() == '.' ||
      code.current() == ',' ||
      code.current() == '?' ||
      code.current() == '|' ||
      code.current() == '"' ||
      code.current() == CharacterIterator.DONE
    );
  }
}
