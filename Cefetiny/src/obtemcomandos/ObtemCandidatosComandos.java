package obtemcomandos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import obtemcomandos.ExcessaoSintatica;

public class ObtemCandidatosComandos {

    private ArrayList cmds = new ArrayList();
    private static int posicaoAtualCursor = 0;
    private String strArquivo;
    private int indiceEnd;

    public ObtemCandidatosComandos() {

    }

    public void setStrArquivo(String strArquivo) {
        this.strArquivo = strArquivo;
    }

    public boolean arquivoTemFim() {
        String palavra = "";
        for (int indice = strArquivo.length(); indice > -1; indice++) {
            if (!this.eEspaco(strArquivo.charAt(indice))) {
                palavra += strArquivo.charAt(indice);
            }
            if (palavra.length() == 3) {
                if (palavra.equals("dne")) {
                    this.indiceEnd = indice;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //verifica se ha um proximo caracter que forma um comando, ie, se ha algo antes de end
    public boolean haProxChar() {
        if (posicaoAtualCursor < this.indiceEnd) {
            return true;
        }
        return false;
    }

    public char getNextChar() {
        if (this.haProxChar()) {
            char c = strArquivo.charAt(posicaoAtualCursor);
            posicaoAtualCursor++;
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public int getNumNextChar() {
        if (this.haProxChar()) {
            char c = strArquivo.charAt(posicaoAtualCursor);
            posicaoAtualCursor++;
            int aux = (int) c;
            return aux;
        }
        System.out.println("Indice >= tamanho arquivo");
        return -1;
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
            char c = strArquivo.charAt(posicaoAtualCursor);
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public int getPosicaoAtualCursor() {
        return posicaoAtualCursor;
    }

    public boolean eEspaco(char teste) {
        int k = (int) teste;
        return !(k != 9 && k != 32 && k != 10);
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
        char chrAtual;
        do {
            chrAtual = this.getNextChar();

        } while (this.eEspaco(chrAtual));

        if (chrAtual == '(') {
            chrAtual = this.getNextChar();

            while (this.eEspaco(chrAtual)) {
                chrAtual = this.getNextChar();
            }
            int inicioExpressao = posicaoAtualCursor;
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
                fimExpressao = posicaoAtualCursor;
                cmds.add("print");
                //adicionando a expressao
                cmds.add(strArquivo.substring(inicioExpressao, fimExpressao));
            } else {
                throw new ExcessaoSintatica("Excessao Sintatica: esperado ')'");
            }
        } else {
            throw new ExcessaoSintatica("Erro sintatico, esperado ')'");
        }

    }
    
    public void pulaEspaco() {
        while (this.eEspaco(this.getCharAtual())) {
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

        expressao = this.obtemExpressao();
        cmds.add("Atrib");
        cmds.add(variavel);
        cmds.add(expressao);

    }

    public String obtemExpressao() {
        String expressao = "";
        int inicioExp = this.getPosicaoAtualCursor();
        int fimExp = 0;
        //Para cada espaco, verificar os caracters que ele para obter o fim da expressao
        //constante (numero ou letra) ou parenteses fechando ) - espaco(s) >=0 - constante(numero ou letra) ou parenteses abindo ( ou ; --> fim expressao
        while (this.haProxChar()) {
            if (this.eEspaco(this.getNextChar())) {
                
                //String com palavra formado por caracteres anteriores ao igual;
                String antEspaco = "";
                
                while (this.haProxChar()) {
                    if (strArquivo.charAt(posicaoAtualCursor-1) == ')' || this.eDigito(getCharAtual()) || this.eLetra(getCharAtual())) {
                        if(this.haProxChar()){
                            this.pulaEspaco(posicaoAtualCursor);
                            if (this.eDigito(this.getCharAtual()) || this.getCharAtual() == ';') {
                                fimExp = this.getPosicaoAtualCursor();
                                expressao = strArquivo.substring(inicioExp, fimExp + 1);
                                return expressao;
                            } else {
                                //qualquer string diferente de or, mod, div, and ou de =, >, < <=, >=, <> ou ) deve finalizar a expressao, pois nao pode ser constante
                                if(this.haProxChar()){
                                    String operador = "" + strArquivo.charAt(posicaoAtualCursor + 1); 
                                    if(operador != "=" && operador != ">" && operador != "<" && operador != ")"){
                                        operador+= strArquivo.charAt(posicaoAtualCursor + 2);
                                        operador += strArquivo.charAt(posicaoAtualCursor + 3);
                                        if (!"or ".equals(operador)) {
                                            operador += strArquivo.charAt(posicaoAtualCursor + 4);
                                            if (!operador.equals("mod ") && !operador.equals("div ") && !operador.equals("and ")) {
                                                fimExp = this.getPosicaoAtualCursor();
                                                expressao = strArquivo.substring(inicioExp, fimExp + 1);
                                                //Registrar os quatro caracteres lidos
                                                this.getNextChar();
                                                this.getNextChar();
                                                this.getNextChar();
                                                this.getNextChar();

                                                return expressao;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return expressao;
    }

    public void verificaReadInt() throws ExcessaoSintatica {
        this.pulaEspaco();
        if(this.getCharAtual()=='('){
            pulaEspaco();
            String variavel = "";
            while(getCharAtual()!=')' && !this.eEspaco(this.getCharAtual())){
                variavel+= this.getCharAtual();
                this.getNextChar();
            }
            cmds.add("ReadInt");
            cmds.add(variavel);
            
        }else{
            throw new ExcessaoSintatica("Excessao Sintatica: esperado '('");
        }
    }

    public void verificaIf() {

    }

    public void verificaFor() {
        
    }

    public void verificaWhile() {
        
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
        
        while(this.haProxChar()) {
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
                    case "readint":
                        this.verificaReadInt();
                        break;
                        
                    case ":=":
                        this.verificaAtribuicao();
                        break;
                        
                    case "if":
                        break;
                        
                    case "while":
                        break;
                        
                    case "for":
                        break;
                }
                c = this.getNextChar();
            }
            bufferPalavra = "";
        }
    }
}
