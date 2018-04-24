/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import java.util.ArrayList;
import memoria.MemoriaComando;
import memoria.MemoriaVariaveis;
public class For {
    MemoriaComando comandos = new MemoriaComando();
    MemoriaVariaveis variaveis = new MemoriaVariaveis();

    public MemoriaComando getComandos() {
        return comandos;
    }

    public void setComandos(MemoriaComando comandos) {
        this.comandos = comandos;
    }

    public MemoriaVariaveis getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(MemoriaVariaveis variaveis) {
        this.variaveis = variaveis;
    }

    public For() {
    }
     public void executafor(String nomeVariavel,Object encrementador,Object ultimoValor){
         Object j = variaveis.getListaVariaveisvalor(nomeVariavel);
         int i = comandos.getPosicao("for");
         if(encrementador == "++"){
             while(ultimoValor != j){
                  if(comandos.getComandoPosisao(i) == "print"){
                    //chama comando print
                }
                if(comandos.getComandoPosisao(i) == "Println"){
                 Println print = new Println();
                 print.executaPrintln();
                    
                }
                if(comandos.getComandoPosisao(i) == "readint"){
                    //chama readint
                }
                if(comandos.getComandoPosisao(i) == "atrib"){
                    //chama readint
                }
                if(comandos.getComandoPosisao(i) == "if"){
                   // if if
                }
                if(comandos.getComandoPosisao(i) == "for"){
                   // if if
                }
                 if(comandos.getComandoPosisao(i) == "while"){
                   // if if
                }
                 i++;
             }
         }else if(encrementador == "--"){
             while(ultimoValor != j){
                  if(comandos.getComandoPosisao(i) == "print"){
                    //chama comando print
                }
                if(comandos.getComandoPosisao(i) == "Println"){
                 Println print = new Println();
                 print.executaPrintln();
                    
                }
                if(comandos.getComandoPosisao(i) == "readint"){
                    //chama readint
                }
                if(comandos.getComandoPosisao(i) == "atrib"){
                    //chama readint
                }
                if(comandos.getComandoPosisao(i) == "if"){
                   // if if
                }
                if(comandos.getComandoPosisao(i) == "for"){
                   // if if
                }
                 if(comandos.getComandoPosisao(i) == "while"){
                   // if if
                }
                 i++;
             }
         }
    }
}
    

