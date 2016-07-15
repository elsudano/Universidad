package PruebasVariasTema2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aljibe
 */
public class Persona {
    String nombre;
    int edad;
    
    Persona(String n, int e) {
        this.nombre=n;
        this.edad=e;
    }
    
    @Override
    public Persona clone(){
        return new Persona(this.nombre, this.edad);
    }
    public static void main(String argsString[]) {

        Persona p1 = new Persona("Juan", 30);
        Persona p2 = p1;
        Persona p3 = p1.clone();
        System.out.println(p1.nombre);
        p2.nombre = "Luis";
        p3.nombre = "Pepe";
        System.out.println(p1.nombre);
    
       
        
    }
    
}
