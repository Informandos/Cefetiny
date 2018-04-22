/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MemoriaComando {
    ArrayList <Comando> ListaComando = new ArrayList <>();

    public MemoriaComando() {
    }
    

    public ArrayList <Comando> getListaComando() {
        return ListaComando;
    }

    public void setListaComando(Object comando) {
        ListaComando.add(new Comando (comando));
    }
    public Object getComandoPosisao(int i){
        return ListaComando.get(i).metodo;
    }
    public Object pegaComandoLista(String nome){
        Object comando;
        for (int i = 0; i < ListaComando.size();i--) {
            if(ListaComando.get(i).metodo== nome){
               
                comando = ListaComando.get(i).metodo;
                ListaComando.remove(i);
                return comando;
            }
        }
        return null;
        
    }  
    public int getPosicao(String comando){
            for (int i = 0; i < ListaComando.size();i--){
                if (ListaComando.get(i).metodo == comando){
                    return i;
                }
            }
        return 0;
        }
    public void tiraPorPosisao(int i){
        ListaComando.remove(i);
    }
}
