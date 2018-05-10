package cefetiny;

import java.io.IOException;
import io.EntradaDados;
import java.util.logging.Level;
import java.util.logging.Logger;
import memoria.MemoriaVariaveis;
import obtemcomandos.ExcessaoSintatica;
import obtemcomandos.ObtemCandidatosComandos;

public class Cefetiny {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Teste");
        EntradaDados entradaArquivo = new EntradaDados();
        entradaArquivo.lerArquivo("Arquivo_Texto.txt");
        System.out.println(entradaArquivo.getArquivo());
        
        try {
            ObtemCandidatosComandos obtemComandos = new ObtemCandidatosComandos(entradaArquivo.getArquivo());
        } catch (ExcessaoSintatica ex) {
            Logger.getLogger(Cefetiny.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MemoriaVariaveis memoria = new MemoriaVariaveis();
        memoria.setListaVariaveis("a",1);
        memoria.setListaVariaveis("b",2);
        memoria.setListaVariaveis("b",3);
        System.out.println(memoria.mostraLista());
        memoria.modificaVariaveis("a", 9);
        System.out.println(memoria.mostraLista());
    }
}
