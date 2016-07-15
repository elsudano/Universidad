package hebras.barbero;

import java.util.Random;

public class Cliente implements Runnable {
	public Thread thr;
	private Barberia barberia;
	private int numCli;
	private Random genAlea = new Random();
	
	public Cliente(Barberia bar, int num){
		this.barberia = bar;
		this.thr = new Thread(this,"Cliente");
		this.numCli = num;
	}
	
	public void run() {
		while (true) {
			if (barberia.cortarPelo(this,genAlea.nextInt(5000))){
				aux.dormir_max(15000); // el cliente está fuera de la barberia un tiempo por que esta llena
			}; // el cliente pasa (si procede) y se corta el pelo
		}
	}

	/**
	 * @return el parametro numCli
	 */
	public int getNumCli() {
		return numCli;
	}
	
	
}

