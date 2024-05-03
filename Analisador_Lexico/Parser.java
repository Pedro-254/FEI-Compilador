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

    public boolean dicere(){
        if(matchL("dicere") && matchL("(") && printado() && matchL(")") && matchL("?")){
            return true;
        }
        erro("metodo");
        return false;
    }
    
    // x de dicere
    public boolean printado(){
        if( IDSTRING() && multiprintado()){
            return true;
        }
        erro("printado");
        return false;
    }

    public boolean IDSTRING(){
        if(matchT("ID") || matchT("STRING")){
            return true;
        }
        erro("IDSTRING");
        return false;
    }

    // y de dicere
    public boolean multiprintado(){
        if((matchT("VIRGULA") && IDSTRING() && multiprintado())){
            return true;
        }
        erro("multiprintado");
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

    public boolean opera(){
        if(matchL("+") || matchL("-") || matchL("*") || matchL("/") ){
            return true;
        }
        return false;
    }

    public boolean compara(){
        if(matchL("<") || matchL(">") || matchL("<=") || matchL(">=") || matchL("<>") || matchL("<=>")){
            return true;
        }
        return false;
    }

    public boolean condição(){
        if(matchT("ID") && compara() && (matchT("ID") || matchT("NUM"))){
            return true;
        }
        return false;
    }


}
