 
package ejemplocolecciones;

/**
 * La siguiente clase define el concepto de Rueda, entiéndela y a partir de ella
 * prueba los distintos tipos de colecciones de objetos que nos proporciona Java 
 *
 * En el siguiente enlace hay un resumen de las colecciones de Java:
 *   http://vayajava.blogspot.com.es/2008/05/diferencias-entre-las-colecciones-list.html
  * 
  * Una vez entendido el ejemplo, sigue el iguiente orden: 
  * 
  * 1. EjemploArrayList.java
  * 2. EjemploLinkedList.java
  * 3. EjemploHashSet.java
  * 4. EjemploTreeSet.java
  * 5. EjemploHashMap.java
  * 6. EjemploTreeMap.java
 */

public class Rueda implements Comparable {
    private double diametro;
    private double grosor;
    
    public Rueda(double d, double g){
        diametro = d;
        grosor = g;
    } 
    
    public double getDiametro(){
        return diametro;
    }
    public double getGrosor(){
        return grosor;
    }
    
    void setDiametro(double diametro) {
         this.diametro = diametro;  
    }
    void setGrosor(){
        this.grosor=grosor;
    }
    
    @Override
    public String toString(){
        
     return "diametro : " + Double.toString(diametro) + " grosor : " + Double.toString(grosor);
    
    } 
    @Override
    public boolean equals(Object o){
        Rueda r = (Rueda)o;
        return this == r;
    }     
    @Override
    public int compareTo(Object object){
      Rueda r = (Rueda)object;
      int resul;
       
       if (this.diametro < r.getDiametro()) {
            resul = 1;
       }else if(this.diametro == r.getDiametro()) {
                    if(this.grosor < r.getGrosor()) {
                             resul = 1;
                    }else if(this.grosor ==r.getGrosor()) {
                                 resul =0;
                          }else {
                                 resul= -1;
                          }
               }else {
                    resul= -1;
               }
     return resul;
    }

} 