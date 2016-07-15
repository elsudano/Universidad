package practica.lector_escritor;

public class Escritor implements Runnable{
	private MonitorLE monitorLE ; // objeto monitor l.e. compartido
	private int       nveces ; // numero de veces que lee
	public Thread     thr   ;  // objeto hebra encapsulado
	   
	public Escritor( MonitorLE p_monitorLE, int p_nveces, String nombre ){
		monitorLE  = p_monitorLE  ;    
		nveces     = p_nveces ;
		thr        = new Thread(this,nombre);
	}
	
	public void run(){
		for( int i = 0 ; i < nveces ; i++ ){
			System.out.println( thr.getName()+": solicita escritura.");
			monitorLE.inicio_escritura();
			System.out.println( thr.getName()+": escribiendo.");
			aux.dormir_max( 1000 );
			monitorLE.fin_escritura ();
		}
	}
}