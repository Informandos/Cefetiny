/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cefetiny;

/**
 *
 * @author User
 */
public class Cefetiny {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MemoriaVariaveis me = new MemoriaVariaveis();
        me.setListaVariaveis("a",1);
        me.setListaVariaveis("b",2);
        me.setListaVariaveis("b",3);
        System.out.println(me.mostraLista());
        me.modificaVariaveis("a", 9);
        System.out.println(me.mostraLista());
        
        
    }
    
}
