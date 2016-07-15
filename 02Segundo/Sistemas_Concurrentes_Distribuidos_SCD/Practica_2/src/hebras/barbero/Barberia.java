package hebras.barbero;

import monitor.AbstractMonitor;
import monitor.Condition;

public class Barberia extends AbstractMonitor {
	private Condition SalaEspera;
	private Condition estadoBarbero;
	private final int numSillasEspera = 2;
	private int miTiempo;

	public Barberia() {
		this.SalaEspera = makeCondition();
		this.estadoBarbero = makeCondition();
	}

	// invocado por los clientes para cortarse el pelo
	public boolean cortarPelo(Cliente cli, int pTiempo) {
		boolean peluqueria_llena = false;
		enter();
		System.out.println("El Cliente: " + cli.getNumCli() + " entra a Cortarse el Pelo y se sienta en sala de espera\n");
		if (SalaEspera.count() < this.numSillasEspera) {
			this.miTiempo = pTiempo;
			if (estadoBarbero.count()>0){
				estadoBarbero.signal();
				System.out.println("El Barbero se Despierta\n");
			}
			SalaEspera.await();
		}else
			peluqueria_llena = true;
		leave();
		return peluqueria_llena;
	}

	// invocado por el barbero para esperar (si procede) a un nuevo cliente y
	// sentarlo para el corte
	public int siguienteCliente() {
		enter();
		if (SalaEspera.isEmpty()) {
			System.out.println("El Barbero esta durmiendo\n");
			estadoBarbero.await();
		} else
			System.out.println("El Barbero le dice al siguiente Cliente que esta esperando que se siente para pelarse\n");
		leave();
		return miTiempo;
	}

	// invocado por el barbero para indicar que ha terminado de cortar el pelo
	public void finCliente() {
		enter();
		System.out.println("El Barbero termina y le abre la puerta al Cliente\n");
		
		SalaEspera.signal();
		leave();
	}
}
