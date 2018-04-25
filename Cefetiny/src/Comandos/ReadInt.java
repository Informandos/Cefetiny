package Comandos;

import java.util.Scanner;
import memoria.MemoriaVariaveis;
import memoria.MemoriaComando;

/**
 *
 * @author aluno
 */
public class ReadInt {
    MemoriaVariaveis variavel = new MemoriaVariaveis(); 
    MemoriaComando comando = new MemoriaComando();
    
    public ReadInt(){
        
    }
    
    public MemoriaVariaveis getVariavel() {
        return variavel;
    }

    public void setVariaveis(MemoriaVariaveis variavel) {
        this.variavel = variavel;
    }
    
    public MemoriaComando getComando() {
        return comando;
    }

    public void setComando(MemoriaComando comando) {
        this.comando = comando;
    }
    
    public void executaReadInt(MemoriaVariaveis variavel) {
        int valorInteiro;
        Scanner ler = new Scanner(System.in);
        int i = comando.getPosicao("readint");
        
        comando.tiraPorPosisao(i);
        try{
        valorInteiro = ler.nextInt();
        variavel.setListaVariaveis(variavel,valorInteiro);
        } catch(NumberFormatException ex){
            System.out.println("Entrada inválida, o valor digitado não é um número!");
        }
    }
}
