package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

public class Airline {

	private String code;
	private String name;
	
	public Airline(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
}