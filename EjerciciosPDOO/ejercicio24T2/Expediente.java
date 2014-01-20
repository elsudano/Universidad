package ejercicio24T2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import ejercicio24T2.AsignaturaCursada;

public class Expediente {
	private int aCursoApertura;
	private GregorianCalendar aFechaSolicitud;
	private float aNotaMedia;
	private float aNCreOptAprobados;
	private float aNCreTroAprobados;
	public Alumno aSinNombre_Alumno_;
	public ArrayList<AsignaturaCursada> aSinNombre_AsignaturaCursada_ = new ArrayList<AsignaturaCursada>();

	Expediente(int pCursoAcademico) {
		
	}

	boolean convocatoriasAgotadas(String pCodAsig) {
		return false;
	}

	boolean estaAprobada(String pCodAsig) {
		return false;
	}

	boolean estaExpedienteCerrado() {
		return false;
	}

	boolean estaAsignatura(String pConAsig) {
		return false;
	}

	void incluirNota(float pNota, String pConvocatoria, int pCursoAcademico, Asignatura pSig) {
		
	}

	void modificarNota(float pNota, String pConvocatoria, int pCursoAcademico, Asignatura pSig) {

	}

	void modificarDatosExpediente(String pCodAsig, Nota pNota) {
		
	}

	boolean haTerminado(float pCreTronTotales, float pCreOptTotales) {
		return false;
	}

	Collection obtenerExpediente() {
		return null;
	}

	void solicitarTitulo(GregorianCalendar pFechaSolicitud) {

	}

	private AsignaturaCursada buscarAsigCursada(String pCodAsig) {
		return null;
	}
}