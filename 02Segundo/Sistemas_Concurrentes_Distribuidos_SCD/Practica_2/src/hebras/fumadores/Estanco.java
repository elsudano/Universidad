package hebras.fumadores;

import monitor.AbstractMonitor;
import monitor.Condition;

public class Estanco extends AbstractMonitor {  
	private int ingredienteMostrador;
	private Condition poner_ingredientes = makeCondition();
	
	public Estanco(){
		this.ingredienteMostrador = -1;
	}
	
	public boolean obtenerIngredientes(int pIngrediente) {
		enter();
		boolean valor = false;
		if (ingredienteMostrador == pIngrediente){
			System.out.println("El fumador con el ingrediente: " + pIngrediente + 
					" recoge el ingrediente: " + ingredienteMostrador);
			poner_ingredientes.signal();
			valor  = true;
		}
		leave();
		return valor;
	}

	public void ponerIngredientes(int ingred) {
		enter();
		this.ingredienteMostrador = ingred;
		System.out.println("El estanquero pone el ingrediente: " + ingred + " encima del mostrador");
		leave();
	}

	public void esperarRecogidaIngredientes() {
		enter();
		poner_ingredientes.await();
		leave();
	}
}
