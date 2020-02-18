package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Norm {

	public static void main(String[] args) {

		System.out.print("Bitte geben sie eine Dimension an: ");
		
		Scanner scan = new Scanner(System.in);
		int sum = 0, dim = scan.nextInt();
		
		int[] vektor = new int[dim];
		
		for(int i = 0; i < dim; i++) {
			System.out.print("Bitte geben sie x" + (i+1) + " ein: ");
			vektor[i] = scan.nextInt();
		}
		
		for (int i : vektor) {
			sum += Math.pow(i,2);
		}
		
		System.out.println("Der Betrag deines Vektors ist: " + Math.sqrt(sum));
		
		scan.close();
		
	}

}