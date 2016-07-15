package ejercicio11T2;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ejercicio11T2.Membership;

public class Customer {
	private String aName;
	private String aTitle;
	private Boolean aIsMale;
	private GregorianCalendar aDateOfBirth;
	private int aAge;
	public ArrayList<Membership> aSinNombre_Membership_ = new ArrayList<Membership>();

	public int age() {
		return aAge;
	}

	/**
	 * @return el parametro aName
	 */
	public String getaName() {
		return aName;
	}

	/**
	 * @param aName se le asigna a aName
	 */
	public void setaName(String aName) {
		this.aName = aName;
	}

	/**
	 * @return el parametro aTitle
	 */
	public String getaTitle() {
		return aTitle;
	}

	/**
	 * @param aTitle se le asigna a aTitle
	 */
	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}

	/**
	 * @return el parametro aIsMale
	 */
	public Boolean getaIsMale() {
		return aIsMale;
	}

	/**
	 * @param aIsMale se le asigna a aIsMale
	 */
	public void setaIsMale(Boolean aIsMale) {
		this.aIsMale = aIsMale;
	}

	/**
	 * @return el parametro aDateOfBirth
	 */
	public GregorianCalendar getaDateOfBirth() {
		return aDateOfBirth;
	}

	/**
	 * @param aDateOfBirth se le asigna a aDateOfBirth
	 */
	public void setaDateOfBirth(GregorianCalendar aDateOfBirth) {
		this.aDateOfBirth = aDateOfBirth;
	}
}