package ejercicio8T3;

import java.util.ArrayList;

public class Grupo<T> {
	ArrayList<T> conjunto = new ArrayList<T>();
	T lider;

	public void setMiembro(T miembro) {
		this.conjunto.add(miembro);
	}

	public void setLider(T elLider) {
		this.lider = elLider;
	}

	public T getLider() {
		return this.lider;
	}

	public ArrayList<T> getMiembros() {
		return this.conjunto;
	}
}
