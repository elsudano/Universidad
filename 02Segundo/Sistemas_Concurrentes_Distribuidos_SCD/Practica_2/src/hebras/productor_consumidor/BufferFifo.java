package hebras.productor_consumidor;

import monitor.AbstractMonitor;
import monitor.Condition;

public class BufferFifo extends AbstractMonitor{
	private int numSlots = 0, libre = 0, ocupada = 0;
	private double[] buffer = null;
	private Condition puede_producir = makeCondition();
	private Condition puede_consumir = makeCondition();
	
	public BufferFifo( int p_numSlots ){
		this.numSlots = p_numSlots ; 
		this.buffer = new double[numSlots] ;
	}
	
	public void depositar( double valor ) throws InterruptedException{
		enter();
		buffer[libre] = valor;
		if (libre==ocupada)
			puede_producir.await();
		if (libre<numSlots-1)
			libre++;
		else
			libre=0;
		puede_consumir.signal();
		leave();
	}
	
	public double extraer() throws InterruptedException{
		enter();
		double valor = 0;
		if (ocupada<numSlots-1)
			ocupada++;
		else
			ocupada=0;
		valor = buffer[ocupada] ;
		puede_producir.signal();
		leave();
		return valor;
	}
}
