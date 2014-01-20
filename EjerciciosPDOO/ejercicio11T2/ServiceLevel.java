package ejercicio11T2;

import java.util.ArrayList;

import ejercicio11T2.Membership;

public class ServiceLevel {
	private String aName;
	public LoyaltyProgram aProgram;
	public ArrayList<Membership> aSinNombre_Membership_ = new ArrayList<Membership>();
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
}