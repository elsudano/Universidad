package seminario.seminario2_1;

public class TipoHebra extends MiClase implements Runnable {

	long siesta ; // tiempo que duerme la hebra
	public Thread thr ; // objeto hebra encapsulado
	
	public TipoHebra( String nombre, long siesta ){
		this.siesta = siesta;
		thr = new Thread( this, nombre );
	}

	public void run(){
		try{
			while(true){
				System.out.println("Hola, soy"+thr.getName()+"\n Mi tiempo de espera es "+this.siesta/1000+" Segundos");
				if (siesta>0)
					Thread.sleep(siesta);
			}
		}catch ( InterruptedException e ){
			System.out.println( "me fastidiaron la siesta!" );
		}
	}
}
