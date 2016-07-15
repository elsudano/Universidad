/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocolecciones;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Características del ArrayList:
 * - Con elementos duplicados y no ordenados.
 * - Para ordenar hay que usar Collections.sort(miColección)
 *  
 */
public class EjemploArrayList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rueda r1 = new Rueda(10,5);
        Rueda r2 = new Rueda(5,10);
        Rueda r3 = new Rueda(2,4);
        Rueda r4 = new Rueda(4,2);
        Rueda r5 = new Rueda(4,4);
        Rueda r6 = new Rueda(5,10);
        
        // Pruebas con el ArrayList 
        ArrayList<Rueda> misRuedas = new ArrayList();
        misRuedas.add(r2);
        misRuedas.add(r1);
        misRuedas.add(r3);
        misRuedas.add(r4);
        misRuedas.add(r5);
        misRuedas.add(r6);
        misRuedas.add(r1);
        
        
        /*---------------------Ejercicios -------------------------------------
         * PRIMERO:
         * Probar en el ArrayList misRuedas los siguientes métodos: 
         *          addAll, clear, contains, get, indexOf, lastIndexOf, remove, removeAll, 
         *          set, toArray, trimToSize
         * usando siempre System.out.println() para mostrar qué pasa       
        */
     
        
        /* SEGUNDO:
         * Probar la funcionalidad de la clase Collections con los ArrayList
         * Los métodos que  proporciona la clase Collections son static, luego la 
         * forma de usarlos es anteponiendo el nombre de la clase al métotod que se quiere usar
         * por ejemplo si queremos copiar la lista l2 en la lista l1, sería : Collections.copy(l1,l2);
         * Probar los siguientes métodos:addAll,fill, frequency, indexOfSubList, nCopies, replaceAll, 
         *                                reverse, rotate, shuffle, swap, 
        */
        // System.out.println("numero de veces que aparece un objeto" + Collections.nCopies(3, r6)));
         
        
        System.out.println("Mis Ruedas");
        
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());
            
        }          
         
        // Ordenar las ruedas.
         Collections.sort(misRuedas);
         
        System.out.println("Uso del ArrayList ordenado");
        for (Rueda rueda : misRuedas) {
            System.out.println(rueda.toString());
            
        }
    
    }
}
