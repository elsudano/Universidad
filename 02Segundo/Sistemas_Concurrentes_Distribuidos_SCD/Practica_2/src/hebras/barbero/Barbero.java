package hebras.barbero;

public class Barbero implements Runnable {
	public Thread thr;
	private Barberia barberia;
	
	public Barbero(Barberia bar){
		this.barberia = bar;
		this.thr = new Thread(this,"Barbero");
	}
	
	public void run() {
		while (true) {
			int cuantoTardare = barberia.siguienteCliente();
			System.out.println("Tardare es Cortar el pelo:" + cuantoTardare + "\n");
			try {
				Thread.sleep(cuantoTardare);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // el barbero está cortando el pelo
			barberia.finCliente();
		}
	}
}
