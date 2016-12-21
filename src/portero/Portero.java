/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portero;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author carlo
 */
public class Portero {
    
    int aforo=6;
    int cola=0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaNormal = mutex.newCondition();
    Condition colaVIP = mutex.newCondition();
    
    public  void EntraNormal() throws InterruptedException{
       
      try{
        mutex.lock();
      
        System.out.println("---------------------------------------------------------------------------------------- ENTRA NORMAL CON AFORO " +aforo);
        
        while(aforo-1<0) colaNormal.await();
        
        aforo=aforo-1;
      }
      finally{
          mutex.unlock();
      }
        
    }
    public  void SaleNormal(){
        
        try{
        mutex.lock();
        aforo=aforo+1;
        
        if(cola>0) colaVIP.signal(); 
        
        else colaNormal.signal();
        
        System.out.println("---------------------------------------------------------------------------------------- SALE NORMAL CON AFORO " +aforo);

        }
      finally{
          mutex.unlock();
      }
    }
    public  void EntraVIP() throws InterruptedException{
        try{
        mutex.lock();
        System.out.println("---------------------------------------------------------------------------------------- ENTRA VIP CON AFORO " +aforo);
        cola++;
        while(aforo-3<0) colaVIP.await();
        cola--;
        aforo=aforo-3;
        
        }
      finally{
          mutex.unlock();
      }
    }
    public  void SaleVIP(){
        try{
        mutex.lock();
        aforo=aforo+3;
        
        if(cola>0) colaVIP.signal(); 
        
        else colaNormal.signal();
        
        System.out.println("---------------------------------------------------------------------------------------- SALE VIP CON AFORO " +aforo);

        }
      finally{
          mutex.unlock();
      }
    }
    
    
    
    
   
    
}
