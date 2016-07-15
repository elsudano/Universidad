import monitor.AbstractMonitor;
import monitor.Condition;

class BufferFifo extends AbstractMonitor{
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

class ProductorH1 extends AbstractMonitor implements Runnable{
	private Object bb = null;
	int veces; 
	int numP ;
	public Thread thr ;
	
	public ProductorH1( Object pbb, int pveces, int pnumP ){
		bb = pbb;
		veces = pveces;
		numP = pnumP ;
		thr = new Thread(this,"productor "+numP);
	}
	
	public void run(){
		try{
			double item = 100*numP ;
			for( int i=0 ; i<veces ; i++ ){
				System.out.println(thr.getName()+", produciendo " + item);
				if (bb.getClass().getName().contains("BufferFifo")){
					BufferFifo bb = (BufferFifo) this.bb;
					bb.depositar(item++);
				}else if (bb.getClass().getName().contains("BufferLifo")){
					BufferLifo bb = (BufferLifo) this.bb;
					bb.depositar(item++);
				}
			}
		}catch( Exception e ){
			System.err.println("Excepcion en run Productor: " + e);
		}
	}
}

class ConsumidorH1 extends AbstractMonitor implements Runnable{
	private Object bb;
	int veces; 
	int numC ;
	public Thread thr ;
	
	public ConsumidorH1( Object pbb, int pveces, int pnumC ){
		bb	  = pbb;
		veces = pveces;
		numC  = pnumC ;
		thr   = new Thread(this,"consumidor "+numC);
	}
	
	public void run(){
		try{
			double item = 0.0;
			for(int i=0 ; i<veces ; i++ ){
				if (bb.getClass().getName().contains("BufferFifo")){
					BufferFifo bb = (BufferFifo) this.bb;
					bb.extraer();
				}else if (bb.getClass().getName().contains("BufferLifo")){
					BufferLifo bb = (BufferLifo) this.bb;
					bb.extraer();
				}
				System.out.println(thr.getName()+", consumiendo "+item);
			}
		}catch( Exception e ){
			System.err.println("Excepcion en run Consumidor: " + e);
		}
	}
}

class pcfifo {
	public static void main(String[] args) {
		if ( args.length != 5 ){
			System.err.println("Uso: ncons nprod tambuf niterp niterc");
			return ;
		}
		// leer parametros, crear vectores y buffer intermedio
		ConsumidorH1[] cons1 = new ConsumidorH1[Integer.parseInt(args[0])] ; //lista de consumidores
		ProductorH1[]  prod1 = new ProductorH1[Integer.parseInt(args[1])] ; // lista de productores
		Object bufferFL = null;
		bufferFL = new BufferFifo(Integer.parseInt(args[2]));
		int iter_cons1 = Integer.parseInt(args[3]);
		int iter_prod1 = Integer.parseInt(args[4]);
		if ( cons1.length*iter_cons1 != prod1.length*iter_prod1 ){
			System.err.println("no coinciden número de items a producir con a cosumir");
		}    
		// crear hebras
		for(int i = 0; i < cons1.length; i++)
			cons1[i] = new ConsumidorH1(bufferFL,iter_cons1,i);
		for(int i = 0; i < prod1.length; i++)
			prod1[i] = new ProductorH1(bufferFL,iter_prod1,i);
		// poner en marcha las hebras
		for(int i = 0; i < prod1.length; i++)
			prod1[i].thr.start();
		for(int i = 0; i < cons1.length; i++)
			cons1[i].thr.start();
	}
}
