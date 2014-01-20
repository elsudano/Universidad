package ejercicio6T2;

import java.util.ArrayList;

import ejercicio6T2.Atleta;

public class Equipo {
	public ArrayList<Atleta> aAtletas = new ArrayList<Atleta>();
	
	ArrayList<Atleta> getAtletas(){
		return aAtletas;
	}
	
	public void anadirAtleta(Atleta pAtleta){
		this.aAtletas.add(pAtleta);
	}
}