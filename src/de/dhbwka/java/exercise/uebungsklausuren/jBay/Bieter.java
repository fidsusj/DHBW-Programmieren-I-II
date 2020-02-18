package de.dhbwka.java.exercise.uebungsklausuren.jBay;

public class Bieter {

	private String surname;
	private String lastname;
	
	public Bieter(String surname, String lastname) {
		this.surname = surname;
		this.lastname = lastname;
	}
	
	public String getFullName() {
		return this.surname + " " + this.lastname;
	}

	public String getSurname() {
		return surname;
	}

	public String getLastname() {
		return lastname;
	}
	
}