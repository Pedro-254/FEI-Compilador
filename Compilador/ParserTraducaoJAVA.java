import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ParserTraducaoJAVA {
    List<Token> tokens;
    Token token;
    String conteudo = "";
    public ParserTraducaoJAVA(List<Token> tokens) {
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
        System.exit(0);
    }

    public void main(){
        token = getNexToken();

        //________________Importando Métodos_______________
        traduz("import java.util.Scanner;\n");

        //________________Iniciando arquivo_______________
        traduz("public class TraducaoJava {\npublic static void main(String[] args) {\n");
        if(bloco()){
            
            if(token.getLexema().equals("$")){
                System.out.println("Sintaticamente correto");
                //________________Fechando arquivo_______________
                traduz("}\n}");

                //________________Criando arquivo_______________
                criarArquivo("TraducaoJava.java", conteudo);
            }else{
                erro("erro sintático");
            }
        }

    }

    //______________________BLOCO__________________________
    public boolean bloco(){
        if (token.getTipo().equals("ID")) {
            if (atribui() && bloco()) {
                return true;
            }
            
        }
        else if ((token.getTipo().equals("INT") || token.getTipo().equals("FLOAT") || token.getTipo().equals("STRING") || token.getTipo().equals("BOOLEAN")  )) {
            if (declara() && bloco()) {
                return true;
            }
        }
        else if (token.getLexema().equals("reditus")){
            if (reditus() && bloco()) {
                return true;
            }
        }
        else if (token.getLexema().equals("propositum")) {
            if (propositum() && bloco()) {
                return true;
            }
        }
        else if (token.getLexema().equals("dicere")) {
            if (dicere() && bloco()) {
                return true;
            }
            
        }else if (token.getLexema().equals("dum")) {
            if (dum() && bloco()) {
                return true;
            }

        }
        else if(token.getTipo().equals("INPUT")){
            if (Input() && bloco()) {
                return true;
            }

        }
        else if (token.getLexema().equals("nintendum")) {
            if (nintendum() && bloco()) {
                return true;
            }

        }
        else if (token.getLexema().equals("si")) {
            if (i_si() && bloco()) {
                return true;
            }

        }else if (token.getTipo().equals("COMENTARIO")) {
            if (noncoment() && bloco()) {
                return true;
            }
            
        }

        return true;
    }



    //____________________Declara_________________________ (TRADUZIDO)
    public boolean declara(){
        if(tipo() && matchT("ID",token.getLexema()) && matchT("FIM",";")){
            return true;
        }
        erro("declara");
        return false;
    }

    public boolean tipo(){
        if(matchT("INT","int ") || matchT("FLOAT","float ") || matchT("STRING","String ") || matchT("BOOLEAN","boolean ")){
            return true;
        }
        // erro("veritipo");
        // Vazio
        return false;
    }

    //____________________Atribui__________________________ (TRADUZIDO)
    public boolean atribui(){
        if(matchT("ID", token.getLexema()) && matchT("ATRIBUICAO", "=") && dado() && matchT("FIM", ";")){
            return true;
        }
        erro("atribui");
        return false;
    }


    public boolean dado(){
        if(matchT("FRASE", token.getLexema()) || true_false() || expre()){
            return true;
        }
        erro("result"); 
        return false;
    }

    public boolean true_false(){
        if (matchT("TRUE", token.getLexema()) || matchT("FALSE", token.getLexema())) {
            return true;
        }
        return false;
    }

    //_____________ Reditus (Return) _____________ (TRADUZIDO)
    public boolean reditus(){
        if(matchL("reditus","return ") && var()){
            return true;
        }
        erro("reditus");
        return false;
    }

    public boolean var(){
        if((matchT("FRASE",token.getLexema()) || matchT("NUM",token.getLexema()) || matchL("inanis","null") || matchT("ID",token.getLexema())) && matchL("?",";")){
            return true;
        }
        erro("var");
        return false;
    }

    //______________ Propositum (FOR) _______________ (TRADUZIDO)
    private boolean InnerLoop = false;

    public boolean propositum(){
        if(matchL("propositum","for") && startForLoop() && atribui() && condição() && matchL("?",";") && atualiza() && finishForLoop()
         && matchL("{","{") && bloco() && matchL("}","}")){
            return true;
        }
        erro("propositum");
        return false;
    } 

    public boolean startForLoop(){
        if(matchL("(", "(")){
            InnerLoop = true;
            return true;
        }
        erro("startForLoop");
        return false;   
    }

    public boolean finishForLoop(){
        if(matchL(")", ")")){
            InnerLoop = false;
            return true;
        }
        erro("finishForLoop");
        return false;   
    }

    public boolean atualiza(){
        if(matchT("ID",token.getLexema()) && matchL("+","+") && matchL("+","+")){
            return true;
        }
        erro("atualiza");
        return false;
    }

    //__________________Dicere_____________________ (TRADUZIDO)

    public boolean dicere(){
        if(matchL("dicere", "System.out.println") && matchL("(","(") && printado() && matchL(")",")") && matchT("FIM", ";")){
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
        if(matchT("ID", token.getLexema()) || matchT("FRASE", token.getLexema())){
            // token = getNexToken();
            return true;
        }
        erro("IDSTRING");
        return false;
    }

    // y de dicere
    public boolean multiprintado(){
        if((matchT("VIRGULA", "+") && IDSTRING() && multiprintado())){
            // token = getNexToken();
            return true;
        }
        // vazio
        return true;
    }
    

    //_______________Comentario_________________ (TRADUZIDO)
    public boolean noncoment(){
        if (matchT("COMENTARIO", token.getLexema())){
            return true;
        }
        return false;
    }
    
    //_________________While_________________ (TRADUZIDO)
    public boolean dum(){
        if (matchL("dum", "while") && matchL("(","(") && condição() && matchL(")", ")") && matchL("{", "{") && bloco() && matchL("}", "}")){
            return true;
        }
        erro("dum");
        return false;
    }


    //__________________Expressao______________________ (TRADUZIDO)
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
        if (matchT("ID", token.getLexema()) || matchT("NUM", token.getLexema()) || matchT("FLUTUANTE", token.getLexema() + "f") || matchL("(", "(") && expre() && matchL(")", ")")){
            return true;
        }
        erro("fator");
        return false;
    }

    public boolean somamenos(){
        if (matchL("+", "+") || matchL("-", "-")){
            return true;
        }
        // erro("somamenos");
        return false;
    }

    public boolean multidiv(){
        if (matchL("*", "*") || matchL("/", "/")){
            return true;
        }
        // erro("multidiv");
        return false;
    }
    
    public boolean compara(){
        if(matchL("<", "<") || matchL(">", ">") || matchL("<=","<=") || matchL(">=", ">=") || matchL("<>", "!=") || matchL("<=>", "==")){
            //token = getNexToken();
            return true;
        }
        return false;
    }

    //______________________Condição________________________ (TRADUZIDO)
    public boolean condição(){
        if(true_false() || (expre() && compara() && expre())){
            // token = getNexToken();
            return true;
        }
        return false;
    }

    public boolean ID_NUM(){
        if(matchT("ID", token.getLexema()) || matchT("NUM", token.getLexema())){
            // token = getNexToken();
            return true;
        }
        return false;
    }

    //_______ Input ________ (TRADUZIDO)
    public boolean Input(){
        if(traduz("Scanner scanner = new Scanner(System.in);\n") && matchT("INPUT","") && matchL("(","") && matchT("ID",token.getLexema() + " ") && traduz("= scanner.nextLine()") && matchL(")","") && matchL("?", ";")){
            return true;
            
        }

        erro("Erro input");
        return false;
    }

    //________ Switch Case_______
    public boolean nintendum(){
        if(matchL("nintendum","switch") && matchL("(","(") && ID_FRASE_NUM() && matchL(")",")") && matchL("{","{") && comentario_wii() && wii() && matchL("}","}")){
            return true;
        } 
        erro("nintendum");
        return false;
    }

    public boolean comentario_wii(){
        if (matchT("COMENTARIO",token.getLexema())) {
            return true;
        }
        return true;
    }
    public boolean wii(){
        if(matchL("wii","case ") && ID_FRASE_NUM() && matchL(":",":\n") && bloco() && matchL("confractus","break") && matchL("?",";") && continuawii()){
            return true;
        }
        erro("wii");
        return false;
    }

    public boolean continuawii(){
        if( comentario_wii() && (matchL("vexillum","default") && matchL(":",":\n") && bloco() && matchL("confractus","break") && matchL("?",";")) || wii()){
            return true;
        }
        erro("y");
        return false;
    }

    public boolean ID_FRASE_NUM(){
        if(matchT("ID",token.getLexema()) || matchT("FRASE",token.getLexema()) || matchT("NUM",token.getLexema())){
            return true;
        }
        erro("x");
        return false;
    }

       
    //_____________if else__________________ (TRADUZIDO)

    public boolean e_oppositum(){
        if(matchL("oppositum","else") && matchL("{","{") && bloco() && matchL("}","}")){
            return true;
        }
        return false;
    }

    public boolean i_si(){
        if(matchL("si","if") && matchL("(","(") && condição() && matchL(")",")") && matchL("{","{") && bloco() && matchL("}","}") && addcond()){
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
    public boolean matchL(String lexema, String code){

        // _____ Código para debug _____
        //  System.out.println("Necessario: " + lexema);
        //  System.out.println("Lexema: " + token.getLexema());
        //  System.out.println("Token: " + token);
        //  System.err.println();
        
        if(token.getLexema().equals(lexema)){
            traduz(code);
            token = getNexToken();
            return true;
        }
        return false;
    }

    //_____________Compara Tipo______________
    public boolean matchT(String tipo, String code){

        // _____ Código para debug _____
        //  System.out.println("Necessario: " + tipo);
        //  System.out.println("Tipo: " + token.getTipo());
        //  System.out.println("Token: " + token);
        //  System.err.println();

        if(token.getTipo().equals(tipo)){
            traduz(code);
            token = getNexToken();
            return true;
        }
        return false;
    }
    
    public boolean traduz(String s){


        //__________Pulando Linha_____________
        if (s.equals(";") || s.equals("{")) {
            if (!InnerLoop) {
                s += "\n";
            }
        }
        
        if (s.equals("}")) {
            s = "\n" + s.trim() + "\n";
        }
        
        
        //__________Tradução Comentario__________
        if(s.contains("|")){
            s = s.replace("|", "\"");
        }
        if(s.contains("noncommento")){
            s = s.replace("noncommento", "/*");
            s = s.replace("oblivion", "*/\n");
        }

        
        if (InnerLoop) {
            conteudo += s;
        }
        else
        conteudo += s;
        return true;
    
    }

    // Método para criar e escrever no arquivo
    public static void criarArquivo(String nomeArquivo, String conteudo) {
        // Usando BufferedWriter para escrever no arquivo
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            escritor.write(conteudo);
            System.out.println("Arquivo criado e conteúdo escrito com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
