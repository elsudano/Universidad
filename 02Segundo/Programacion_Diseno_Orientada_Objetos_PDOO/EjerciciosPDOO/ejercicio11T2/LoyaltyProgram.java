package ejercicio11T2;

import java.util.ArrayList;

import ejercicio11T2.Membership;
import ejercicio11T2.ServiceLevel;

public class LoyaltyProgram {
	private String aName;
	public ArrayList<Membership> aSinNombre_Membership_ = new ArrayList<Membership>();
	public ArrayList<ServiceLevel> aLevels_ordered_ = new ArrayList<ServiceLevel>();

	public void enroll(Customer pC) {
		throw new UnsupportedOperationException();
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
}