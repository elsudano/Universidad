/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocolecciones;

import java.util.Collections;
import java.util.HashSet;

/**
  Características del HashSet:
 * - Sin elementos duplicados y no ordenados.  
 * - El orden es el de insersión de los elementos.
 */
public class EjemploHashSet {
    public static void main(String[] args){
        Rueda r1 = new Rueda(10,5);
        Rueda r2 = new Rueda(5,10);
        Rueda r3 = new Rueda(2,4);
        Rueda r4 = new Rueda(4,2);
        Rueda r5 = new Rueda(4,4);
        Rueda r6 = new Rueda(5,10);
        
        HashSet<Rueda> misRuedas = new HashSet(); 
        
        misRuedas.add(r2);
        misRuedas.add(r1);
        misRuedas.add(r3);
        misRuedas.add(r4);
        misRuedas.add(r5);
        misRuedas.add(r6);
        misRuedas.add(r1);
        
        /* Probar con el HashSet misRuedas los siguientes métodos:
         *  addAll, contains, remove, removeAll y toArray
         */      
        
        System.out.println("Uso del HashSet");
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());
            
        }
      
        
        /*Usar la funcionalidad de la clase Collections para los HashSet 
         * ¿Qué ocurre? 
         */ 
                
   }
}
