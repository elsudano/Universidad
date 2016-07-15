package ejercicio3T3;

/**
 * @author Carlos de la Torre
 *
 */
public class Ejercicio3T3 {
	private static final Ejercicio3T3 mInstancia = new Ejercicio3T3();
	
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private Ejercicio3T3(){
		
	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		// Creamos un trasporte cualquiera y le asignamos un tipo de Transporte
		Transporte miTransporte = new Barco();
		// ahora le ponemos la marca de la que es nuestro barco
		miTransporte.setMarca("SirenaPlateada");
		// Ahora hacemos la ruta que podamos con el Transporte
		System.out.println(miTransporte.hacerRuta("Lisboa", "Cadiz")+miTransporte.getMarca());
		// una vez que hemos llegado a Cádiz cambiamos al coche
		miTransporte = new Coche();
		// Elegimos la marca de nuestro coche
		miTransporte.setMarca("Mercedez Benz");
		// y ahora volvemos a casa
		System.out.println(miTransporte.hacerRuta("Cádiz", "Granada")+miTransporte.getMarca());
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio3T3 getInstancia() {
		return mInstancia;
	}
}
