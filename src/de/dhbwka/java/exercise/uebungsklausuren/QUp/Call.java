package de.dhbwka.java.exercise.uebungsklausuren.QUp;

public class Call {

	private int number;
	private DeskApp desk;
	
	public Call(int number, DeskApp desk) {
		this.number = number;
		this.desk = desk;
	}

	public int getNumber() {
		return number;
	}

	public DeskApp getDesk() {
		return desk;
	}
	
	public String toString() {
		return String.format("%d: Sachbearbeitung %d (Raum %d)", this.number, this.desk.getNumber(), this.desk.getRoom());
	}
	
}