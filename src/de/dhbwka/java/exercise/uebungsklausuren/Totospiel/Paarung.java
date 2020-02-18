package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

import java.util.Random;

public class Paarung {

	private final Random rdm = new Random();
	
	private Verein heim;
	private Verein gast;
	private int toreHeim;
	private int toreGast;
	
	public Paarung(Verein heim, Verein gast) {
		this.heim = heim;
		this.gast = gast;
	}
	
	private double calcSum(int j) {
		double sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += rdm.nextInt(j)+1;
		}
		sum-=3;
		sum/=3;
		return sum;
	}
	
	public void spielen() {
		this.toreHeim = (int) calcSum(6);
		this.toreGast = (int) calcSum(5);
	}
	
	public String getSpielstand() {
		return this.toreHeim + " : " + this.toreGast;
	}

	public Verein getHeim() {
		return heim;
	}

	public Verein getGast() {
		return gast;
	}

	public int getToreHeim() {
		return toreHeim;
	}

	public int getToreGast() {
		return toreGast;
	}
	
}