package de.dhbwka.java.exercise.uebungsklausuren.jBay;

public class Gebot {

	private double price;
	private Bieter bieter;
	
	public Gebot(double price, Bieter bieter) {
		this.price = price;
		this.bieter = bieter;
	}

	public double getPrice() {
		return price;
	}

	public Bieter getBieter() {
		return bieter;
	}
	
}