package de.dhbwka.java.exercise.temp.sNatter;

public class User {

	private String sNatterName;
	private String name;
	
	public User(String sNatterName, String name) {
		this.sNatterName = sNatterName;
		this.name = name;
	}

	public String getsNatterName() {
		return sNatterName;
	}

	public String getName() {
		return name;
	}
	
}