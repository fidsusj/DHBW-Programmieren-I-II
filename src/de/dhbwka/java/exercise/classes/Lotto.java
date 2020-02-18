package de.dhbwka.java.exercise.classes;

import java.util.Random;
import java.util.Scanner;

//java.util.Arrays -> Sort

public class Lotto {

	private int amountTotal;
	private int amountCurrent;
	private int[] guess;
	private int[] actual;
	
	public Lotto(int m, int n) {
		
		this.amountCurrent = m;
		this.amountTotal = n;
		
		this.guess = new int[amountCurrent];
		this.actual = new int[amountCurrent];
		
	}
	
	public void tippen() {
		Scanner scan = new Scanner(System.in);
		int number;
		for(int i = 0; i < guess.length; i++) {
			while(guess[i] == 0){	
				System.out.println("Bitte tippen Sie die " + (i+1) + ". Zahl:");
				number = scan.nextInt();
				boolean contains = false;
				
				for (int j = 0; j < i; j++) {
					if(guess[j] == number) {
						System.err.println("Eingabe falsch!");
						contains = true;
						break;
					}
				}
				
				if (contains)
					continue;
				
				if(0 < number && number <= amountTotal)
					guess[i] = number;
				else
					System.err.println("Eingabe falsch!");
			}
		}
		bubbleSort(guess);
		scan.close();
	}
	
	public void tippen(int[] tipp) {
		guess = tipp;
		bubbleSort(guess);
	}
	
	public void ziehen() {
		Random rdm = new Random();
		for (int i = 0; i < actual.length; i++) {
			actual[i] = rdm.nextInt(amountTotal + 1);
		}
		bubbleSort(actual);
	}
	
	public String toString() {
		String output = "";
		
		if(guess[0] != 0) {
			output += "Tipp: ";
			for (int i = 0; i < guess.length; i++) {
				output += guess[i] + " ";
			}
		}
		
		if(actual[0] != 0) {
			output += "\nGezogene Zahlen: ";
			for (int i = 0; i < actual.length; i++) {
				output += actual[i] + " ";
			}
			
			output += "\nRichtig Zahlen: " + richtige();
			
		}
		
		return output;
	}
	
	public int richtige() {
		int right = 0;
		for (int i = 0; i < guess.length; i++) {
			for (int j = 0; j < actual.length; j++) {
				if(guess[i] == actual[j]) {
					right++;
					break;
				}
			}
		}
		
		return right;
	}
	
	private int[] bubbleSort(int[] array) {
		
		int temp;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length-(1+i); j++) {
				if(array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		
		return array;
	}
	
}