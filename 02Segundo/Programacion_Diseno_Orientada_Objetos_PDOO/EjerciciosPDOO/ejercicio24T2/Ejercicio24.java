package ejercicio24T2;

public class Ejercicio24 {
	private static final Ejercicio24 mInstancia = new Ejercicio24();
	
	private Ejercicio24(){
		;
	}
	
	public void ejecutar() {
		System.out.println("Este Ejercicio no esta completo como para poder ejecutarlo");
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio24 getInstancia() {
		return mInstancia;
	}
}
