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
        if(propositum()){
            if(token.getLexema().equals("$")){
                System.out.println("Sintaticamente correto");
            }else{
                erro("erro sintático");
            }
        }
    }

    public boolean propositum(){
        if(matchL("propositum") && matchL("(") && atribui() && matchL("?") && condição()
        && matchL("?") && atualiza() && matchL(")") && matchL("{") && expre() && matchL("?") && matchL("}") && matchT("FIM")){
            return true;
        }
        erro("propositum");
        return false;
    }  

    public boolean atualiza(){
        if(matchT("ID") && matchL("+") && matchL("+") && matchT("FIM")){
            return true;
        }
        erro("atualiza");
        return false;
    }

    public boolean dicere(){
        if(matchL("dicere") && matchL("(") && printado() && matchL(")") && matchT("FIM")){
            return true;
        }
        erro("dicere");
        return false;
    }

    // x de dicere
    public boolean printado(){
        if( IDSTRING() && multiprintado()){
            // token = getNexToken();
            return true;
        }
        erro("printado");
        return false;
    }

    public boolean IDSTRING(){
        if(matchT("ID") || matchT("FRASE")){
            // token = getNexToken();
            return true;
        }
        erro("IDSTRING");
        return false;
    }

    // y de dicere
    public boolean multiprintado(){
        if((matchT("VIRGULA") && IDSTRING() && multiprintado())){
            // token = getNexToken();
            return true;
        }
        // vazio
        return true;
    }
    
    public boolean noncoment(){
        if (matchT("COMENTARIO")){
            return true;
        }
        return false;
    }
    
    public boolean dum(){
        if (matchL("dum") && matchL("(") && condição() && matchL(")") && matchL("{") && atribui() && matchL("}")){
            return true;
        }
        erro("dum");
        return false;
    }


    public boolean atribui(){
        if( veritipo() && matchT("ID") && matchT("ATRIBUICAO") && matchT("NUM") || veritipo() && matchT("ID") && matchT("ATRIBUICAO") && result() && matchT("FIM")){
            return true;
        }
        erro("atribui");
        return false;
    }
    
    public boolean veritipo(){
        if(matchT("INT") || matchT("FLOAT") || matchT("STRING") || matchT("BOOLEAN")){
            return true;
        }
        erro("veritipo");
        return false;
    }


    public boolean result(){
        if(matchT("FRASE") ||  expre() ){
            return true;
        }
        erro("result"); 
        return false;
    }

    public boolean expre(){
        if (tato() && exp2()){
            return true;
        }
        erro("expre");
        return false;
    }

    public boolean tato(){
        if ( fator() && tato2()){
            return true;
        }
        erro("tato");
        return false;
    }

    public boolean tato2(){
        if(somamenos() && fator() && tato2()){
            return true;
        }
        // vazio
        return true;
    }

    public boolean exp2(){
        if (multidiv() && tato() && exp2()){
            return true;
        }
        // vazio
        return true;
    }

    public boolean fator(){
        if (veritipo() && matchL("ID")||matchT("ID") || matchT("NUM") || matchT("FLUTUANTE") || matchL("?") || matchL("(") && expre() && matchL(")")){
            return true;
        }
        erro("fator");
        return false;
    }

    public boolean somamenos(){
        if (matchL("+") || matchL("-")){
            return true;
        }
        // erro("somamenos");
        return false;
    }

    public boolean multidiv(){
        if (matchL("*") || matchL("/")){
            return true;
        }
        // erro("multidiv");
        return false;
    }
    
    public boolean compara(){
        if(matchL("<") || matchL(">") || matchL("<=") || matchL(">=") || matchL("<>") || matchL("<=>")){
            token = getNexToken();
            return true;
        }
        return false;
    }
    
    public boolean condição(){
        if(matchT("ID") && compara() && (matchT("ID") || matchT("NUM"))){
            token = getNexToken();
            return true;
        }
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