
package io;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




public class EntradaDados {
    private String strArquivo;
    
    public EntradaDados(){
        
    }
    
    public void lerArquivo(String arquivo) throws IOException {
        //Arquivo deve estar na pasta Cefetiny, que é a pasta do projeto
        Path caminho = Paths.get(arquivo);
        
        try{
            //armazena o arquivo em bytes
            byte[] arquivoBytes = Files.readAllBytes(caminho);
            //transforma o arquivo em bytes em String
            //O arquivo vira uma unica String
            this.strArquivo = new String(arquivoBytes);
            
        } catch(Exception erro) {
            System.out.println("Erro na leitura de arquivo");
        }
    }
    
    public String getArquivo(){
        return this.strArquivo;
    }
}
