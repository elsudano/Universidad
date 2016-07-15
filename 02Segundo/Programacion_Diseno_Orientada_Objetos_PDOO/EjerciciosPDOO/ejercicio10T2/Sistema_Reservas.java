package ejercicio10T2;

import java.util.ArrayList;

import ejercicio10T2.Evento;

public class Sistema_Reservas {
	public ArrayList<Evento> aEvento = new ArrayList<Evento>();

	public void hacerReserva(String pNombreEvento) {
		Evento aux = buscarEvento(pNombreEvento);
		aEvento.add(aux);
	}

	private Evento buscarEvento(String pNombreEvento) {
		Evento elEvento = new Evento();
		elEvento.hacerReserva();
		for (Evento miEvento : aEvento)
			if (miEvento.getaNombre().contentEquals(pNombreEvento)){
				elEvento=miEvento;
				break;
			}
		return elEvento;
	}
}