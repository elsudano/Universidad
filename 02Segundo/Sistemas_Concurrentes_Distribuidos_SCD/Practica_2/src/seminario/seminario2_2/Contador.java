package seminario.seminario2_2;

public class Contador{
	private long valor;
	
	public Contador(long inicial){
		valor = inicial;
	}
	
	private void retrasoOcupado(){ // ocupa la CPU durante cierto tiempo
		long tmp = 0 ;
		for(int i = 0;i<100000;i++)
			tmp = tmp*i-tmp+i;
	}
	
	public synchronized void incrementa (){ // incrementa valor en 1
		long aux = valor ; // hace copia local del valor actual
		retrasoOcupado() ; // permite entrelazado cuando no se hace en EM
		valor = aux+1 ; // escribe el valor compartido (incrementado)
	}
	
	public synchronized void decrementa (){ // decrementa valor en 1
		long aux = valor ; // hace copia local del valor actual
		retrasoOcupado() ; // permite entrelazado cuando no se hace en EM
		valor = aux-1 ; // escribe el valor compartido (incrementado)
	}
	
	public synchronized long getvalor (){ // devuelve el valor actual
		return valor;
	}
}