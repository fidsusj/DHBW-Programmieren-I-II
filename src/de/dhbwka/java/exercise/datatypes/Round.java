package de.dhbwka.java.exercise.datatypes;

public class Round {

	public static void main(String[] args) {
		
		double pi = 3.1415926;
		double e = 2.7182818;
		
		int piInt = (int) pi;
		int eInt = (int) e;
		
		System.out.println("pi ganzzahlig: " + piInt); // Ausgabe: 3
		System.out.println("e ganzzahlig: " + eInt); // Ausgabe: 2
		System.out.println("\n");
		
		// Verbesserung

		pi = pi<0? (pi-=0.5) : (pi+=0.5);
		e = e<0? (e-=0.5) : (e+=0.5);
		
		System.out.println("pi gerundet: " + (int) pi);
		System.out.println("pi gerundet: " + (int) e);
		
		
		
//		System.out.println("\n");
//		
//		System.out.println("pi gerundet mit Math:" + Math.round(pi));
//		System.out.println("pi gerundet mit Math:" + Math.round(e));
		
		
		
		
//      Tims Lösung

//         toRound % 1 >= 0 --> return (int) toRound + 1
//         toRound % 1 <= -0.5 --> return (int) toRound - 1
//         else: return (int) toRound
	}

}