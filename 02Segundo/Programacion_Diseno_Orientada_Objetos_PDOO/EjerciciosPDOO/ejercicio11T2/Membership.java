package ejercicio11T2;

public class Membership {
	public ServiceLevel aCurrentLevel;
	public LoyaltyProgram aPrograms;
	public Customer aParticipants;

	public void setPrograms(LoyaltyProgram pPrograms) {
		this.aPrograms = pPrograms;
	}

	public LoyaltyProgram getPrograms() {
		return this.aPrograms;
	}

	public void setParticipants(Customer pParticipants) {
		this.aParticipants = pParticipants;
	}

	public Customer getParticipants() {
		return this.aParticipants;
	}
}