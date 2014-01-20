package PruebaVisibilidad.UnPaquete;

public class PruebaBicicleta {
	
	private static final PruebaBicicleta mInstancia = new PruebaBicicleta();
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private PruebaBicicleta() {

	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar(){
		//Construcción de objetos de la clase Bicicletas.
		Bicicleta bici= new Bicicleta();
		System.out.println("color de la bicicleta = " + bici.getColor());
		//System.out.println("Numero de bicicletas construidas = " + Bicicleta.NumeroBicicletas);
		
		Bicicleta miBici = new Bicicleta(5,"rojo");
		System.out.println("color de la bicicleta = " + miBici.getColor());
		//System.out.println("Numero de bicicletas construidas = " + Bicicleta.NumeroBicicletas);
		
		// ¿Qué ocurre si les quito los comentarios a estas dos líneas de código?
		//bici.marchas=4;           
		//System.out.println(bici.marchas);
		
		// ¿Qué ocurre si les quito los comentarios a estas dos líneas de código?
		bici.color="rojo";   
		System.out.println(bici.color);  
		
		// ¿Qué ocurre si le quito los comentarios?
		//miBici.incrementarKilometros(12.5);
		System.out.println("la bicicleta ha recorrido " + miBici.getKilometros() + "Km");
	}
	
	public void ejecutar2(){
		// Acceso a los atributos de ámbito de clase a través de la clase;
		// System.out.println("Numero de bicicletas construidas = " +
		// Bicicleta.NumeroBicicletas);
		// System.out.println("Numero de ruedas de las bicicletas = " +
		// Bicicleta.NumeroRuedas);
		
		// Construcción de objetos de la clase Bicicletas.
		// Bicicleta bici= new Bicicleta();
		// System.out.println("color de la bicicleta = " + bici.getColor());
		// System.out.println("kilometros recorridos por la bici = " +
		// bici.getKilometros());
		
		// Bicicleta miBici = new Bicicleta(5,"rojo");
		// System.out.println("color de la bicicleta = " + miBici.getColor());
		// System.out.println("Numero de bicicletas construidas = " +
		// miBici.getNumeroBicicletas());
		
		// Acceso a los atributos de ámbito de clase a través del objeto
		// ¿Qué ocurre si les quito los comentarios a estas dos líneas de
		// código?
		// bici.incrementarNumeroBicicletas();
		// System.out.println("Numero de bicicletas construidas = " +
		// bici.getNumeroRuedas());
		
		// ¿Qué ocurre si les quito los comentarios a estas dos líneas de
		// código?
		// bici.marchas=4;
		// System.out.println(bici.marchas);
		
		// ¿Qué ocurre si les quito los comentarios a estas dos líneas de
		// código?
		// bici.color="rojo"; //¿Qué problema ves aquí?
		// System.out.println(bici.color);
		
		// ¿Qué ocurre si le quito los comentarios?
		// miBici.incrementarKilometros(12.5);
		// System.out.println("la bicicleta ha recorrido " +
		// miBici.getKilometros() + "Km");
	}

	public void LinkedListTest(){

	}
	
    public void MapTest(){

	}
	
    public void ShuffleTest(){

	}
	
    public void TreeSetTest(){

	}
	
	/**
	 * @return el parametro mInstancia
	 */
	public static PruebaBicicleta getInstancia() {
		return mInstancia;
	}
}
