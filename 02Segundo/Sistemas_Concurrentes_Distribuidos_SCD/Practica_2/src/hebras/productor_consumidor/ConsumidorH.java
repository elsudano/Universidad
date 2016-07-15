package hebras.productor_consumidor;

import monitor.AbstractMonitor;

public class ConsumidorH extends AbstractMonitor implements Runnable{
	private Object bb;
	int veces; 
	int numC ;
	public Thread thr ;
	
	public ConsumidorH( Object pbb, int pveces, int pnumC ){
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
