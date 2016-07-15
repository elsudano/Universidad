package hebras.fumadores;

public class Estanquero implements Runnable {
	private Estanco miEstanco;
	public Thread thr;
	
	public Estanquero(Estanco pEstanco) {
		this.miEstanco = pEstanco;
		this.thr = new Thread(this,"Estanquero");
	}
	
	public void run() {
		int ingrediente;
		while (true) {
			ingrediente = (int) (Math.random() * 3.0);
			miEstanco.ponerIngredientes(ingrediente);
			miEstanco.esperarRecogidaIngredientes();
		}
	}
}
