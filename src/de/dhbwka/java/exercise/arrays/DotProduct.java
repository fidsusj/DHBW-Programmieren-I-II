package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class DotProduct {

	public static void main(String[] args) {
		
		System.out.print("Bitte geben sie eine Dimension an: ");
		
		Scanner scan = new Scanner(System.in);
		int sum = 0, dim = scan.nextInt();
		
		int[] vektor1 = new int[dim], vektor2 = new int[dim];
		
		for(int i = 0; i < dim; i++) {
			System.out.print("Bitte geben sie x" + (i+1) + " für Vektor 1 ein: ");
			vektor1[i] = scan.nextInt();
			System.out.print("Bitte geben sie x" + (i+1) + " für Vektor 2 ein: ");
			vektor2[i] = scan.nextInt();
		}
		
		for (int i = 0; i < dim; i++) {
			sum += vektor1[i] * vektor2[i];
		}
		
		System.out.println("Der das Skalarprodukt deiner Vektoren ist: " + sum);
		
		scan.close();
		
	}

}