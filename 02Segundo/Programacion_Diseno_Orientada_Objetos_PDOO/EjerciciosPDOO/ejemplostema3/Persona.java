/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplostema3;
/**
 *
 * @author ana
 */
public class Persona {   
    protected static int numPersonas = 0;
    protected String dni;
    private String nombre;
    static int getNumPersonas(){return numPersonas;}
    public Persona(String d, String nom) {  
       this.setNombre(nom);
       this.setDni(d);
       numPersonas +=1;
    }
    protected String getNombre(){
        return this.nombre;
    }
    private String getDni(){
        return this.dni;
    }
    protected void setNombre(String nom) {
        this.nombre=nom; 
    }
    void setDni(String d){
        this.dni=d;
    }
    public void hablar(){
         System.out.println("bla bla bla");
    }    
}
