package obtemcomandos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import obtemcomandos.ExcessaoSintatica;

public class ObtemCandidatosComandos {

    private ArrayList cmds = new ArrayList();
    private static int i = 0;
    private String arquivo;
    private int indiceEnd;

    public ObtemCandidatosComandos() {

    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public boolean arquivoTemFim() {
        String palavra = "";
        for (int indice = arquivo.length(); indice > -1; indice++) {
            if (!this.eEspaco(arquivo.charAt(indice))) {
                palavra += arquivo.charAt(indice);
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

    //verifica se ha um proximo caracter, ie, se ha algo antes de end
    public boolean haProxChar() {
        if (i < this.indiceEnd) {
            return true;
        }
        return false;
    }

    public char getNextChar() {
        if (this.haProxChar()) {
            char c = arquivo.charAt(i);
            i++;
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public int getNumNextChar() {
        if (this.haProxChar()) {
            char c = arquivo.charAt(i);
            i++;
            int aux = (int) c;
            return aux;
        }
        System.out.println("Indice >= tamanho arquivo");
        return -1;
    }

    //Este metodo olha qual é o prox char sem incrementar i
    public char previaProxChar() {
        if (this.haProxChar()) {
            char c = arquivo.charAt(i);
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public boolean haDoisProxChar() {
        return i + 1 < this.indiceEnd;
    }

    public char previaDoisProxChar() {
        if (i + 1 < arquivo.length()) {
            char c = arquivo.charAt(i);
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public boolean eEspaco(char teste) {
        int k = (int) teste;
        return !(k != 9 && k != 32 && k != 10);
    }

    public int indiceFechaAspas(int indiceChr) {
        for (int p = indiceChr + 1; p < arquivo.length() - 2; p++) {
            if (arquivo.charAt(p) == '"') {
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
            status &= eLetra(str.charAt(a)) || eNumero(str.charAt(a)) || (str.charAt(a) == '"');
        }
        return status;
    }

    public boolean eLetra(char chr) {
        int vlrChr = (int) chr;
        return (vlrChr > 96 && vlrChr < 123);
    }

    public boolean eNumero(char chr) {
        int vlrChr = (int) chr;
        return (vlrChr > 48 && vlrChr < 58);
    }

    public void ePrint() throws ExcessaoSintatica {
        String expressao = "";
        char chrAtual;
        do {
            chrAtual = this.getNextChar();

        } while (this.eEspaco(chrAtual));

        switch (chrAtual) {
            case '(':
                chrAtual = this.getNextChar();
                while (this.eEspaco(chrAtual)) {
                    chrAtual = this.getNextChar();
                }

                if (chrAtual == '"') {
                    String bufferStr = "";
                    int fimAspas = this.indiceFechaAspas(i);
                    if (fimAspas == -1) {
                        throw new ExcessaoSintatica("Erro sintatico, aspas devem ser fechadas");
                    } else {
                        for (int aux = 0; aux < this.indiceFechaAspas(i); i++) {
                            bufferStr += "" + chrAtual;
                            chrAtual = getNextChar();
                        }
                        if (!this.verificaString(bufferStr)) {
                            throw new ExcessaoSintatica("Erro sintatico, String nao confere formacao por letra e digito");
                        }
                        while (this.eEspaco(chrAtual)) {
                            chrAtual = this.getNextChar();
                        }
                        if (chrAtual == ')') {
                            cmds.add("print");
                            cmds.add("String");
                            cmds.add(bufferStr);
                        } else {
                            throw new ExcessaoSintatica("Erro sinatatico, Sem parenteses para fechar print");
                        }
                    }
                } else {
                    //Verificando a existencia de unop's not ou sqrt
                    String bufferTestUnop = chrAtual + "";
                    char vet[] = new char[4];
                    vet[0] = chrAtual;
                    int indiceVetor = 1;
                    //armazaena os dois proximos caracteres em um buffer
                    while (!this.eEspaco(chrAtual) && indiceVetor < 3) {
                        chrAtual = this.getNextChar();
                        bufferTestUnop += chrAtual;
                        vet[indiceVetor] = chrAtual;
                        indiceVetor++;
                    }
                    //verifica se os tres caracteres da string buffer formam not
                    if (bufferTestUnop.equals("not")) {

                    } else {
                        //verifica se os quatro caracteres da string buffer formam sqrt
                        bufferTestUnop += this.getNextChar();
                        if (bufferTestUnop.equals("sqrt")) {

                        } else {
                            //senao nao e permitido os parentes e basta chamar tudo de expressao o que esta antes
                        }
                    }

                }
                break;
            default:
                throw new ExcessaoSintatica("Erro sintatico, Caracter nao esperado");
        }
    }

    public void ePrintln() {

    }

    public void eAtribuicao() {
        /*
        String pal = "";//palavra atual
        String aPal = "";//palavra anterior
        String pPal = "";//proxima palavra

        cmds.add("atribuicao");
        cmds.add(aPal);
        //obtendo proxima palavra
        String expressao = "";
        i++;
        ic = (int) arquivo.charAt(i);
        while (ic != 9 && ic != 10 && ic != 32) {
            expressao += arquivo.charAt(i);
            ic = (int) arquivo.charAt(i);
            i++;
        }*/
    }

    public boolean eIf() {
        return false;
    }

    public boolean eFor() {
        return false;
    }

    public boolean eWhile() {
        return false;
    }

    public void lerArquivoBuscandoCaracteres() {
        if (!this.arquivoTemFim()) {
            System.out.println("Arquivo sem fim");
            System.exit(0);
        }

        String pal = "";//palavra atual
        String aPal = "";//palavra anterior

        if (this.haProxChar()) {
            char c = this.getNextChar();

            while (!eEspaco(c)) {
                if (pal == "end") {
                    return;
                }
                switch (pal) {
                    case "print":
                {
                    try {
                        this.ePrint();
                    } catch (ExcessaoSintatica ex) {
                        Logger.getLogger(ObtemCandidatosComandos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    case ":=":
                        this.eAtribuicao();
                    case "if":
                        break;
                }
            }

            if (eEspaco(c)) {
                aPal = pal;
                pal = "";
            }
        }
    }
}
