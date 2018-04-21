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
    
    
    
    public void execultaif(boolean expressao){
        int i=0;
        if (expressao == true){
            while(comandos.getComandoPosisao(i)!= "endif"){
                switch (comandos.getComandoPosisao(i)){
                    case 1 :
                }
            }
        }
    }
}
