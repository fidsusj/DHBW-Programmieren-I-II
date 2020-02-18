package de.dhbwka.java.exercise.uebungsklausuren.zoo;

public class ZooTier {

	private String name;
	private String art;
	private String lieblingsFutter;
	
	public ZooTier(String art, String name, String lieblingsFutter) {
		this.art = art;
		this.name = name;
		this.lieblingsFutter = lieblingsFutter;
	}
	
	public void fuettern(String futter){
		if(this.lieblingsFutter.equalsIgnoreCase(futter))
			System.out.println(toString() + "frisst " + futter);
		else
			System.out.println(toString() + "verschmäht " + futter);
	}

	public String toString() {
		return this.name + " (" + this.art + ") ";
	}
	
	public String getName() {
		return name;
	}

	public String getArt() {
		return art;
	}

	public String getLieblingsFutter() {
		return lieblingsFutter;
	}
	
	
	
}