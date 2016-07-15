package ejercicio3T3;

/**
 * @author Carlos de la Torre
 *
 */
public class Coche extends Transporte {

	/**
	 * 
	 */
	public Coche() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String hacerRuta(String origen, String destino) {
		return "Desde: " + origen + " Hasta: " + destino + " se tiene que poder\n"
				+ " llegar por carretera para usar este Coche de la marca: ";
	}

}
