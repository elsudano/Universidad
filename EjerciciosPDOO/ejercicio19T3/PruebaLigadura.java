package ejercicio19T3;

import java.util.ArrayList;

public class PruebaLigadura {
	public static void main(String args[]) {
		Transporte c1 = new Coche();
		c1.llevarCosas();
		((Coche) c1).correr();

		Transporte b = new Barco();
		b.llevarCosas();
		((Barco) b).navegar();
		//b = c1;

		Transporte p = new Pesquero();
		((Barco) p).navegar();
		((Pesquero) p).pescar();
		p.llevarCosas();
		p = b;
		//b = p;
		((Barco) b).navegar();
		//((Pesquero) b).pescar();
		
		ArrayList v = new ArrayList();
		v.add(c1);
		v.add(p);
		((Barco) v.get(1)).navegar();
		((Transporte) v.get(1)).llevarCosas();
	}
}


