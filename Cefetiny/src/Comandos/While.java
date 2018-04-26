/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import memoria.MemoriaComando;
import memoria.MemoriaVariaveis;
public class While {
    String expressao;
    MemoriaComando comandos = new MemoriaComando();
    MemoriaVariaveis variaveis;

    public While(String expressao, MemoriaVariaveis variaveis) {
        this.expressao = expressao;
        this.variaveis = variaveis;
    }

    
    public void executaWhile() {
        int i=comandos.getPosicao("while");
      //while(expersao == true){
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
                    //chama atribuicao
                }
                if(comandos.getComandoPosisao(i) == "if"){
                   If comando = new If();
                   comando.executaIf(true);
                    //presisa do analisador sintatico
                }
                  
                if(comandos.getComandoPosisao(i) == "for"){
                   // if if
                }
                 if(comandos.getComandoPosisao(i) == "while"){
                   // if if
                }
                 else{
                     Atribuicao comando = new Atribuicao();
                     comando.cmdAtribuicao(comandos.getComandoPosisao(i),comandos.getComandoPosisao(i) );
                 }
        //}         
      }
          
}
