package seminario.seminario2_1;

public class Programa {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		if ( args.length < 1 ){
			System.out.println( "Error: falta valor de ’siesta’" );
			System.exit(1);
		}
		
		long siesta = Long.parseLong( args[0] ) ;
		TipoHebra obj = new TipoHebra("hebra ’obj’", siesta); // crear hebra
		obj.thr.start(); // lanzar hebra
		Thread.sleep( 100 ); // la hebra principal duerme 1/10 sec.
		obj.thr.join(); // esperar a que termine la hebra
	}
}
