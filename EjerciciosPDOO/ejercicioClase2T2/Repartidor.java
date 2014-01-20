package ejercicioClase2T2;

import java.util.ArrayList;

public class Repartidor {
	private ArrayList<Pieza> ListaA;
	private ArrayList<Pieza> ListaB;
	private ArrayList<Pieza> listaPiezas;
	/**
	 * @param listaA
	 * @param listaB
	 * @param listaPiezas
	 */
	public Repartidor() {
		this.ListaA = new ArrayList<Pieza>();
		this.ListaB = new ArrayList<Pieza>();
		this.listaPiezas = new ArrayList<Pieza>();
		for (int c=0;c<30;c++){
			Pieza pieza = new Pieza(c);
			this.listaPiezas.add(pieza);
		}
	}
	
	public void repartir(){
		for (Pieza pieza : this.listaPiezas){//1
			String tipo = pieza.verTipo();//2 
			if (tipo.contentEquals("A")){
				this.ListaA.add(pieza); // 3
			}else if (tipo.contentEquals("B")){
				this.ListaB.add(pieza);//4
			}
			if (tipo.contentEquals("A") || tipo.contentEquals("B"))
				this.listaPiezas.remove(pieza);//5
		}
	}

	public void mostrar() {
		System.out.println("Las piezas que hay que repartir son:\n");
		for (Pieza pieza : listaPiezas)
			System.out.println("El Tipo de Pieza es: "+pieza.verTipo());
		System.out.println("Las Piezas que tiene ListaA son:\n");
		for (Pieza pieza : ListaA)
			System.out.println("El Tipo de Pieza es: "+pieza.verTipo());
		System.out.println("Las Piezas que tiene ListaB son:\n");
		for (Pieza pieza : ListaB)
			System.out.println("El Tipo de Pieza es: "+pieza.verTipo());
		
	}
}
