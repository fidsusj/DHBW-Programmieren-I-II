package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

public class Sitz {

	private int row;
	private char position;
	private Buchungsstatus status;
	
	public Sitz(int row, char position) {
		this.row = row;
		this.position = position;
		this.status = Buchungsstatus.FREI;
	}

	public int getRow() {
		return row;
	}

	public char getPosition() {
		return position;
	}

	public Buchungsstatus getStatus() {
		return status;
	}
	
	public void setStatus(Buchungsstatus status) {
		this.status = status;
	}
	
}