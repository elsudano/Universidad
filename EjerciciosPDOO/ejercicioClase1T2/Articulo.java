package ejercicioClase1T2;

import java.util.ArrayList;

public class Articulo {
	private String pTitulo;
	private long pFechaPubli;
	private int pPagIni;
	private int pPagFin;
	private ArrayList<Autor> misAutores;
	private ArrayList<Documento> Relacionados;
	
	/**
	 * @param pTitulo
	 * @param pFechaPubli
	 * @param pPagIni
	 * @param pPagFin
	 */
	public Articulo(String pTitulo, long pFechaPubli, int pPagIni, int pPagFin) {
		this.pTitulo = pTitulo;
		this.pFechaPubli = pFechaPubli;
		this.pPagIni = pPagIni;
		this.pPagFin = pPagFin;
	}

	public void incluirAutores(ArrayList<Autor> autores) {
		this.misAutores.addAll(autores);
		
	}

	public void incluirDocuRelacionados(ArrayList<Documento> docuRelacionados) {
		this.Relacionados.addAll(docuRelacionados);
	}
	
	
}
