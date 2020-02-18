package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
//		System.out.println("Bitte geben sie eine Zahl ein:");
//		CrossTotal ct = new CrossTotal(scan.next());
//		System.out.println(ct.getCrossTotal());
//		
//		System.out.println("\n--------\n");
//		
//		System.out.println("Bitte geben sie ein Wort ein:");
//		Palindrome pd = new Palindrome(scan.next());
//		System.out.println(pd.getWord());
//		System.out.println(pd.getPalindrom());
//		System.out.println(pd.isPalindrom());
//		
//		System.out.println("\n--------\n");
		
		System.out.println("Bitte geben sie eine römische Zahl ein:");
		RomanNumber pd = new RomanNumber(scan.next());
		
		System.out.println(pd.getValue());
		
		scan.close();
		
	}

}