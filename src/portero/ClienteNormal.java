/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portero;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class ClienteNormal extends Thread{
    
        private int id;
        private Portero por;
    
    public ClienteNormal(int id, Portero por){
        this.por=por;
        this.id=id;
    }

    @Override
    public void run() {
        
            try {
                
                Random rnd= new Random();
                rnd.setSeed(System.currentTimeMillis()+id);
                
                System.out.println("Cliente Normal ("+id+") ENTRANDO");
                por.EntraNormal();
                Thread.sleep((rnd.nextInt(3)+4)*1000);
                System.out.println("Cliente Normal ("+id+") SALIENDO");
                por.SaleNormal();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ClienteNormal.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }

    
}
