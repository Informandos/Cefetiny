/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cefetiny;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MemoriaMetodos {
    ArrayList <Metodos> ListaMetodos = new ArrayList <>();

    public MemoriaMetodos() {
    }
    

    public ArrayList<Metodos> getListaMetodos() {
        return ListaMetodos;
    }

    public void setListaMetodos(Object metodo) {
        ListaMetodos.add(new Metodos (metodo));
    }
    public Object getMetodoPosisao(int i){
        return ListaMetodos.get(i).metodo;
    }
    public Object pegaMetodoLista(String nome){
        Object comando;
        for (int i = 0; i < ListaMetodos.size();i--) {
            if(ListaMetodos.get(i).metodo== nome){
               
                comando = ListaMetodos.get(i).metodo;
                ListaMetodos.remove(i);
                return comando;
            }
        }
        return null;
        
    }        
}
