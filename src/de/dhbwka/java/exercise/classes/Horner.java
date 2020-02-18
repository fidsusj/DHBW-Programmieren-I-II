package de.dhbwka.java.exercise.classes;

public class Horner {

	private int[] koeff;
	
	public Horner(int[] koeff) {
		
		if(koeff != null && koeff.length != 0)
			this.koeff = koeff;
		else
			this.koeff = new int[1];
		
	}
	
	public double getValue(double param) {
		double val = koeff[0];
		
		for (int i = 1; i < koeff.length; i++) {
			val *= param; 
			val += koeff[i];
		}
		return val;
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < koeff.length; i++) {
			if(koeff[i] == 0)
				continue;
			
			if(output == "")
				output += koeff[i] + "x^" + (koeff.length - 1 - i) + " ";
			else if (i == koeff.length-1)
				output += getAugury(koeff[i]) + " " + Math.abs(koeff[i]);
			else
				output += getAugury(koeff[i]) + " " + Math.abs(koeff[i]) + "x^" + (koeff.length - 1 - i) + " ";
				
		}
		return output;
	}
	
	private String getAugury(double val) {
		return (String) (val < 0 ? "-" : "+");
	}
	
	public static void main(String[] args) {
		System.out.println(new Horner(new int[] {1,2,3,-4,5}).toString());
	}	
}