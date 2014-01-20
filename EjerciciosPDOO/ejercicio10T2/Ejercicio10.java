package ejercicio10T2;

public class Ejercicio10 {
	private static final Ejercicio10 mInstancia = new Ejercicio10();
	
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private Ejercicio10(){
		
	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		Sistema_Reservas miReserva = new Sistema_Reservas();
		miReserva.hacerReserva("Evento de la Fiesta");
		for (int c=0;c<miReserva.aEvento.size();c++){
			System.out.println("Nombre de la Sala: "+miReserva.aEvento.get(c).getaSala());
			System.out.println("Array de Reservas: "+miReserva.aEvento.get(c).getaReservas().toString());
		}
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio10 getInstancia() {
		return mInstancia;
	}
}
