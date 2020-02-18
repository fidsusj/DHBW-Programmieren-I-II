package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class LeapYear {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Welches Jahr soll auf Schaltjahr geprüft werden? ");
		int year = scan.nextInt();
		
	    if(year%400 == 0 || year%100 != 0 && year%4 == 0)
		     System.out.println(year + " ist ein Schaltjahr");
	    else
		     System.out.println(year + " ist kein Schaltjahr");
		
		scan.close();

	}

}