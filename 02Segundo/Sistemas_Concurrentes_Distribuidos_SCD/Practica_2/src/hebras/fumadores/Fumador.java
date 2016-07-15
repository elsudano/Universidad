package hebras.fumadores;

public class Fumador implements Runnable {
	private int miIngrediente;
	private int tiempo;
	private Estanco miEstanco;
	public Thread thr;
	public String miNombre;
	public Estado miEstado = null;
	
	public Fumador(int pIngrediente, Estanco pEstanco) {
		this.miEstanco = pEstanco;
		this.miIngrediente = pIngrediente;
		this.miNombre = "Fumador"+pIngrediente;
		this.miEstado = Estado.Parado;
		this.thr = new Thread(this,this.miNombre);
	}

	public void run() {
		while (true) {
			try {
				tiempo = (int) (Math.random() * 10000.0); // elegimos entre 0 y 9 seg. para fumar
				if (this.miEstanco.obtenerIngredientes(miIngrediente)){
					this.miEstado = Estado.Fumando;
					Thread.sleep(tiempo); // Esta fumando
				}else{
					this.miEstado = Estado.Esperando_su_ingrediente;
					Thread.sleep(1000); // esta camindo hacia el estanco para comprar 1 seg.
					this.miEstado = Estado.Parado;
				}				
			} catch (InterruptedException e) {
				System.out.println("Error en el fumador:" + e);
			}
		}
	}
}
