package de.dhbwka.java.exercise.control;

public class Deers {

	public static void main(String[] args) {

		double bestand = 200;
		int index = 0;
		
		while(bestand <= 300) {
	
			index++;
			bestand *= 1.1;
			bestand = (int) bestand - 15;
			
			System.out.printf("%2d: %d Hirsche \n", index, (int) bestand);
			
		}
		
	}

}