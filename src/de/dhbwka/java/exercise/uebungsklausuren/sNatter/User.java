package de.dhbwka.java.exercise.uebungsklausuren.sNatter;

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

	public void setsNatterName(String sNatterName) {
		this.sNatterName = sNatterName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}