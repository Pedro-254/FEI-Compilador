public class Sintatico_Input {

    public Sintatico_Input() {}

    public boolean conferir(Lexer lexer){
        Token token = lexer.NextToken();
        if (token.getTipo() == "INPUT") {
            token = lexer.NextToken();
            if (token.getLexema() == "(") {
                token = lexer.NextToken();
                if (token.getTipo() == "ID") {
                    token = lexer.NextToken();
                    if (token.getLexema() == ")") {
                        token = lexer.NextToken();
                        if (token.getLexema() == "?") {
                            return true;
                        }
                    }
                }
            }
        }
        

        return false;
    }
}

