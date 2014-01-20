/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocolecciones;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *  Características del TreeMap: * 
 * - Sin elementos duplicados en la key.
 * - No ordenados por la Key, según la relación de orden definida en clase a la 
 *   que pertece la Key (compareTo de Rueda).  
 */
public class EjemploTreeMap {
  public static void main(String[] arg){
      /*  Construimos un TreeMap con la Key a la primera rueda de una lista de ruedas 
     *  ordenadas de mayor a menor y el Value a esa lista
    */
     Rueda r1 = new Rueda(10,5);
     Rueda r2 = new Rueda(5,10);
     Rueda r3 = new Rueda(2,4);
     Rueda r4 = new Rueda(4,2);
     Rueda r5 = new Rueda(4,4);
     Rueda r6 = new Rueda(5,10);
     TreeMap<Rueda, LinkedList<Rueda>> misRuedas = new TreeMap();
     LinkedList<Rueda> listaRuedas;
     listaRuedas = new LinkedList();
     listaRuedas.add(r1);
     listaRuedas.add(r2);
     listaRuedas.add(r4);
     Collections.sort(listaRuedas);
     misRuedas.put(listaRuedas.getFirst(),listaRuedas);
      
     listaRuedas = new LinkedList();
     listaRuedas.add(r3);
     listaRuedas.add(r4);
     listaRuedas.add(r6);
     Collections.sort(listaRuedas);
     misRuedas.put(listaRuedas.getFirst(),listaRuedas);
     
     listaRuedas = new LinkedList();
     listaRuedas.add(r4);
     listaRuedas.add(r5);
     listaRuedas.add(r2);
     Collections.sort(listaRuedas);
     misRuedas.put(listaRuedas.getFirst(),listaRuedas);
     
     for (Entry<Rueda,LinkedList<Rueda>> unaRueda:misRuedas.entrySet()){
         System.out.println("La rueda : "+ unaRueda.getKey().toString() + "  Es la primera de la lista:" );
         for(Rueda otraRueda: unaRueda.getValue())
             System.out.println(otraRueda.toString());
     }     
     
     // Prueba los siguientes métodos: containsKey,containsValue, firsKey, keySet, lastKey
     // subMap, remove, 
  }
}
