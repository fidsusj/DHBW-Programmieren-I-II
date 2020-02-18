package de.dhbwka.java.exercise.classes.periodic;

public class Metal extends Element {

	private boolean metalliod;
	private double conductivity;
	
	public Metal(String name, String symbol, int ordinal, char shell, int phase, boolean main, boolean metalloid, double conductivity) {
		super(name, symbol, ordinal, shell, phase, main);
		this.metalliod = metalloid;
		if(conductivity > 0)
			this.conductivity = conductivity;
		else
			this.conductivity = 1.0;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"Halbmetal: " + metalliod + "\n" + 
				"\u03C3: " + conductivity + "\n";
	}

	public boolean isMetalliod() {
		return metalliod;
	}

	public double getConductivity() {
		return conductivity;
	}

	public void setMetalliod(boolean metalliod) {
		this.metalliod = metalliod;
	}

	public void setConductivity(double conductivity) {
		this.conductivity = conductivity;
	}
	
}