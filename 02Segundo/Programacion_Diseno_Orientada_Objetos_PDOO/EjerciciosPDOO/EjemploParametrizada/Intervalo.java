/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemploParametrizada;

import java.awt.Point;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ana
 */
 
class Intervalo<T> {
       T inicio, fin;
       public Intervalo (T ini, T fin) 
              { inicio=ini; fin=fin;}
       //... implementación de funcionalidad del intervalo
    
public static void main(String agrs[]){
     Intervalo<Point> segmento;
     Point a = new Point(1,2);
     Point b = new Point(7,8);
     segmento= new Intervalo(a,b);
     Intervalo<Time> reunion;
     Intervalo<Date> semanaSanta;   
}
 }