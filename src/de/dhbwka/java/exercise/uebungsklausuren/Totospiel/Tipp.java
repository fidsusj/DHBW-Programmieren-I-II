package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

public class Tipp {

	private Paarung paarung;
	private int ergebnisTipp;
	
	public Tipp(Paarung paarung, int ergebnisTipp) {
		this.paarung = paarung;
		this.ergebnisTipp = ergebnisTipp;
	}

	public Paarung getPaarung() {
		return paarung;
	}

	public int getErgebnisTipp() {
		return ergebnisTipp;
	}
	
}