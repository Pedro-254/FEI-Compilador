package Lexico;
import java.text.CharacterIterator;

public abstract class AFD {

  public abstract Token evaluate(CharacterIterator code);
}
