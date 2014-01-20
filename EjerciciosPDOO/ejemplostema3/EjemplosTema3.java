/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplostema3;

/**
 *
 * @author Carlos de la Torre
 */
public class EjemplosTema3 {
	private static final EjemplosTema3 mInstancia = new EjemplosTema3();
	
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private EjemplosTema3(){
		
	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
        Persona p = new Persona("p","p");        
        Profesor pro = new Profesor("pro","pro","asig1",5);
        p.hablar(); // bla bla bla        
        pro.hablar(); //Estimados
                      // bla bla bla
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static EjemplosTema3 getInstancia() {
		return mInstancia;
	}
}
