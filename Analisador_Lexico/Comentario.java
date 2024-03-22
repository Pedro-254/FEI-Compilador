import java.text.CharacterIterator;

public class Comentario extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {
    if (Character.isLetter(code.current())) {
      String plv = readplv(code);
      String com = plv;
      if (endplv(code)) {
        if(plv == "noncommento"){
          while(plv == "oblivion" || endplv(code)){
            plv = readplv(code);
            com += plv;
          }
          return new Token("COMENTARIO", com);
        }
      }
    }
    return null;
  }


  private String readplv(CharacterIterator code) {
    String plv = "";
    while (Character.isDigit(code.current()) || endplv(code)) {
      plv += code.current();
      code.next();
    }
    return plv;
  }

  private boolean endplv(CharacterIterator code) {
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
