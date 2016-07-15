package ejercicioClase2T2;

public class EjercicioClase2 {
	private static final EjercicioClase2 mInstancia = new EjercicioClase2();
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private EjercicioClase2() {

	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		Repartidor repartidor = new Repartidor();
		repartidor.repartir();
		repartidor.mostrar();
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static EjercicioClase2 getInstancia() {
		return mInstancia;
	}
}
