/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portero;

import java.util.Random;

/**
 *
 * @author carlo
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
            Random rnd= new Random();
            rnd.setSeed(System.currentTimeMillis());
            Thread hilos[] = new Thread[12];
            Portero por= new Portero();
            
            for (int i = 0; i < 12; i++) {
                
                if(rnd.nextInt(2)%2==1){
                        hilos[i]= new ClienteNormal(i+1, por);
                }
                else{
                     hilos[i]= new Thread( new ClienteVIP(i+1, por));
                }
                
                hilos[i].start();
                Thread.sleep((rnd.nextInt(2)+1)*1000);
            
        }
            
        for (int i = 0; i < 12; i++) {
            hilos[i].join();
        }
        
        System.out.println("Todos los hilos finalizados");
    }
    
}
