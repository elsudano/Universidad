/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocolecciones;

import java.util.Collections;
import java.util.TreeSet;

/**
 *  Características del HashSet:
 * - Sin elementos duplicados y 
 * - Ordenados según la relación de orden definida 
 *      en la clase de sus elementos (compareTo de Rueda). 
 */
 
public class EjemploTreeSet {
     public static void main(String[] args){
        Rueda r1 = new Rueda(10,5);
        Rueda r2 = new Rueda(5,10);
        Rueda r3 = new Rueda(2,4);
        Rueda r4 = new Rueda(4,2);
        Rueda r5 = new Rueda(4,4);
        Rueda r6 = new Rueda(5,10);
        TreeSet<Rueda> misRuedas = new TreeSet();       
        misRuedas.add(r2);
        misRuedas.add(r1); 
        misRuedas.add(r3);
        misRuedas.add(r4);
        misRuedas.add(r5);
        misRuedas.add(r6);
        misRuedas.add(r1);
        
        
        // Prueba toda la funcionalidad de TreeSet
          
        System.out.println("Uso del TreeSet");
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());
            
        }
         
        // Intenta ordenar el TreeSet
         
        System.out.println("Uso del TreeSet");
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());          
        }   
                
   }
}
