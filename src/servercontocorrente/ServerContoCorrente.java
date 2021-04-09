/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercontocorrente;

import java.util.Scanner;

/**
 *
 * @author Alessio
 */
public class ServerContoCorrente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContiManager cm = new ContiManager();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci la porta");
        new ServerInstance(sc.nextInt()).goSever();
        //System.out.println("lista conti\n"+cm.getListaConti());
    }
    
}
