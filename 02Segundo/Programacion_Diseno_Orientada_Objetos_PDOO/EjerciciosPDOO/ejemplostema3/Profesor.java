/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplostema3;

/**
 *
 * @author ana
 */
public class Profesor extends Persona{    
  String asignatura;
  int experiencia;
  public Profesor (String d, String nom,String asig,int exp){
        super(d,nom); 
        asignatura=asig;
        experiencia=exp;
  }
  // Nueva funcionalidad de Profesor
  public void darClase(){
    System.out.println("dando clase"); 
  }
  
  @Override
  public void hablar(){
        System.out.println( "Estimados");
        super.getNombre();
  }
} 

