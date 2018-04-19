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
public class MemoriaVariaveis {
    ArrayList<Variaveis> ListaVariaveis = new ArrayList<>();

    public MemoriaVariaveis() {
    }

    public Object getListaVariaveis() {
        return ListaVariaveis;
    }
    
    public Object getListaVariaveisvalor(Object nome) {
         for (int i = 0; i < ListaVariaveis.size(); i++) {
              if(nome == ListaVariaveis.get(i).nome){
                  return ListaVariaveis.get(i).valor;
              }
        }
      
        return null;
    }

    public void setListaVariaveis(Object nome, Object valor) {
        ListaVariaveis.add(new Variaveis(nome, valor)) ;
    }
    
    public void apagaLista(Object nome){
        for (int i = 0; i < ListaVariaveis.size(); i++) {
            if(nome == ListaVariaveis.get(i).nome){
                ListaVariaveis.remove(i);
            }
        }
    }
    
    public Object mostraLista (){
         Object Lista = null; 
        if(!ListaVariaveis.isEmpty()){
            for (int i = 0; i < ListaVariaveis.size(); i++) {
                Lista += "nome : " + ListaVariaveis.get(i).nome + " valor : " + ListaVariaveis.get(i).valor +"\n";
            }
 
        }
        return Lista;
    }
    public void modificaVariaveis(Object nome, Object valor){
        for (int i = 0; i < ListaVariaveis.size(); i++) {
            if(ListaVariaveis.get(i).nome == nome){
                ListaVariaveis.get(i).setValor(valor);
            }
        }
    }
    
}
