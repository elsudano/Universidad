package ejercicio10T2;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ejercicio10T2.Reserva;

public class Evento {
	private String aNombre;
	private GregorianCalendar aDia;
	private GregorianCalendar aHora;
	public Sala aSala;
	public ArrayList<Reserva> aReservas = new ArrayList<Reserva>();

	public void hacerReserva() {
		Butaca miButaca = new Butaca();
		aSala = new Sala();
		aSala.setaNombre("Una Sala");
		Reserva miReserva = new Reserva(miButaca);
		aReservas.add(miReserva);
	}

	/**
	 * @return el parametro aNombre
	 */
	public String getaNombre() {
		return aNombre;
	}

	/**
	 * @return el parametro aDia
	 */
	public GregorianCalendar getaDia() {
		return aDia;
	}

	/**
	 * @return el parametro aHora
	 */
	public GregorianCalendar getaHora() {
		return aHora;
	}

	/**
	 * @return el parametro aSala
	 */
	public Sala getaSala() {
		return aSala;
	}

	/**
	 * @return el parametro aReservas
	 */
	public ArrayList<Reserva> getaReservas() {
		return aReservas;
	}
}