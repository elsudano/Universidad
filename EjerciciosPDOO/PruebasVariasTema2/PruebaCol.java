/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasVariasTema2;

import java.util.ArrayList;

/**
 * Probando a añadir objetos y valores de tipos básicos dentro de colecciones y arrays
 * @author aljibe
 */
public class PruebaCol {
    public static void main (String args[]){
        
       //colección no parametrizada, añadiendo valores de tipos básicos y objetos
        ArrayList numeros=new ArrayList();
        numeros.add(7);
        System.out.println(numeros.get(0));
        numeros.add("hola");
        System.out.println(numeros.get(1));
        numeros.add(new Persona("Ana", 19));
        System.out.println(((Persona)numeros.get(2)).nombre); //arreglarlo
        
       //Vector, probando a introducir valores y objetos
        
        int cosas[]={44, 88};
        cosas[0]=22;
        System.out.println(cosas[0]);
        
        Persona p1= new Persona("p1", 19);
        Persona p2= new Persona("p2", 20);
        Persona p3= new Persona("p3", 21);
        Persona personas[]={p1, p2};
        personas[0]=p3;
        System.out.println(((Persona)personas[0]).nombre);
    }
    
}
