package seminario.seminario2_2;

public class HebraContadora implements Runnable{
	private int numero ; // cuenta múltiplos de este número
	public Thread thr ; // objeto encapsulado
	private Contador cont; // contador de número de múltiplos

	public HebraContadora( String nombre, int p_numero, Contador p_contador ){
		this.numero = p_numero;
		this.cont = p_contador;
		this.thr = new Thread( this, nombre );
	}
	
	public void run (){
		for ( int i = 1 ; i <= 1000 ; i++ ){
			if (i % numero == 0)
				cont.incrementa();
			if (i % numero == 1)
				cont.decrementa();
		}
	}
}