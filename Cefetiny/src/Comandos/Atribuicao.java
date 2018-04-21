package Comandos;

import memoria.MemoriaVariaveis;

public class Atribuicao{
    
    MemoriaVariaveis variaveis = new MemoriaVariaveis();
    
    public Atribuicao(Object nome, Object valor) {
        
        Object x = variaveis.getListaVariaveisvalor(nome);
        
        if (x == null) {
            variaveis.setListaVariaveis(nome, valor);
        } else {
            variaveis.modificaVariaveis(nome, valor);
        }
    }    
    
}