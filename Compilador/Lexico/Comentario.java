package Lexico;
import java.text.CharacterIterator;


public class Comentario extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {
    if (Character.isLetter(code.current())) {
      String plv = readplv(code);
      if (plv.equals("noncommento")) {
        String com = "" + plv;
        com += resto(code);
        
        // System.out.println(com);
        if (com.contains("oblivion")) {
          // int pos = com.indexOf("oblivion");
          // com = com.substring(0, pos+8);
          // code.setIndex(pos+8);
          return new Token("COMENTARIO", com);
        } else {
          System.err.println("Comentario nao fechado");
        }
      }
    }
    return null;
  }

  private String readplv(CharacterIterator code) {
    String plv = "";
    while (Character.isLetter(code.current())) {
      plv += code.current();
      code.next();
    }
    return plv;
  }

  private String resto(CharacterIterator code) {
    String plv = "";
    while (!plv.contains("oblivion") && code.current() != CharacterIterator.DONE) {
      plv += code.current();
      code.next();
  }
    return plv;
  }
}
