package ejercicio19T3;

/**
 * @author Carlos de la Torre
 *
 */
public class Coche extends Transporte {

	public Coche() {
		
	}

	@Override
	public String hacerRuta(String origen, String destino) {
		return "Desde: " + origen + " Hasta: " + destino + " se tiene que poder\n"
				+ " llegar por carretera para usar este Coche de la marca: ";
	}

	@Override
	public void llevarCosas() {
		System.out.println("Este trasnporte puede llevar cualquier cosas por tierra\n");
	}

	public void correr(){
		System.out.println("Voy a toda leche por la carretera\n");
	}
}
