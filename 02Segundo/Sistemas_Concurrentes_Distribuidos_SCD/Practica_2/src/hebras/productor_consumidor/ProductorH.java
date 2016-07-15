package hebras.productor_consumidor;

import monitor.AbstractMonitor;

public class ProductorH extends AbstractMonitor implements Runnable{
	private Object bb = null;
	int veces; 
	int numP ;
	public Thread thr ;
	
	public ProductorH( Object pbb, int pveces, int pnumP ){
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
