package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Eratostenes {

	public static void main(String[] args) {
		
//		System.out.println("Bitte geben Sie eine obere Grenze der Zahlen an, die auf Primzahl geprüft werden sollen.");
//		
//		Scanner scan = new Scanner(System.in);
//		int grenze = scan.nextInt();
//		
//		int[] potPrim = new int[grenze-1];
//		potPrim[0] = 2;
//		
//		for (int i = 1; i < potPrim.length; i++) {
//			potPrim[i] = potPrim[i-1] + 1;
//		}
//		
//		for (int i = 0; i < potPrim.length/2; i++) {
//			if(potPrim[i] == 0)
//				continue;
//			
//			for (int j = i + 1; j < potPrim.length; j++) {
//				if(potPrim[j] % potPrim[i] == 0)
//					potPrim[j] = 0;
//			}
//		}
//		
//		System.out.println("Die Primzahlen aller Zahlen von 2 bis " + grenze + " sind:");
//		
//		for (int i = 0; i < potPrim.length; i++) {
//			if(potPrim[i] != 0)
//				System.out.println(potPrim[i]);
//		}
//		
//		scan.close();
		
		
	/** 
	 * Verbesserung: Modulo Rechnung verbessert auf Addition -> Um vielfaches schneller
	 *  Weitere Verbesserung: boolsches Array, in dem die Zahlen durch die Indices representiert werden. nicht 32 Bit Speicherplatz, sondern 1 Bit! 
	 * 	Wertebereich viel größer 
	 * */
		
		System.out.println("Bitte geben Sie eine obere Grenze der Zahlen an, die auf Primzahl geprüft werden sollen.");
		
		Scanner scan = new Scanner(System.in);
		int grenze = scan.nextInt();
		
		int[] potPrim = new int[grenze+1];
		
		for (int i = 0; i < potPrim.length; i++) {
			potPrim[i] = i;		// Index entspricht Zahl, sonst Werte um 2 zu Index verschoben -> Ausgabe falsch
		}
		
		for (int i = 2; i < potPrim.length/2; i++) {
			if(potPrim[i] == 0)
				continue;
			
			for (int j = 2*potPrim[i]; j < potPrim.length; j+= potPrim[i]) {
				potPrim[j] = 0;
			}
		}
		
		System.out.println("Die Primzahlen aller Zahlen von 2 bis " + grenze + " sind:");
		
		for (int i = 2; i < potPrim.length; i++) {
			if(potPrim[i] != 0)
				System.out.println(potPrim[i]);
		}
		
		scan.close();

	}

}