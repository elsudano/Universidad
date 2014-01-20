package ejercicio24T2;

import java.util.ArrayList;
import java.util.Collection;

import ejercicio24T2.Grupo;

public class Asignatura {
	private String aNombreA;
	private String aCodigoA;
	private float aCreditosTotales;
	public PlanEstudios aSinNombre_PlanEstudios_;
	public AsignaturaCursada aSinNombre_AsignaturaCursada_;
	public ArrayList<Grupo> aSinNombre_Grupo_ = new ArrayList<Grupo>();

	Asignatura(String pNomAsig, String pCodAsig, float pCreditos) {

	}

	void definirGrupos(int pNumGrupos, int pCursoAcademico, int pNumMaxAlumnos) {
		
	}

	private boolean tieneDefinidosGrupos(int pCursoAcademico) {
		return false;
	}

	void matricular(String pNomGrupo, int pCursoAcademico, Alumno pLu) throws Exception {
		Grupo gru = buscarGrupo(pNomGrupo, pCursoAcademico);
		gru.matricula(pLu);
	}

	private Grupo buscarGrupo(String pNomGrupo, int pCursoAcademico) {
		return null;
	}

	Collection obtenerListaAlumno(int pCursoAcademico, String pNomGrupo) {
		return null;
	}

	String obtenerNombre() {
		return null;
	}

	float obtenerCreditos() {
		return aCreditosTotales;
	}
}