import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    List<Token> tokens = null;
    //FileReader file = new FileReader("./teste1.txt");
    //BufferedReader reader = new BufferedReader(file);
    //String data = "";
    //String linha = reader.();
    String data = new String(Files.readAllBytes(Paths.get("teste.txt")), StandardCharsets.UTF_8);
    //while(linha != null){
      // System.out.println(linha);
      //data += linha ;
      // if(data.contains("noncommento")){
      //   while (!data.contains("oblivion") && linha != null) {
      //     linha = reader.readLine();
      //     data += linha + "\n";
      //     count++;
      //   }
      // }
    //  linha = reader.readLine();
   // }

    // System.out.println(data);
    Lexer lexer = new Lexer(data);
    tokens = lexer.getTokens();
    // for (Token token : tokens) {
    //   System.out.println(token);
    // }
    
    // System.out.println(data);
    // String data = "noncomento System.out.println( + x) oblivion";

    // Lexer lexer = new Lexer(data, count);
    //   tokens = lexer.getTokens();
    //   for (Token token : tokens) {
    //     System.out.println(token);
    //   }
    
    //reader.close();
    Sintatico_Input teste = new Sintatico_Input();


    if (teste.conferir(lexer)) {
      System.out.println("deu bom");
    };
  }
}