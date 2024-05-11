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
        System.out.println();
        // System.exit(0);
    }

    public void main(){
        token = getNexToken();
        if(bloco()){
            if(token.getLexema().equals("$")){
                System.out.println("Sintaticamente correto");
            }else{
                erro("erro sintático");
            }
        }

    }

    //______________________BLOCO__________________________
    public boolean bloco(){
        if ((reditus() || atribui() || propositum() || dicere() || dum() || Input() || nintendum() || i_si()) && bloco()) {
            return true;
        }

        return true;
    }

    //____________________Atribui__________________________
    public boolean atribui(){
        if(veritipo() && matchT("ID") && matchT("ATRIBUICAO") && dado() && matchT("FIM")){
            return true;
        }
        erro("atribui");
        return false;
    }

    public boolean veritipo(){
        if(matchT("INT") || matchT("FLOAT") || matchT("STRING") || matchT("BOOLEAN")){
            return true;
        }
        // erro("veritipo");
        // Vazio
        return true;
    }

    public boolean dado(){
        if(matchT("FRASE") ||  expre() || matchT("ID") || matchT("NUM")){
            return true;
        }
        erro("result"); 
        return false;
    }

    //_____________ Reditus (Return) _____________
    public boolean reditus(){
        if(matchL("reditus") && var()){
            return true;
        }
        erro("reditus");
        return false;
    }

    public boolean var(){
        if((matchT("FRASE") || matchT("NUM") || matchL("inanis") || matchT("ID")) && matchL("?")){
            return true;
        }
        erro("var");
        return false;
    }

    //______________ Propositum (FOR) _______________
    public boolean propositum(){
        if(matchL("propositum") && matchL("(") && atribui() && condição() && atualiza() && matchL(")")
         && matchL("{") && bloco() && matchL("}")){
            return true;
        }
        erro("propositum");
        return false;
    }  

    public boolean atualiza(){
        if(matchT("ID") && matchL("+") && matchL("+")){
            return true;
        }
        erro("atualiza");
        return false;
    }

    //__________________Dicere_____________________

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
    

    //_______________Comentario_________________
    public boolean noncoment(){
        if (matchT("COMENTARIO")){
            return true;
        }
        return false;
    }
    
    //_________________While_________________
    public boolean dum(){
        if (matchL("dum") && matchL("(") && condição() && matchL(")") && matchL("{") && bloco() && matchL("}")){
            return true;
        }
        erro("dum");
        return false;
    }
    





    //__________________Expressao______________________
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
        if (matchT("ID") || matchT("NUM") || matchT("FLUTUANTE") || matchL("(") && expre() && matchL(")")){
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
            //token = getNexToken();
            return true;
        }
        return false;
    }
    
    public boolean condição(){
        if(ID_NUM() && compara() && ID_NUM() && condição_fim()){
            // token = getNexToken();
            return true;
        }
        return false;
    }

    // Corrige o problema de sempre ter que finalizar uma condição com ? (precisa no for, mas nao no if)
    public boolean condição_fim(){
        if(matchL("?")){
            // token = getNexToken();
            return true;
        }
        return true;
    }

    public boolean ID_NUM(){
        if(matchT("ID") || matchT("NUM")){
            // token = getNexToken();
            return true;
        }
        return false;
    }

    //_______ Input ________
    public boolean Input(){
        if(matchT("INPUT") && matchL("(") && matchT("ID") && matchL(")") && matchL("?")){

            return true;
            
        }

        erro("Erro input");
        return false;
    }

    //________ Switch Case_______
    public boolean nintendum(){
        if(matchL("nintendum") && matchL("(") && EXPRE_NUM_ID_FRASE() && matchL(")") && matchL("{") && wii() && matchL("}")){
            return true;
        } 
        erro("nintendum");
        return false;
    }

    public boolean wii(){
        if(matchL("wii") && EXPRE_NUM_ID_FRASE() && matchL(":") && bloco() && matchL("confractus") && matchL("?") && continuawii()){
            return true;
        }
        erro("wii");
        return false;
    }

    public boolean EXPRE_NUM_ID_FRASE(){
        if(matchT("ID") || matchT("FRASE") || matchT("NUM") || expre()){
            return true;
        }
        erro("x");
        return false;
    }

    public boolean ID_FRASE_NUM(){
        if(matchT("ID") || matchT("FRASE") || matchT("NUM")){
            return true;
        }
        erro("x");
        return false;
    }

    public boolean continuawii(){
        if( (matchL("vexillum") && matchL(":") && bloco() && matchL("?")) || wii()){
            return true;
        }
        erro("y");
        return false;
    }
       
    //_____________if else__________________

    public boolean e_oppositum(){
        if(matchL("oppositum") && matchL("{") && bloco() && matchL("}")){
            return true;
        }
        return false;
    }

    public boolean i_si(){
        if(matchL("si") && matchL("(") && condição() && matchL(")") && matchL("{") && bloco() && matchL("}") && addcond()){
            return true;
        }
        return false;
    }

    //y do if - após o primeiro if, possibilita if/else/vazio
    public boolean addcond(){
        if(e_oppositum()){
            return true;
        }
        return true;
    }





    //_____________Compara Lexema______________
    public boolean matchL(String lexema){

        // _____ Código para debug _____
         System.out.println("Necessario: " + lexema);
         System.out.println("Lexema: " + token.getLexema());
         System.out.println("Token: " + token);
         System.err.println();
        
        if(token.getLexema().equals(lexema)){
            token = getNexToken();
            return true;
        }
        return false;
    }

    //_____________Compara Tipo______________
    public boolean matchT(String tipo){

        // _____ Código para debug _____
         System.out.println("Necessario: " + tipo);
         System.out.println("Tipo: " + token.getTipo());
         System.out.println("Token: " + token);
         System.err.println();

        if(token.getTipo().equals(tipo)){
            token = getNexToken();
            return true;
        }
        return false;
    }

}
