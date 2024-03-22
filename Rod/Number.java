import java.text.CharacterIterator;

public class Number extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {
    if (Character.isDigit(code.current())) {
      String number = readNumber(code);
      int count = 0;
      if (endNumber(code, count)) {
        if(code.current() == '.'){
          count++;
          code.next();
          if(Character.isDigit(code.current())){
            number += ".";
            number += readNumber(code);
            if(endNumber(code, count)){
              return new Token("FLUTUANTE", number);
            }
          }
        }
        return new Token("NUM", number);
      }
    }
    return null; 
  }

  private String readNumber(CharacterIterator code) {
    String number = "";
    while (Character.isDigit(code.current())) {
      number += code.current();
      code.next();
    }
    return number;
  }

  private boolean endNumber(CharacterIterator code, int count) {
    return (
      code.current() == ' ' ||
      code.current() == '+' ||
      code.current() == '\n' ||
      code.current() == '-' ||
      code.current() == '*' ||
      code.current() == '/' ||
      code.current() == '=' ||
      code.current() == ')' ||
      code.current() == '(' ||
      code.current() == '}' ||
      code.current() == '{' ||
      code.current() == ';' ||
      (code.current() == '.' && count == 0 )||
      code.current() == CharacterIterator.DONE
    );
  }
}
