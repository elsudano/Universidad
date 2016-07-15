package ejercicioClase1T2;

public class EjercicioClase1 {
	private static final EjercicioClase1 mInstancia = new EjercicioClase1();
	
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private EjercicioClase1(){
		
	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static EjercicioClase1 getInstancia() {
		return mInstancia;
	}
}
