package ejercicio24T2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import ejercicio24T2.GEA;
import ejercicio24T2.Grupo;

public class Alumno {
	private String aDni;
	private String aNombre;
	public ArrayList<GEA> aSinNombre_GEA_ = new ArrayList<GEA>();
	public ArrayList<Grupo> aSinNombre_Grupo_ = new ArrayList<Grupo>();
	public PlanEstudios aSinNombre_PlanEstudios_;
	public Expediente aSinNombre_Expediente_;

	Alumno(String pDnisA, String pNombreA, int pCursoAcademico, PlanEstudios pPlanEstud) {
		Expediente exp = new Expediente(pCursoAcademico);
		this.aSinNombre_PlanEstudios_=pPlanEstud;
	}

	boolean matricular(String pCodAsig, String pNomGrupo, int pCursoAcademico) throws Exception {
		boolean cerrado = aSinNombre_Expediente_.estaExpedienteCerrado();
		if (cerrado) throw new Exception("Expediente Cerrado");
		else{
			boolean aprobada = aSinNombre_Expediente_.estaAprobada(pCodAsig);
			if (aprobada) throw new Exception("Asignatura ya aprobada");
			else{
				boolean agotadas = aSinNombre_Expediente_.convocatoriasAgotadas(pCodAsig);
				if (agotadas) throw new Exception("Convocatorias Agotadas");
				else{
					boolean matriculado = estaMatriculado(pCodAsig, pCursoAcademico);
					if (matriculado) throw new Exception("Ya matriculado en la Asignatura");
					else{
						return aSinNombre_PlanEstudios_.matricular(pCodAsig, pNomGrupo, pCursoAcademico, this);
					}
				}
			}
		}
		
	}

	private boolean estaMatriculado(String pCodAsig, int pCursoAcademico) {
		return true;
	}

	void incluirGrupo(Grupo pGru) {
		aSinNombre_Grupo_.add(pGru);
	}

	void incluirNota(String pCodAsig, float pNota, String pConvocatoria, int pCursoAcademico) {
		;
	}

	String[] obtenerDatos() {
		String[] datos = {"",""};
		return  datos;
	}

	Collection<GEA> obtenerExpediente() {
		return aSinNombre_GEA_;
	}

	Collection<GEA> obtenerMatricula(int pCursoAcademico) {
		return aSinNombre_GEA_;
	}

	void solicitarTitulo(GregorianCalendar pFechaSolicitud) {
		;
	}
}