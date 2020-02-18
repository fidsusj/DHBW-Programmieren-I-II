package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

public class Destination {

	private String name;
	private String city;
	private String code;
	
	public Destination(String name, String city, String code) {
		this.name = name;
		this.city = city;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}
	
}