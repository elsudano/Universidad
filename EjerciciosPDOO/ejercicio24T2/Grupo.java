package ejercicio24T2;

import java.util.ArrayList;
import java.util.Collection;

import ejercicio24T2.Alumno;

public class Grupo {
	private String aNombre;
	private int aCursoAcademico;
	private int aNumMaxAlumno;
	private int aPlazasLibres;
	public Asignatura aSinNombre_Asignatura_;
	public ArrayList<Alumno> aLumnos = new ArrayList<Alumno>();

	public void Grupo(String pNomGrupo, int pCursoAcademico, Object pNumMaxAlumnos, Asignatura pSig) {

	}

	public void matricula(Alumno pLu) throws Exception {
		if (aPlazasLibres==0) throw new Exception("en este grupo no hay plazas libres");
		if (aPlazasLibres>0){
			aLumnos.add(pLu);
			pLu.incluirGrupo(this);
		}
	}

	public Collection obtenerListaAlumnos() {
		return null;
	}

	public Collection obtenerDatosGrupo() {
		return null;
	}
}