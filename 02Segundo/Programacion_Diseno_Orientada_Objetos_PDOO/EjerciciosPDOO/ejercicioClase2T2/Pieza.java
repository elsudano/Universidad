package ejercicioClase2T2;

public class Pieza {
	private String tipo;
	
	public Pieza(int num){
		if (num%2==0)
			this.tipo="A";
		else
			this.tipo="B";
	}

	public String verTipo() {
		return this.tipo;
	}
}
