package ejemplostema3.interfaces;

public class Interfaces {
	private static final Interfaces mInstancia = new Interfaces();
	private Rectangulo miRectangulo;
	
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private Interfaces(){
		miRectangulo = new Rectangulo(5, 6);
	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		System.out.println("Cuantos lados tengo: " + miRectangulo.getNumLados());
		System.out.println("Lado A: " + miRectangulo.ladoa);
		System.out.println("Lado B: " + miRectangulo.ladob);
		System.out.println("Y mi Area es: " + miRectangulo.area());
		System.out.println("Y mi Perimetro es: " + miRectangulo.perimetro());
		System.out.println("El grosor del perimetro es: " + FiguraGrafica.grosorBorde);
		miRectangulo.pintarBorde("Rojo");
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Interfaces getInstancia() {
		return mInstancia;
	}
}
