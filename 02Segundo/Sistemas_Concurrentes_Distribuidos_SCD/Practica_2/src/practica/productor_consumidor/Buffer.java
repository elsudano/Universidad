package practica.productor_consumidor;

public class Buffer{
	private int numSlots = 0, cont = 0;   
	private double[] buffer = null;
	
	public Buffer( int p_numSlots ){
		numSlots = p_numSlots ; 
		buffer = new double[numSlots] ;
	}
	
	public synchronized void depositar( double valor ) throws InterruptedException{
		while( cont == numSlots ) wait();              
		buffer[cont] = valor; cont++;      
		notifyAll();  
	}
	
	public synchronized double extraer() throws InterruptedException{
		double valor;
		while( cont == 0 ) wait() ;
		cont--; valor = buffer[cont] ;
		notifyAll();
		return valor;
	}
}
