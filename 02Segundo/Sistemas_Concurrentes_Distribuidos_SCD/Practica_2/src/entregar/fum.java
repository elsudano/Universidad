import java.util.Vector;
import monitor.AbstractMonitor;
import monitor.Condition;

enum Estado {
	Fumando,Esperando_su_ingrediente,Parado;
}

class Estanco extends AbstractMonitor {  
	private int ingredienteMostrador1;
	private int ingredienteMostrador2;
	private Condition poner_ingredientes = makeCondition();
	
	public Estanco(){
		this.ingredienteMostrador1 = -1;
		this.ingredienteMostrador2 = -1;
	}
	
	public boolean obtenerIngredientes(int pIngrediente) {
		enter();
		boolean valor = false;
		if (ingredienteMostrador1 != pIngrediente && ingredienteMostrador2 != pIngrediente){
			System.out.println("El fumador con el ingrediente: " + pIngrediente + 
					" recoge el ingrediente: " + ingredienteMostrador1 + " y el " + ingredienteMostrador2);
			poner_ingredientes.signal();
			valor  = true;
		}
		leave();
		return valor;
	}

	public void ponerIngredientes(int ingred1, int ingred2) {
		enter();
		this.ingredienteMostrador1 = ingred1;
		this.ingredienteMostrador2 = ingred2;
		System.out.println("El estanquero pone ingredientes: " + ingred1 + " y " + ingred2 +" encima del mostrador");
		leave();
	}

	public void esperarRecogidaIngredientes() {
		enter();
		poner_ingredientes.await();
		leave();
	}
}

class Fumador implements Runnable {
	private int miIngrediente;
	private int tiempo;
	private Estanco miEstanco;
	public Thread thr;
	public String miNombre;
	public Estado miEstado = null;
	
	public Fumador(int pIngrediente, Estanco pEstanco) {
		this.miEstanco = pEstanco;
		this.miIngrediente = pIngrediente;
		this.miNombre = "Fumador"+pIngrediente;
		this.miEstado = Estado.Parado;
		this.thr = new Thread(this,this.miNombre);
	}

	public void run() {
		while (true) {
			try {
				tiempo = (int) (Math.random() * 10000.0); // elegimos entre 0 y 9 seg. para fumar
				if (this.miEstanco.obtenerIngredientes(miIngrediente)){
					this.miEstado = Estado.Fumando;
					Thread.sleep(tiempo); // Esta fumando
				}else{
					this.miEstado = Estado.Esperando_su_ingrediente;
					Thread.sleep(1000); // esta camindo hacia el estanco para comprar 1 seg.
					this.miEstado = Estado.Parado;
				}				
			} catch (InterruptedException e) {
				System.out.println("Error en el fumador:" + e);
			}
		}
	}
}

class Estanquero implements Runnable {
	private Estanco miEstanco;
	public Thread thr;
	
	public Estanquero(Estanco pEstanco) {
		this.miEstanco = pEstanco;
		this.thr = new Thread(this,"Estanquero");
	}
	
	public void run() {
		int ingrediente1, ingrediente2;
		while (true) {
			do {
				ingrediente1 = (int) (Math.random() * 3.0);
				ingrediente2 = (int) (Math.random() * 3.0);
			} while (ingrediente1 == ingrediente2);
			miEstanco.ponerIngredientes(ingrediente1, ingrediente2);
			miEstanco.esperarRecogidaIngredientes();
		}
	}
}

class fum{
	public static void main(String[] args) {
		int nFumadores = 3;
		Estanco estanco = new Estanco();
		Estanquero  estanquero = new Estanquero(estanco);
		estanquero.thr.start();
		Vector<Fumador> fumadores = new Vector<Fumador>();
		for (int w=0;w<nFumadores;w++){
			Fumador fumador = new Fumador(w,estanco);
			fumador.thr.start();
			fumadores.add(fumador);
		}
		int count = 0;
		while (count < 20){
			for (Fumador fum : fumadores)
				System.out.println("El " + fum.miNombre + " esta " + fum.miEstado);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Error en Programa Principal: " + e);
			}
			count++;
		}
	}
}

