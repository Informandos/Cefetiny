package projetoclasseexpressão;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Felps
 */
public class ProjetoClasseExpressão {

    public static void main(String[] args) {

        Metodos m = new Metodos();
        
        JOptionPane.showMessageDialog(null,m.Expressao((JOptionPane.showInputDialog("Digite a expressão:"))));

    }
}
