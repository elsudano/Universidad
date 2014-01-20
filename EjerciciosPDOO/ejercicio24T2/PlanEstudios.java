package ejercicio24T2;

import java.util.ArrayList;
import java.util.Collection;

import ejercicio24T2.Asignatura;

public class PlanEstudios {
	private String aCodigoPE;
	private String aNombre;
	private float aCreditosOptativos;
	private float aCreditosTroncales;
	public GEA aSinNombre_GEA_;
	public Alumno aSinNombre_Alumno_;
	public ArrayList<Asignatura> aSinNombre_Asignatura_ = new ArrayList<Asignatura>();

	PlanEstudios(String pCodPE, String pNomPE, float creOptativos, float pCreTroncales) {

	}

	void incluirAsignatura(String pNomAsig, String pCodAsign, float pCred) throws Exception {
		boolean existe = existeAsignatura(pCodAsign);
		if (existe) throw new Exception("Asignatura ya definida en ese plan de estudios");
		else{
//			if(tipoAsig=="optativa") Asignatura_Troncal asig = new Asignatura_Troncal(pNomAsig,pCodAsign,pCred);
//			if(tipoAsig=="troncal") Asignatura_Optativa asig = new Asignatura_Optativa(pNomAsig,pCodAsign,pCred);
//			aSinNombre_Asignatura_.add(asig);
//			estas lineas estan comentadas por que en el diagrama de clases no he encontrado ninguna clase
//			que se llame Asignatura_Troncal o Asignatura_Optativa, aparte de eso no veo por ningun lado
//			donde demonios esta el ¿tipo de una asignatura? no hay!!
		}
	}

	private boolean existeAsignatura(String pCodAsig) {
		return false;
	}

	void definirGrupos(String pCodAsig, int pNumGrupos, int pCursoAcademico, int pNumMaxAlumnos) {

	}

	private Asignatura buscarAsignatura(String pCodAsig) {
		return null;
	}

	boolean matricular(String pCodAsig, String pNomGrupo, int pCursoAcademico, Alumno pLu) throws Exception {
		Asignatura asig = buscarAsignatura(pCodAsig);
		asig.matricular(pNomGrupo, pCursoAcademico, pLu);
		return true; // este return lo he puesto por que tal y como viene en el diagram de clases
					 // es imposible que esto funcione por que mirando el diagrama de clases y el 
					 // de comunicación no he conseguido ver como demonios esta funcion puede
					 // devolver un valor booleano.
	}

	Asignatura obtenerAsignatura(String pCodAsig) {
		return null;
	}

	Collection obtenerListaAlumnos(String pCodAsig, int pCursoAcademico, String pNomGrupo) {
		return null;
	}

	float obtenerCreOptTotales() {
		return aCreditosOptativos;
	}

	float obtenerCreTroTotales() {
		return aCreditosTroncales;
	}

	String obtenerNombre() {
		return null;
	}
}