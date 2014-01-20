package ejercicio24T2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import ejercicio24T2.PlanEstudios;

public class GEA {
	public ArrayList<Alumno> aSinNombre_Alumno_;
	public ArrayList<PlanEstudios> aSinNombre_PlanEstudios_ = new ArrayList<PlanEstudios>();

	public void definirPe(String pCodigoPE, String pNombrePE, float pCreOptativo, float pCreTroncales) throws Exception {
		boolean existe = existePE(pCodigoPE);
		PlanEstudios pe;
		if (existe) throw new Exception ("Plan de estudio ya definido");
		else{
			pe = new PlanEstudios(pCodigoPE, pNombrePE, pCreOptativo, pCreTroncales);
			aSinNombre_PlanEstudios_.add(pe);
		}
	}

	private boolean existePE(String pCodigoPE) {
		return false;
	}

	public void incluirAsignatura(String pNombraA, String pCodigoA, float pCreditos, String pTipoAsig, String pCodigoPE) throws Exception {
		PlanEstudios pe = buscarPE(pCodigoPE);
		pe.incluirAsignatura(pNombraA, pCodigoA, pCreditos);
	}

	private PlanEstudios buscarPE(String pCodigoPE) {
		return null;
	}

	public void abrirExpediente(String pDniA, String pNombreA, String pCodigoPE, int pCursoAcademico) throws Exception {
		boolean existe = existeAlumno(pDniA);
		PlanEstudios planEstudios;
		Alumno alu;
		if (existe) throw new Exception("El alumno ya existe");
		else{
			planEstudios = buscarPE(pCodigoPE);
			alu = new Alumno(pDniA, pNombreA, pCursoAcademico, planEstudios);
			aSinNombre_Alumno_.add(alu);
		}
			
	}

	private boolean existeAlumno(String pDniA) {
		return false;
	}

	public void definirGrupos(String pCodigoPE, String pCodAsig, int pCursoAcademico, int pNumMaxAlumnos) {

	}

	public void matricular(String pDniA, String pCodAsig, String pNomGrupo, int pCursoAcademico) throws Exception {
		Alumno alu = buscarAlumno(pDniA);
		alu.matricular(pCodAsig, pNomGrupo, pCursoAcademico);
	}

	private Alumno buscarAlumno(String pDniA) {
		return null;
	}

	public void incluirNota(String pDniA, String pCodAsig, float pNota, String pConvocatoria, int pCursoAcademico) {

	}

	public Collection obtenerListaAlumnos(String pCodPE, String pCodAsig, int pCursoAcademico, String pNomGrupo) {
		return null;
	}

	public Collection obtenerExpediente(String pDniA) {
		return null;
	}

	public Collection obtenerMatricula(String pDniA, int pCursoAcademico) {
		return null;
	}

	public void solicitarTitulo(String pDniA, GregorianCalendar pFechaSolicitud) {

	}
}