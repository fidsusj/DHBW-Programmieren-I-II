package de.dhbwka.java.exercise.arrays;

public class Fibonacci {

	public static void main(String[] args) {

		long[] fibo = new long[50];
		fibo[0] = fibo[1] = 1;
		
		for (int i = 2; i < fibo.length; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		System.out.println("Die Fibonacci-Folge für 20 Zahlen sind:");
		
		for (int i = 0; i < fibo.length; i++) {
			System.out.println(fibo[i]);
		}
		
	}

}