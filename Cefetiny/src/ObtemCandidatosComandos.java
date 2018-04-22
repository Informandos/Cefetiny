/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.ArrayList;

/**
 *
 * @author julia
 */
public class ObtemCandidatosComandos {

    public ArrayList cmds = new ArrayList();
    public static int i = 0;
    public String arquivo;

    public ObtemCandidatosComandos() {

    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public char getNextChar() {
        if (i < arquivo.length()) {
            char c = arquivo.charAt(i);
            i++;
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public int getNumNextChar() {
        if (i < arquivo.length()) {
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
        if (i < arquivo.length()) {
            char c = arquivo.charAt(i);
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public char previaDoisProxChar() {
        if (i + 1 < arquivo.length()) {
            char c = arquivo.charAt(i);
            return c;
        }
        System.out.println("Indice >= tamanho arquivo");
        return ';';
    }

    public boolean haProxChar() {
        if (i < arquivo.length()) {
            return true;
        }
        return false;
    }

    public boolean haDoisProxChar() {
        if (i + 1 < arquivo.length()) {
            return true;
        }
        return false;
    }

    public boolean eEspaco(char teste) {
        int k = (int) teste;
        if (k != 9 && k != 32 && k != 10) {
            return false;
        }
        return true;
    }

    public void ePrint() {
        String expressao = "";

        char t = this.getNextChar();
        while (t != ')') {
            switch (t) {
                case ' ':
                    //ok, fim do print
                    break;
                case '(':
                    char u = this.getNextChar();
                    while (u != '(') {
                        expressao += this.getNextChar();
                        u = this.getNextChar();
                    }
                    break;
                case 'l':
                    if (this.previaProxChar() == 'n') {

                    }
                    break;
                default:
                    System.out.print("Caracter nao esperado");
                    System.exit(0);
                    break;
            }
            t = this.getNextChar();
        }

        cmds.add("print");
        cmds.add(expressao);
        expressao = "";
        //fim do caso print
    }

    public void ePrintln() {

    }

    public void eAtribuicao() {

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
        }
    }

    public boolean arquivoTemFim() {
        String palavra = "";
        char caracter = ' ';
        for (int indice = arquivo.length(); indice > -1; indice++) {
            if (!this.eEspaco(arquivo.charAt(indice))) {

            }
        }
    }

    public boolean eFimArquivo() {
        return (i == arquivo.length());
    }

    public void lerArquivoBuscandoCaracteres() {
        String pal = "";//palavra atual
        String aPal = "";//palavra anterior
        String pPal = "";//proxima palavra

        if (this.haProxChar()) {
            char c = this.getNextChar();

            while (!eEspaco(c)) {
                if (pal == "end") {
                    return;
                }
                switch (pal) {
                    case "print":
                        this.ePrint();
                    case ":=":
                        this.eAtribuicao();
                    case "if":
                        break;
                }
            }

            if (ic == 9 || ic == 32 || ic == 10) {
                aPal = pal;
                pal = "";
            }
        }
    }
}
