import java.text.CharacterIterator;

public class Reservada extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {
    if (Character.isLetter(code.current())) {
      String plv = readRev(code);
      if (endRev(code)) {
        switch (plv) {
          case "int":
            return new Token("INT", plv);
          case "float":
            return new Token("FLOAT", plv);
          case "return":
            return new Token("RETURN", plv);
          default:
            return null;
        }
      }
    }
    return null;
  }

  private String readRev(CharacterIterator code) {
    String plv = "";
    while (Character.isLetter(code.current())) {
      plv += code.current();
      code.next();
    }
    return plv;
  }

  private boolean endRev(CharacterIterator code) {
    return (
      code.current() == ' ' ||
      code.current() == '\n' ||
      code.current() == ';' ||
      code.current() == CharacterIterator.DONE
    );
  }
}
