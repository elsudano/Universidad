package PruebasVariasTema2;


import PruebasVariasTema2.Prueba;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aljibe
 * Probando ejercicio 2 de relación 1.
 * Ver cuál es el motivo de cada error y cómo solucionarlo
 */
public class Prueba2 {
    
    public static void main(String args[]) {
        
        Prueba obj1=new Prueba();
        Prueba obj2=new Prueba();
        //obj1.A=3;  
        System.out.println(obj1.A);
        //Prueba.A=3;
        Prueba.s="hola";
        obj1.s="hola";
        //obj1.b=0;
        
        
    }
    
}
