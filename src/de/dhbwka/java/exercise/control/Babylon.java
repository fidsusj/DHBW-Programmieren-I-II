package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class Babylon {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Bitte geben Sie eine Zahl ein: ");
		double sqrt = scan.nextDouble();
		
		
		double zahlAlt = 5, zahlNeu = (zahlAlt + Math.abs(sqrt) / zahlAlt) / 2;
		
		while(Math.abs(zahlAlt - zahlNeu) >= 1.0E-6) {
			zahlAlt = zahlNeu;
			zahlNeu = (zahlAlt + Math.abs(sqrt) / zahlAlt) / 2;
			System.out.println("xn: " + zahlNeu);
		}

		System.out.println("Die positive Wurzel aus " + sqrt + " ist " + zahlNeu);
		
		
		scan.close();
	}

}