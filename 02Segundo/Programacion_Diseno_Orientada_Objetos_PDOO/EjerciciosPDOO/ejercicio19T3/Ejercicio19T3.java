package ejercicio19T3;

import java.util.ArrayList;

/**
 * @author Carlos de la Torre
 * 
 */
public class Ejercicio19T3 {
	private static final Ejercicio19T3 mInstancia = new Ejercicio19T3();

	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda hacer un new y se
	 * tiene que llamar usar getInstancia para poder usar el objeto en cuestión.
	 */
	private Ejercicio19T3() {

	}

	// METODOS
	/*
	 * Con este metódo en concreto lo que hacemos es que cuando lo llamamos
	 * ejecutamos el ejercicio en cuestión
	 */
	public void ejecutar() {
		Transporte c1 = new Coche();
		c1.llevarCosas();
		((Coche) c1).correr();

		Transporte b = new Barco();
		b.llevarCosas();
		((Barco) b).navegar();
		//b = c1;

		Transporte p = new Pesquero();
		((Barco) p).navegar();
		((Pesquero) p).pescar();
		p.llevarCosas();
		p = b;
		//b = p;
		((Barco) b).navegar();
		//((Pesquero) b).pescar();
		
		ArrayList v = new ArrayList();
		v.add(c1);
		v.add(p);
		((Barco) v.get(1)).navegar();
		((Transporte) v.get(1)).llevarCosas();
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio19T3 getInstancia() {
		return mInstancia;
	}
}
