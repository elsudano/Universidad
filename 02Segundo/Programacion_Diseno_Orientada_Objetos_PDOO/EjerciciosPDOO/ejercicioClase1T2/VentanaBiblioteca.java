package ejercicioClase1T2;

import java.util.GregorianCalendar;

public class VentanaBiblioteca {
	private String Titulo;
	private long fechaPubli;
	private int issn;
	private int pagIni;
	private int pagFin;
	private int[] codigoAutores = {4,6,8};
	private int[] codDocuRelacionados = {5,7,9};
	BibliotecaElectronica be;
	
	/**
	 * @param titulo
	 * @param fechaPubli
	 * @param issn
	 * @param pagInt
	 * @param pagFin
	 * @param codigoAutores
	 * @param codDocuRelacionados
	 * @param be
	 */
	public VentanaBiblioteca(String titulo, long fechaPubli, int issn, int pagInt, int pagFin, int[] codigoAutores, int[] codDocuRelacionados, BibliotecaElectronica be) {
		this.Titulo = "MiTitulo";
		this.fechaPubli = new GregorianCalendar().getTime().getTime();
		this.issn = 2332422;
		this.pagIni = 2;
		this.pagFin = 234;
		this.codigoAutores = codigoAutores;
		this.codDocuRelacionados = codDocuRelacionados;
		this.be = new BibliotecaElectronica();
		be.incluirArticulo(Titulo, fechaPubli, issn, pagIni, pagFin, codigoAutores, codDocuRelacionados);
	}
	
	
}
