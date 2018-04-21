/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

/**
 *
 * @author User
 */
public class Comando {
    Object metodo;

    public Comando() {
    }

    public Comando(Object Metodo) {
        this.metodo = metodo;
    }

    public Object getComando() {
        return metodo;
    }

    public void setComado(Object Metodo) {
        this.metodo = metodo;
    }
    
    
}
