package ejercicio10T2;

import java.util.ArrayList;

import ejercicio10T2.Butaca;

public class Sala {
	private int aNumero;
	private String aNombre;
	private int aCapacidad;
	public ArrayList<Butaca> aButacas = new ArrayList<Butaca>();

	public int obtener(int pCapacidad) {
		return aNumero;
	}

	public Butaca siguienteButacaLibre(int pIndice) {
		return aButacas.get(pIndice);
	}

	/**
	 * @return el parametro aNombre
	 */
	public String getaNombre() {
		return aNombre;
	}

	/**
	 * @param aNombre se le asigna a aNombre
	 */
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}

	/**
	 * @return el parametro aCapacidad
	 */
	public int getaCapacidad() {
		return aCapacidad;
	}

	/**
	 * @param aCapacidad se le asigna a aCapacidad
	 */
	public void setaCapacidad(int aCapacidad) {
		this.aCapacidad = aCapacidad;
	}
}