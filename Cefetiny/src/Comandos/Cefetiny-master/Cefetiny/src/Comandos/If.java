/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import java.util.ArrayList;
import memoria.MemoriaComando;
import memoria.MemoriaVariaveis;

public class If {
    MemoriaComando comandos = new MemoriaComando();
    
    public If() {
    }
    

    public MemoriaComando getComandos() {
        return comandos;
    }

    public void setComandos(MemoriaComando comandos) {
        this.comandos = comandos;
    }
    
    
    
    public void executaIf(boolean expressao){
        int i = comandos.getPosicao("if");
        comandos.tiraPorPosisao(i);
        i++;
        int com = 0;
        if (expressao == true){
            while(comandos.getComandoPosisao(i)!= "endif"){
                if(comandos.getComandoPosisao(i) == "print"){
                    //chama comando print
                }
                if(comandos.getComandoPosisao(i) == "Println"){
                 Println print = new Println();
                 print.executaPrintln();
                    
                }
                if(comandos.getComandoPosisao(i) == "readint"){
                    ReadInt readint = new ReadInt();
                    //readint.executaReadInt(this); //
                }
                if(comandos.getComandoPosisao(i) == "atribuicao"){
                    //chama atrib
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
        else{
            while(com != 1){
            if(comandos.getComandoPosisao(i)== "endif"){
                comandos.tiraPorPosisao(i);
                com = 1;
            }
            else{
                comandos.tiraPorPosisao(i);
            }
           }
        }
    }
}
