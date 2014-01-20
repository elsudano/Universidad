/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocolecciones;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Características del LinkedList:
 * - Con elementos duplicados y no ordenados.
 * - Para ordenar hay que usar Collections.sort(miColección)
 * - La deferencia del ArrayList su implementación, que es mediante una lista encadenada.
 * - Además de los métodos del ArrayList, también tiene aquellos que facilita la lista encadenada:
 *     
 */
public class EjemploLinkedList {
 public static void main(String[] args) {
        Rueda r1 = new Rueda(10,5);
        Rueda r2 = new Rueda(5,10);
        Rueda r3 = new Rueda(2,4);
        Rueda r4 = new Rueda(4,2);
        Rueda r5 = new Rueda(4,4);
        Rueda r6 = new Rueda(5,10);        
        
        LinkedList<Rueda> misRuedas = new LinkedList();
        misRuedas.add(r2);
        misRuedas.add(r1);
        misRuedas.add(r3);
        misRuedas.add(r4);
        misRuedas.add(r5);
        misRuedas.add(r6);
        misRuedas.add(r1);
       
       
        /* Probar toda la funcionalidad distinta de la clase LinkedList respecto
         * a la clase ArrayList y algunas de las funciones de la clase Collections 
         * con la clase LinkedList
         */
      
        
        System.out.println("Uso del ArrayList");
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());            
        }
        
        Collections.sort(misRuedas);
          
        System.out.println("Uso del ArrayList ordenado");
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());
            
        }
    } 
         
    
}
