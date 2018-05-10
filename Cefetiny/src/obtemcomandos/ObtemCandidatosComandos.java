package obtemcomandos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import memoria.MemoriaComando;
import obtemcomandos.ExcessaoSintatica;

public class ObtemCandidatosComandos {

    private ArrayList cmds = new ArrayList();
    private static int posicaoAtualCursor = 0;
    private String strArquivo;
    private int indiceEnd;
    private MemoriaComando comandos;

    public ObtemCandidatosComandos(String strArquivo) throws ExcessaoSintatica {
        this.setStrArquivo(strArquivo);
        comandos = new MemoriaComando();

        lerArquivoBuscandoCaracteres();

        
    }

    public void setStrArquivo(String strArquivo) {
        this.strArquivo = strArquivo;
    }

    public boolean arquivoTemFim() {
        String palavra = "";
       for (int indice = strArquivo.length() - 1; indice > -1; indice--) {

            if (!this.eEspaco(strArquivo.charAt(indice))) {

                palavra += strArquivo.charAt(indice);

                if (indice > 2) {
                    palavra += strArquivo.charAt(indice - 1);
                    palavra += strArquivo.charAt(indice - 2);

                    if (palavra.equals("dne")) {
                        this.indiceEnd = indice - 2;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //verifica se ha um proximo caracter que forma um comando, ie, se ha algo antes de end
    public boolean haProxChar() {
        if (posicaoAtualCursor + 1 < this.indiceEnd) {
            return true;
        }
        return false;
    }

    public char getNextChar() {
        char c = strArquivo.charAt(posicaoAtualCursor);
        posicaoAtualCursor++;
        return c;
    }

    public int getNumNextChar() {
        int aux = (int) this.getNextChar();
        return aux;
    }

    public char previaProxChar() {
        if (this.haProxChar()) {
            char c = strArquivo.charAt(posicaoAtualCursor);
            return c;
        }
        return ' ';
    }

    public boolean haDoisProxChar() {
        return posicaoAtualCursor + 1 < this.indiceEnd;
    }

    public char getCharAtual() {
        return strArquivo.charAt(posicaoAtualCursor);
    }

    public char previaDoisProxChar() {
        if (posicaoAtualCursor + 1 < strArquivo.length()) {
            char c = strArquivo.charAt(posicaoAtualCursor + 1);
            return c;
        } else {
            return '~';
        }
    }

    public int getPosicaoAtualCursor() {
        return posicaoAtualCursor;
    }

    public boolean eEspaco(char teste) {
        int k = (int) teste;
        return k == 9 || k == 32 || k == 10 || k == 13;
        //return !(k != 9 && k != 32 && k != 10 && k!=13);
    }

    public int indiceFechaAspas(int indiceChr) {
        for (int p = indiceChr + 1; p < strArquivo.length() - 2; p++) {
            if (strArquivo.charAt(p) == '"') {
                return p;
            }
        }
        return -1;
    }

    public boolean verificaString(String str) {
        boolean status = true;
        if (str.length() == 2) {
            return true;
        }

        for (int a = 0; a < str.length(); a++) {
            status &= eLetra(str.charAt(a)) || eDigito(str.charAt(a)) || (str.charAt(a) == '"');
        }
        return status;
    }

    public boolean eLetra(char chr) {
        int vlrChr = (int) chr;
        return (vlrChr > 96 && vlrChr < 123);
    }

    public boolean eDigito(char chr) {
        int vlrChr = (int) chr;
        return (vlrChr > 48 && vlrChr < 58);
    }

    public void verificaPrint() throws ExcessaoSintatica {
        //print(sqrt(sqrt(16))*sqrt(4)) print()
        String expressao = "";
        char chrAtual = this.getCharAtual();

        while (this.eEspaco(this.getCharAtual()) && this.haProxChar()) {
            chrAtual = this.getNextChar();
        }

        if (chrAtual == '(') {

            do {
                if (this.haProxChar()) {
                    chrAtual = this.getNextChar();
                } else {
                    throw new ExcessaoSintatica("Necessario haver parametros entre os parenteses");
                }
                chrAtual = this.getNextChar();
            } while (this.eEspaco(chrAtual));

            int inicioExpressao = posicaoAtualCursor + 1;
            int fimExpressao;

            int contAbreP = 1;
            int contFechaP = 0;

            while (contFechaP != (contAbreP + 1)) {
                if (chrAtual != '(' && chrAtual != ')') {
                    if (this.haProxChar()) {
                        chrAtual = this.getNextChar();
                    } else {
                        throw new ExcessaoSintatica("Excessao Sintatica: Parenteses devem ser fechados");
                    }
                }
                if (chrAtual == '(') {
                    contAbreP++;
                }
                if (chrAtual == ')') {
                    contFechaP++;
                }
            }
            //quando sai do while quer dizer que a igualdade vale
            if (chrAtual == ')') {
                fimExpressao = posicaoAtualCursor - 2;

                cmds.add("print");

                //adicionando a expressao
                cmds.add(strArquivo.substring(inicioExpressao - 1, fimExpressao));
                
            } else {
                throw new ExcessaoSintatica("Excessao Sintatica: esperado ')'");
            }
        } else {
            throw new ExcessaoSintatica("Erro sintatico, esperado ')'");
        }

    }
    
    

    public void pulaEspaco() {
        while (this.eEspaco(this.getCharAtual()) && this.haProxChar()) {
            this.getNextChar();
        }
    }

    public void pulaEspaco(int comeco) {
        while (this.eEspaco(strArquivo.charAt(comeco))) {
            this.getNextChar();
            comeco++;
        }
    }

    public void verificaPrintln() {
        if (this.eEspaco(this.getNextChar())) {
            cmds.add("println");
        }
    }

    public void verificaAtribuicao() throws ExcessaoSintatica {

        String expressao = "";

        String variavel = obtemPalvraAnterior(posicaoAtualCursor - 2);
        //expressao ja começa um posicao antes do proximo char;

        //lembrando que o cursor atual esta no igual do sinal de atribuicao
        expressao = this.retiraExpressao();
        cmds.add("Atrib");
        cmds.add(variavel);
        
        cmds.add(expressao);

    }

    public boolean eLetraOuDigito(char c) {
        return this.eLetra(c) && this.eDigito(c);
    }

    public String retiraExpressao() {
        int iniExp, fimExp, possivelFim;
        String expressao = "";
        char charAtual;
        char charAnteriorEspaco;
        char charPosteriorEspaco;

        this.pulaEspaco();

        iniExp = this.getPosicaoAtualCursor();

        while (this.haProxChar()) {
            charAtual = this.getCharAtual();
            if (!this.eEspaco(charAtual) && this.haProxChar()) {
                this.getNextChar();
            } else {
                charAnteriorEspaco = this.strArquivo.charAt(this.getPosicaoAtualCursor() - 1);
                possivelFim = this.getPosicaoAtualCursor() - 1;
                this.pulaEspaco();
                charPosteriorEspaco = this.strArquivo.charAt(this.getPosicaoAtualCursor());
                if (charAnteriorEspaco == ')' && charPosteriorEspaco == '(') {
                    //possivelFim detecta o indice antes do espaco, substring precisa de 1 a mais que o fim da expressao.
                    fimExp = possivelFim + 1;
                    expressao = this.strArquivo.substring(iniExp, fimExp);

                } else if (this.eLetraOuDigito(charAnteriorEspaco) && this.eLetraOuDigito(charPosteriorEspaco)) {
                    //qualquer string diferente de or, mod, div, and deve finalizar a expressao, pois nao pode ser constante
                    if (charPosteriorEspaco == 'o' || charPosteriorEspaco == 'm' || charPosteriorEspaco == 'd' || charPosteriorEspaco == 'a') {
                        String operador = "" + charPosteriorEspaco;
                        if (this.getPosicaoAtualCursor() + 2 < this.strArquivo.length()) {
                            operador += this.getNextChar();
                            operador += this.getNextChar();
                            if (!"or ".equals(operador)) {
                                //se nao for "or ", so pode ser "mod ", "div " ou "and "
                                if (this.haProxChar()) {
                                    operador += this.getNextChar();
                                    if (!operador.equals("mod ") && !operador.equals("div ") && !operador.equals("and ")) {
                                        fimExp = possivelFim + 1;
                                        expressao = this.strArquivo.substring(iniExp, fimExp);
                                    }
                                }

                            }
                        }
                    } else {
                        fimExp = possivelFim + 1;
                        expressao = this.strArquivo.substring(iniExp, fimExp);
                    }
                } else {
                    fimExp = possivelFim + 1;
                    expressao = this.strArquivo.substring(iniExp, fimExp);
                }
            }
        }
        return expressao;
    }

    public void verificaReadInt() throws ExcessaoSintatica {
        this.pulaEspaco();
        if (this.getCharAtual() == '(') {
            pulaEspaco();
            String variavel = "";
            this.getNextChar();
            while (getCharAtual() != ')' && !this.eEspaco(this.getCharAtual())) {
                variavel += this.getCharAtual();
                this.getNextChar();
            }
            cmds.add("ReadInt");
            System.out.println("Variavel do readInt(): " + variavel);
            cmds.add(variavel);

        } else {
            throw new ExcessaoSintatica("Excessao Sintatica: esperado '('");
        }
    }

    public void verificaIf() {

    }

    public void verificaFor() {

    }

    public void verificaWhile() throws ExcessaoSintatica {
        
        String expressaoWhile = "";

        this.pulaEspaco();

        if ( this.getCharAtual() == '(') {
            

            int contAbreP = 1;
            int contFechaP = 0;
            
            do {
                if (this.haProxChar()) {
                    this.getNextChar();
                } else {
                    throw new ExcessaoSintatica("Necessario haver parametros entre os parenteses");
                }
                 this.getNextChar();
            } while (this.eEspaco(this.getCharAtual()));

            int inicioExpressao = posicaoAtualCursor;
            int fimExpressao;


            while (contFechaP != (contAbreP + 1)) {
                if (this.getCharAtual() != '(' && this.getCharAtual() != ')') {
                    if (this.haProxChar()) {
                         this.getNextChar();
                    } else {
                        throw new ExcessaoSintatica("Excessao Sintatica: Parenteses devem ser fechados");
                    }
                }
                if (this.getCharAtual() == '(') {
                    contAbreP++;
                }
                if (this.getCharAtual() == ')') {
                    contFechaP++;
                }
            }
            //quando sai do while quer dizer que a igualdade vale
            if (this.getCharAtual() == ')') {
                fimExpressao = posicaoAtualCursor - 1;

                cmds.add("While");

                //adicionando a expressao
                expressaoWhile = strArquivo.substring(inicioExpressao - 1, fimExpressao+1);
                cmds.add(expressaoWhile);
                
            } else {
                throw new ExcessaoSintatica("Excessao Sintatica: esperado ')'");
            }
           this.getNextChar();
            this.pulaEspaco();
            
            if(this.haDoisProxChar()){
                
                char d = this.getCharAtual();
                char e = this.getNextChar();
                e = this.getCharAtual();
                String possivelDO = ""+ d + e;
                
                if(possivelDO.equals("do")){
                    this.getNextChar();
                    this.pulaEspaco();
                    
                    int indiceInicioListaComandos = this.getPosicaoAtualCursor();
                    int indiceFimListaComandos;
                    String possivelEndWhile = "";
                    
                    while(this.haProxChar()){
                       
                        //String + espaco --> analisa String, comparando com endwhile
                        this.getNextChar();
                        
                        if(this.eEspaco(this.getNextChar())){
                            
                            if(possivelEndWhile.equals("endwhile") ){
                                indiceFimListaComandos = this.getPosicaoAtualCursor();
                                this.obtemListaComandos(indiceInicioListaComandos, indiceFimListaComandos);
                                
                                return;
                            }else{
                                possivelEndWhile = "";
                            }
                        }else{
                            possivelEndWhile += this.getCharAtual();
                        }
                        
                    }
                }else{
                    throw new ExcessaoSintatica("Excessao Sintatica: do esperado");
                }
            }
        } else {
            throw new ExcessaoSintatica("Excessao Sintatica: While incompleto");
        }
    }
    
    public void obtemListaComandos(int indiceInicioListaComandos, int indiceFimListaComandos){
    }

    public String obtemPalvraAnterior(int aux) throws ExcessaoSintatica {
        //aux é o possivel indice de fim da palavra anterior, podendo ser espaco
        while (aux > -1) {
            while (this.eEspaco(strArquivo.charAt(aux))) {
                aux--;
            }
            String nomVarInvertida = "";
            int indiceInicioNomeVarInvertida = aux;
            char charAux = strArquivo.charAt(aux);
            while (!this.eEspaco(strArquivo.charAt(aux))) {
                charAux = strArquivo.charAt(aux);
                nomVarInvertida += charAux;
                aux--;
            }
            int indiceFimNomeVarInvertida = aux + 1;
            //o nome da variavel esta em ordem invertida
            String nomVar = strArquivo.substring(indiceFimNomeVarInvertida, indiceInicioNomeVarInvertida);
            return nomVar;
        }
        throw new ExcessaoSintatica("Excessao Sintatica: variavel invalida");
    }

    public void lerArquivoBuscandoCaracteres() throws ExcessaoSintatica {
        if (!this.arquivoTemFim()) {
            throw new ExcessaoSintatica("Excessao Sintatica: Arquivo sem fim");
        }
        String bufferPalavra = "";//palavra atual

        while (this.haProxChar()) {
            char c = this.getNextChar();
            while (!eEspaco(c)) {
                bufferPalavra += c;
                
                switch (bufferPalavra) {
                    case "print": {
                        char c1 = this.previaProxChar();
                        char c2 = this.previaDoisProxChar();
                        if (c1 == 'l' && c2 == 'n') {
                            this.getNextChar();
                            this.getNextChar();
                            verificaPrintln();
                        } else {
                            try {
                                this.verificaPrint();

                            } catch (ExcessaoSintatica ex) {
                                Logger.getLogger(ObtemCandidatosComandos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;
                    case "readint":
                        this.verificaReadInt();
                        break;

                    case ":=":
                        this.verificaAtribuicao();
                        break;

                    case "if":
                        break;

                    case "while":
                        verificaWhile();
                        break;

                    case "for":
                        break;
                }
                if (this.haProxChar()) {
                    c = this.getNextChar();
                } else {
                    break;
                }
            }
            bufferPalavra = "";
        }
    }
}
