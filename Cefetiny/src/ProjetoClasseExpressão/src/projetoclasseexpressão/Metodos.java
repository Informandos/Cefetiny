

package projetoclasseexpressão;

import java.util.*;
import javax.swing.JOptionPane;

public class Metodos {
    
    private String[] pilhaToken;
    private char[] expressao;
    private String[] pilhaSaida;
    private String[] pilhaOperadores;
    private int tamanhoExpressao;

    public Metodos() {}
    
    public String Expressao (String expressao) {
        this.expressao = replace(expressao).toCharArray();
        ordenaExpressao();
        separaPilhas();
        return Float.toString(resolveExpressao());
    }
    
    public String replace (String expressao) {
        expressao = expressao.replaceAll("sqrt","s");
        expressao = expressao.replaceAll("mod","m");
        expressao = expressao.replaceAll("div","d");
        expressao = expressao.replaceAll("and","a");
        expressao = expressao.replaceAll("not","n");
        expressao = expressao.replaceAll("or","o");
        return expressao;
    }
    
    public void contaTokens(){
        String aux = new String();
        int contaTokens = 0;
        boolean negativo=false;
        
        for(int i=0; i<expressao.length; i++){
            if(expressao[i]>='0'&&expressao[i]<='9'){
                aux=String.valueOf(expressao[i]);
                if(negativo==true){
                    aux=String.valueOf(expressao[i-1]);
                    aux+=String.valueOf(expressao[i]);
                }
                while((i<expressao.length-1)&&((expressao[i+1]>='0'&&expressao[i+1]<='9')||(expressao[i+1]==','))){
                    if(expressao[i+1]==','){
                        aux+=".";
                        i++;
                    }
                    else{
                        aux+=String.valueOf(expressao[i+1]);
                        i++;
                    }
                }

                contaTokens++;
                negativo=false;
                aux = new String();
            } else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]>='0')&&(expressao[i+1]<='9'))||((i>0)&&(i<expressao.length)&&(expressao[i]=='-')&&(expressao[i-1]=='(')&&!(expressao[i+1]=='('))){
                negativo=true;
            } else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]=='('))){
                contaTokens+=2;
            } else if((i<expressao.length)&&(expressao[i]=='*'||expressao[i]=='+'||expressao[i]=='/'||expressao[i]=='-'||expressao[i]=='X'||expressao[i]=='('||expressao[i]==')'||expressao[i]=='^'||expressao[i]=='s'||expressao[i]=='m'||expressao[i]=='d'||expressao[i]=='a'||expressao[i]=='o'||expressao[i]=='n')){
                contaTokens++;
            }
        }
        tamanhoExpressao = contaTokens;
    }
    
    public void ordenaExpressao(){
        this.contaTokens();
        String aux = new String();
        int indiceOrdenada = 0;
        int contaNumeros = 0;
        int contaOperadores = 0;
        boolean negativo=false;
        pilhaToken=new String[tamanhoExpressao+1];
        for(int i=0; i<expressao.length; i++){
            if(expressao[i]>='0'&&expressao[i]<='9'){
                contaNumeros++;
                aux=String.valueOf(expressao[i]);
                if(negativo==true){
                    aux=String.valueOf(expressao[i-1]);
                    aux+=String.valueOf(expressao[i]);
                }
                while((i<expressao.length-1)&&((expressao[i+1]>='0'&&expressao[i+1]<='9')||(expressao[i+1]==','))){
                    if(expressao[i+1]==','){
                        aux+=".";
                        i++;
                    }
                    else{
                        aux+=String.valueOf(expressao[i+1]);
                        i++;
                    }
                }
                
                pilhaToken[indiceOrdenada]=aux;
                indiceOrdenada++;
                negativo=false;
                aux = new String();
            }
            
            else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]>='0')&&(expressao[i+1]<='9'))||((i>0)&&(i<expressao.length)&&(expressao[i]=='-')&&(expressao[i-1]=='(')&&!(expressao[i+1]=='('))){
                negativo=true;
            }
            
            else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]=='('))){
                pilhaToken[indiceOrdenada]="-1";
                indiceOrdenada++;
                pilhaToken[indiceOrdenada]="*";
                indiceOrdenada++;
                contaOperadores+=2;
            }
            
            else if((i<expressao.length)&&(expressao[i]=='*'||expressao[i]=='+'||expressao[i]=='/'||expressao[i]=='-'||expressao[i]=='X'||expressao[i]=='('||expressao[i]==')'||expressao[i]=='^'||expressao[i]=='s'||expressao[i]=='m'||expressao[i]=='d'||expressao[i]=='a'||expressao[i]=='o'||expressao[i]=='n')){
                contaOperadores++;
                pilhaToken[indiceOrdenada]=String.valueOf(expressao[i]);
                indiceOrdenada++;
            }
        }
        pilhaSaida = new String[contaNumeros+contaOperadores];
        pilhaOperadores = new String[contaOperadores];
        //JOptionPane.showMessageDialog(null, pilhaToken);
    }
    
    public void separaPilhas(){
        pilhaToken[tamanhoExpressao]="end";
        JOptionPane.showMessageDialog(null, pilhaToken);
        int indicePilhaSaida = 0;
        int indicePilhaOperadores = 0;
        for(int i=0; i<=tamanhoExpressao; i++){
            if(pilhaToken[i].equalsIgnoreCase("*")||pilhaToken[i].equalsIgnoreCase("+")||pilhaToken[i].equalsIgnoreCase("/")||pilhaToken[i].equalsIgnoreCase("-")||pilhaToken[i].equalsIgnoreCase("(")||pilhaToken[i].equalsIgnoreCase(")")||pilhaToken[i].equalsIgnoreCase("^")||pilhaToken[i].equalsIgnoreCase("s")||pilhaToken[i].equalsIgnoreCase("m")||pilhaToken[i].equalsIgnoreCase("d")||pilhaToken[i].equalsIgnoreCase("a")||pilhaToken[i].equalsIgnoreCase("n")||pilhaToken[i].equalsIgnoreCase("o")){
                switch (pilhaToken[i]){
                    case "+":
                        if((indicePilhaOperadores>0)&&(!pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("("))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("(")); indicePilhaOperadores--){
                                pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                indicePilhaSaida++;
                            }
                            indicePilhaOperadores++;
                        }
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "-":
                        if((indicePilhaOperadores>0)&&(!pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("("))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("(")); indicePilhaOperadores--){
                                pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                indicePilhaSaida++;
                            }
                            indicePilhaOperadores++;
                        }
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "*":
                        if((indicePilhaOperadores>0)&&((pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("*"))||(pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("/"))||(pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("^")))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("(")); indicePilhaOperadores--){
                                if(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("+")&&!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("-")){
                                    pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                    pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                    indicePilhaSaida++;
                                }
                            }
                                indicePilhaOperadores+=2;
                        }
                        
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "/":
                        if((indicePilhaOperadores>0)&&(pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("*")||pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("/")||pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("^"))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("(")); indicePilhaOperadores--){
                                if(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("+")&&!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("-")){
                                    pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                    pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                    indicePilhaSaida++;
                                }
                            }
                                indicePilhaOperadores+=2;
                        }
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "(":
                        pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                        indicePilhaOperadores++;
                        break;
                        
                    case ")":
                        while(indicePilhaOperadores>0&&!pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("(")){
                            pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-1];
                            indicePilhaSaida++;
                            indicePilhaOperadores--;
                        }
                        indicePilhaOperadores--;
                        break;
                    
                    case "^":
                        pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                        indicePilhaOperadores++;
                        break;
                    
                    case "s":
                        pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                        indicePilhaOperadores++;
                        break;
                }
            } else if (pilhaToken[i].equalsIgnoreCase("end")){
                indicePilhaOperadores--;
                if(indicePilhaOperadores>=0){
                do{
                    pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores];
                    indicePilhaSaida++;
                    indicePilhaOperadores--;
                    }
                    while(indicePilhaOperadores>=0);
                }
                tamanhoExpressao = indicePilhaSaida;
            } else {
                pilhaSaida[indicePilhaSaida]=pilhaToken[i];
                indicePilhaSaida++;
            }
        }
        //JOptionPane.showMessageDialog(null,pilhaOperadores);
    }
    
    public Float resolveExpressao(){
        int i=0;
        JOptionPane.showMessageDialog(null, pilhaSaida);
        int topo=tamanhoExpressao-1;
        double auxiliar;
        while(topo>1){
            if(pilhaSaida[i].equalsIgnoreCase("*")||pilhaSaida[i].equalsIgnoreCase("+")||pilhaSaida[i].equalsIgnoreCase("/")||pilhaSaida[i].equalsIgnoreCase("-")||pilhaSaida[i].equalsIgnoreCase("(")||pilhaSaida[i].equalsIgnoreCase(")")||pilhaSaida[i].equalsIgnoreCase("^")||pilhaToken[i].equalsIgnoreCase("s")||pilhaToken[i].equalsIgnoreCase("m")||pilhaToken[i].equalsIgnoreCase("d")||pilhaToken[i].equalsIgnoreCase("a")||pilhaToken[i].equalsIgnoreCase("n")||pilhaToken[i].equalsIgnoreCase("o")){
                switch (pilhaSaida[i]){
                    case "+":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])+Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                        
                    case "-":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])-Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                        
                    case "*":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])*Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2; 
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                        
                    case "/":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])/Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                    
                    case "^":
                        JOptionPane.showMessageDialog(null,pilhaSaida[i-2] + "  " + pilhaSaida[i-1]);
                        auxiliar=Math.pow(Float.parseFloat(pilhaSaida[i-2]), Float.parseFloat(pilhaSaida[i-1]));
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                    case "s":
                        JOptionPane.showMessageDialog(null,"a");
                        auxiliar=Math.sqrt(Float.parseFloat(pilhaSaida[i-1]));
                        pilhaSaida[i-1]=String.valueOf(auxiliar);
                        topo-=1;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+1];
                            }
                        }
                        i-=1;
                        break;
                }
            }
            i++;
        }
        //JOptionPane.showMessageDialog(null,pilhaSaida);
        return(Float.parseFloat(pilhaSaida[0]));
    }
    
}


