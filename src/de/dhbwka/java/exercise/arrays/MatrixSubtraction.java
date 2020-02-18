package de.dhbwka.java.exercise.arrays;

import java.util.Random;
import java.util.Scanner;

public class MatrixSubtraction {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Bitte geben Sie die Zeilenanzahl an: ");
		int z = scan.nextInt();
		System.out.println("Bitte geben Sie die Spaltenanzahl an: ");
		int s = scan.nextInt();
		
		Random rdm = new Random();
		int[][] matrix1 = new int[z][s], matrix2 = new int[z][s], matrix3 = new int[z][s];
		
		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1[i].length; j++) {
				matrix1[i][j] = rdm.nextInt(100);
				matrix2[i][j] = rdm.nextInt(100);
				matrix3[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		
		
		// Ausgabe
		
		System.out.println("");
		
		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1[i].length; j++) {
				System.out.printf("%2d ", matrix1[i][j]);
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < matrix2[i].length; j++) {
				System.out.printf("%2d ", matrix2[i][j]);
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i = 0; i < matrix3.length; i++) {
			for (int j = 0; j < matrix3[i].length; j++) {
				System.out.printf("%3d ", matrix3[i][j]);
			}
			System.out.println("");
		}
		
		scan.close();
		
	}

}