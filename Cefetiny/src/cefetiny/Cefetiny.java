package cefetiny;

import io.EntradaDados;
import java.io.IOException;

public class Cefetiny {

    public static void main(String[] args) throws IOException {
        System.out.println("Teste");
        EntradaDados entradaArquivo = new EntradaDados();
        entradaArquivo.lerArquivo();
    }
}
