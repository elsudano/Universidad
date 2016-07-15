package ejercicio19T3;

/**
 * @author Carlos de la Torre
 *
 */
public class Barco extends Transporte {

	public Barco() {
		
	}

	@Override
	public String hacerRuta(String origen, String destino) {
		return "Desde: " + origen + " Hasta: " + destino + " se tiene que poder\n"
				+ " llegar desde el agua para usar este Barco de la marca: ";
	}

	@Override
	public void llevarCosas() {
		System.out.println("Este trasnporte puede llevar cualquier cosas por el mar\n");
	}

	public void navegar(){
		System.out.println("Voy surcando los mares del Sur\n");
	}
}
