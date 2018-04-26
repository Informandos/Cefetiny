package obtemcomandos;

public class ExcessaoSintatica extends Exception{

    //A excessao sintatica tem a mensagem passada como parametro.
    public ExcessaoSintatica(String msg){
        super(msg);
    }
}
