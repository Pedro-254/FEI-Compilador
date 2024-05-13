import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    List<Token> tokens = null;

    //_______ Leitura de arquivo_______
    String data = new String(Files.readAllBytes(Paths.get("./compilador/arquivos/SwitchCase.txt")), StandardCharsets.UTF_8);

    //_______ Geração de Tokens (Lexico)_______
    Lexer lexer = new Lexer(data);
    tokens = lexer.getTokens();
    for (Token token : tokens) {
      System.out.println(token);
    }

    System.out.println("_______Inicio Sintático________");

    //_______ Análise de Sintática (Lexico)_______
    Parser parser = new Parser(tokens);
    parser.main();
  

  }
}
