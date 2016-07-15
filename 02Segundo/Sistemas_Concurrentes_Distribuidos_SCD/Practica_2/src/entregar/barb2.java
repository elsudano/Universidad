import java.util.Random;
import java.util.Vector;
import monitor.AbstractMonitor;
import monitor.Condition;

class aux3{
	static Random genAlea = new Random() ;
	
	public static void dormir_max( int milisecsMax ){
		try{
			Thread.sleep( genAlea.nextInt(milisecsMax) );
		}catch( InterruptedException e ){
			System.err.println("sleep interumpido en 'aux.dormir_max()'");
		}
	}
}

class Cliente2 implements Runnable {
	public Thread thr;
	private Barberia2 barberia;
	private Random genAlea = new Random();
	
	public Cliente2(Barberia2 bar){
		this.barberia = bar;
		this.thr = new Thread(this,"Cliente");
	}
	
	public void run() {
		while (true) {
			aux3.dormir_max(500); // el cliente está fuera de la barberia un tiempo
			barberia.cortarPelo(genAlea.nextInt(5000)); // el cliente espera (si procede) y se corta el pelo
		}
	}
}

class Barbero2 implements Runnable {
	public Thread thr;
	private Barberia2 barberia;
	
	public Barbero2(Barberia2 bar){
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

class Barberia2 extends AbstractMonitor{
	private Condition PEntrada;
	private Condition Sillas;
	private Condition estadoBarbero;
	private int miTiempo;
	
	public Barberia2(){
		this.PEntrada = makeCondition();
		this.Sillas = makeCondition();
		this.estadoBarbero = makeCondition();
	}
	
	// invocado por los clientes para cortarse el pelo
	public void cortarPelo(int pTiempo){
		enter();
		this.miTiempo=pTiempo;
		if (PEntrada.count()>3){
			Sillas.await();
		}
		System.out.println("El Cliente entra a Cortarse el Pelo y se sienta en una silla\n");
		try {
			Thread.sleep(miTiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		estadoBarbero.signal();
		leave();
	}
	// invocado por el barbero para esperar (si procede) a un nuevo cliente y sentarlo para el corte
	public int siguienteCliente(){
		enter();
		if (PEntrada.isEmpty()){
			System.out.println("El Barbero esta durmiendo\n");
			estadoBarbero.await();
		}else
			System.out.println("El Barbero le dice al siguiente Cliente que esta esperando que se siente para pelarse\n");
		leave();
		return miTiempo;
	}
	// invocado por el barbero para indicar que ha terminado de cortar el pelo
	public void finCliente (){
		enter();
		if (PEntrada.count()==0)Sillas.signal();
		System.out.println("El Barbero termina y le abre la puerta al Cliente\n");
		leave();
	}
}

class barb2 {
	public static void main(String[] args) {
		int nClientes = 5;
		Barberia2 barberia2 = new Barberia2();
		Barbero2  barbero2 = new Barbero2(barberia2);
		barbero2.thr.start();
		Vector<Cliente2> clientes2 = new Vector<Cliente2>();
		for (int w=0;w<nClientes;w++){
			Cliente2 cliente = new Cliente2(barberia2);
			cliente.thr.start();
			clientes2.add(cliente);
		}
	}
}
