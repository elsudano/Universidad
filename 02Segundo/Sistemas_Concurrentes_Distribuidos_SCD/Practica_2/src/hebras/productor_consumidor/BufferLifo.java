package hebras.productor_consumidor;

import monitor.AbstractMonitor;
import monitor.Condition;

public class BufferLifo extends AbstractMonitor{
	private int numSlots = 0, cont = 0;   
	private double[] buffer = null;
	private Condition puede_producir = makeCondition();
	private Condition puede_consumir = makeCondition();
	
	public BufferLifo( int p_numSlots ){
		this.numSlots = p_numSlots ; 
		this.buffer = new double[numSlots] ;
	}
	
	public void depositar( double valor ) throws InterruptedException{
		enter();
		if (cont == numSlots)
			puede_producir.await();
		buffer[cont] = valor;
		cont++;
		puede_consumir.signal();
		leave();
	}
	
	public double extraer() throws InterruptedException{
		enter();
		double valor = 0;
		if (cont == 0)
			puede_consumir.await();
		cont--;
		valor = buffer[cont] ;
		puede_producir.signal();
		leave();
		return valor;
	}
}
