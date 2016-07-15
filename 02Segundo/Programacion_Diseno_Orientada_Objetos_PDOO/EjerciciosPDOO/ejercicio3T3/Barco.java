package ejercicio3T3;

/**
 * @author Carlos de la Torre
 *
 */
public class Barco extends Transporte {

	/**
	 * 
	 */
	public Barco() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String hacerRuta(String origen, String destino) {
		return "Desde: " + origen + " Hasta: " + destino + " se tiene que poder\n"
				+ " llegar desde el agua para usar este Barco de la marca: ";
	}

}
