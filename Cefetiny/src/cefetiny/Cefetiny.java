package cefetiny;

import java.io.IOException;
import io.EntradaDados;
import memoria.MemoriaVariaveis;

public class Cefetiny {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Teste");
        EntradaDados entradaArquivo = new EntradaDados();
        entradaArquivo.lerArquivo("Arquivo_Texto.txt");
        System.out.println(entradaArquivo.getArquivo());
        
        MemoriaVariaveis memoria = new MemoriaVariaveis();
        memoria.setListaVariaveis("a",1);
        memoria.setListaVariaveis("b",2);
        memoria.setListaVariaveis("b",3);
        System.out.println(memoria.mostraLista());
        memoria.modificaVariaveis("a", 9);
        System.out.println(memoria.mostraLista());
    }
}
