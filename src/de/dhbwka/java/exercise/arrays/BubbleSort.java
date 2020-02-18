package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class BubbleSort {

	public static void main(String[] args) {
		
		System.out.print("Bitte geben Sie die Mächtigkeit der Menge ein: ");
		
		Scanner scan = new Scanner(System.in);
		int temp, len = scan.nextInt();
		
		int[] menge = new int[len];
		
		for (int i = 0; i < menge.length; i++) {
			System.out.print("Bitte geben Sie das " + (i+1) + ". Element der Menge ein: ");
			menge[i] = scan.nextInt();
		}
		
		for (int i = 0; i < menge.length; i++) {
			for (int j = 0; j < menge.length-(1+i); j++) {
				if(menge[j] > menge[j + 1]) {
					temp = menge[j];
					menge[j] = menge[j+1];
					menge[j+1] = temp;
				}
			}
		}
		
		System.out.println("Die sortierte Menge: ");
		
		for (int i = 0; i < menge.length; i++) {
			System.out.flush();
			System.out.println(menge[i]);
		}
		
		scan.close();
		
	}

}