package practica.lector_escritor;

public class Lector implements Runnable{
	private MonitorLE monitorLE ; // objeto monitor l.e. compartido
	private int       nveces ; // numero de veces que lee
	public Thread     thr   ; // objeto hebra encapsulado
  
	public Lector( MonitorLE p_monitorLE, int p_nveces, String nombre ){
		monitorLE  = p_monitorLE  ;    
		nveces     = p_nveces ;
		thr        = new Thread(this,nombre);
	}
  
	public void run(){
		for( int i = 0 ; i < nveces ; i++ ){
			System.out.println( thr.getName()+": solicita lectura.");
			monitorLE.inicio_lectura();
			System.out.println( thr.getName()+": leyendo.");
			aux.dormir_max( 1000 ) ;
			monitorLE.fin_lectura();
		}
	}
}
