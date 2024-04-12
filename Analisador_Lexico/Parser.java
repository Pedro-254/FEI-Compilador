import java.util.List;

public class Parser {
    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getNexToken(){
        if (tokens.size() > 0){
            return tokens.remove(0);
        }
        return null;
    }

    private void erro(String regra){
        System.out.println("Regra: " + regra);
        System.out.println("Token inválido: " + token.getLexema());
        System.exit(0);
    }

    public void main(){
        token = getNexToken();
        if(metodo()){
            if(token.getLexema().equals("$")){
                System.out.println("Sintaticamente correto");
            }else{
                erro("metodo");
            }
        }
    }

    public boolean metodo(){
        if(matchL("") && condição() && matchL("") && expressão()){
            return true;
        }
        erro("metodo");
        return false;
    }

    //compara lexema
    public boolean matchL(String lexema){
        if(token.getLexema().equals(lexema)){
            token = getNexToken();
            return true;
        }
        return false;
    }


    //compara tipo
    public boolean matchT(String tipo){
        if(token.getTipo().equals(tipo)){
            token = getNexToken();
            return true;
        }
        return false;
    }
}
