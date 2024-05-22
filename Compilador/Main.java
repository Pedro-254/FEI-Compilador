import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import Lexico.Lexer;
import Lexico.Token;
import Sintatico.ParserTraducaoC;
import Sintatico.ParserTraducaoJAVA;

public class Main {

  public static void main(String[] args) throws IOException {
    List<Token> tokensJava = null;
    List<Token> tokensC = null;
    //_______ Leitura de arquivo_______
    String data = new String(Files.readAllBytes(Paths.get("./compilador/arquivos/TesteFloat.txt")), StandardCharsets.UTF_8);

    //_______ Geração de Tokens (Lexico)_______
    Lexer lexerJava = new Lexer(data);
    tokensJava = lexerJava.getTokens();

    Lexer lexerC = new Lexer(data);
    tokensC = lexerC.getTokens();

    for (Token token : tokensJava) {
      System.out.println(token);
    }

    System.out.println("_______Inicio Sintático________");

    //_______ Análise de Sintática (Lexico)_______
    ParserTraducaoC parserC = new ParserTraducaoC(tokensC);
    parserC.main();
    
    ParserTraducaoJAVA parserJava = new ParserTraducaoJAVA(tokensJava);
    parserJava.main();
    
    

  }
}
