import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    List<Token> tokens = null;
    FileReader file = new FileReader("./arquivo.txt");
    BufferedReader reader = new BufferedReader(file);
    String data = "";
    int count = 1;
    String linha = reader.readLine();
    while(linha != null){
      // System.out.println(linha);
      data = linha + '\n';
      Lexer lexer = new Lexer(data, count);
      tokens = lexer.getTokens();
      for (Token token : tokens) {
        System.out.println(token);
      }
      linha = reader.readLine();
      count++;
    }
    // String data = "pau int ola 12+54 = 14.8290\n //ola estou de pau duro\nfloat";
    // System.out.println(data);
    
    reader.close();
  }
}
