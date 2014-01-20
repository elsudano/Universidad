package ejercicioClase1T2;

import java.util.ArrayList;

public class BibliotecaElectronica {
	private ArrayList<Autor> autores;
	private ArrayList<Documento> docuRelacionados;
	
	public BibliotecaElectronica(){
		this.autores = new ArrayList<Autor>();
		this.docuRelacionados = new ArrayList<Documento>();
	}
	
	public void incluirArticulo(String Titulo, long fechaPubli, int issn, int pagIni, int pagFin, int[] codigoAutores, int[] codDocuRelacionados) {
		Articulo articulo = new Articulo(Titulo, fechaPubli, pagIni, pagFin);
		this.autores = seleccionarAutores(codigoAutores);
		articulo.incluirAutores(autores);
		this.docuRelacionados = seleccionarDocumentos(codDocuRelacionados);
		articulo.incluirDocuRelacionados(docuRelacionados);
		Revista revista = new Revista();
		revista = buscarRevista(issn);
		revista.incluirArticulo(articulo);
	}

	private Revista buscarRevista(int issn) {
		
		return null;
	}

	private ArrayList<Documento> seleccionarDocumentos(int[] codDocuRelacionados) {

		return null;
	}

	private ArrayList<Autor> seleccionarAutores(int[] codigoAutores) {

		return null;
	}
}
