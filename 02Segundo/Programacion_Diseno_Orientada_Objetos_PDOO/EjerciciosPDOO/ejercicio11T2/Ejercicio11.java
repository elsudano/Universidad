package ejercicio11T2;

public class Ejercicio11 {
	private static final Ejercicio11 mInstancia = new Ejercicio11();
	
	private Ejercicio11(){
		;
	}
	public void ejecutar() {
		System.out.println("Este Ejercicio no esta completo como para poder ejecutarlo");
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio11 getInstancia() {
		return mInstancia;
	}
}
