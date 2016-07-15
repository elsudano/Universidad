import java.util.Random;
import java.util.Vector;
import monitor.AbstractMonitor;
import monitor.Condition;

class aux1{
	static Random genAlea = new Random() ;
	
	public static void dormir_max( int milisecsMax ){
		try{
			Thread.sleep( genAlea.nextInt(milisecsMax) );
		}catch( InterruptedException e ){
			System.err.println("sleep interumpido en 'aux.dormir_max()'");
		}
	}
}

class Cliente implements Runnable {
	public Thread thr;
	private Barberia barberia;
	
	public Cliente(Barberia bar){
		this.barberia = bar;
		this.thr = new Thread(this,"Cliente");
	}
	
	public void run() {
		while (true) {
			barberia.cortarPelo(); // el cliente espera (si procede) y se corta el pelo
			aux1.dormir_max(15000); // el cliente está fuera de la barberia un tiempo
		}
	}
}

class Barbero implements Runnable {
	public Thread thr;
	private Barberia barberia;
	
	public Barbero(Barberia bar){
		this.barberia = bar;
		this.thr = new Thread(this,"Barbero");
	}
	
	public void run() {
		while (true) {
			barberia.siguienteCliente();
			aux1.dormir_max(2500); // el barbero está cortando el pelo
			barberia.finCliente();
		}
	}
}

class Barberia extends AbstractMonitor{
	private Condition PEntrada;
	private Condition Sillas;
	private Condition estadoBarbero;
	
	public Barberia(){
		this.PEntrada = makeCondition();
		this.Sillas = makeCondition();
		this.estadoBarbero = makeCondition();
	}
	
	// invocado por los clientes para cortarse el pelo
	public void cortarPelo(){
		enter();
		if (PEntrada.count()>3)Sillas.await();
		estadoBarbero.signal();
		System.out.println("El Cliente entra a Cortarse el Pelo\n");
		leave();
	}
	// invocado por el barbero para esperar (si procede) a un nuevo cliente y sentarlo para el corte
	public void siguienteCliente(){
		enter();
		if (PEntrada.isEmpty()){
			estadoBarbero.await();
			System.out.println("El Barbero esta durmiendo\n");
		}else
			System.out.println("El Barbero le dice al siguiente Cliente que se siente en la silla\n");
		leave();
	}
	// invocado por el barbero para indicar que ha terminado de cortar el pelo
	public void finCliente (){
		enter();
		Sillas.signal();
		System.out.println("El Barbero termina y le abre la puerta al Cliente\n");
		leave();
	}
}

class barb {
	public static void main(String[] args) {
		int nClientes = 5;
		Barberia barberia = new Barberia();
		Barbero  barbero = new Barbero(barberia);
		barbero.thr.start();
		Vector<Cliente> clientes = new Vector<Cliente>();
		for (int w=0;w<nClientes;w++){
			Cliente cliente = new Cliente(barberia);
			cliente.thr.start();
			clientes.add(cliente);
		}
	}
}
