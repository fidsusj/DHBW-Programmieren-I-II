package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class AddUp {

	public static void main(String[] args) {
		
		// Mit While Schleife
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Zahl eingeben (<0 für Abbruch): ");
		double x = 0, y  = scan.nextDouble();
		
		while(y >= 0) {
			x += y;
			System.out.print("Zahl eingeben (<0 für Abbruch): ");
			y = scan.nextDouble();
		}
		
		System.out.println("Summe: " + x + "\n");
		
		
		
		// Mit Do-While Schleife
		
		x = y = 0;
		
		do {			
			x += y;
			System.out.print("Zahl eingeben (<0 für Abbruch): ");
			y = scan.nextDouble();
		} while(y >= 0);
		
		System.out.println("Summe: " + x);
		
		scan.close();
		
	}

}