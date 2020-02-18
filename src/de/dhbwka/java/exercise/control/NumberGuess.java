package de.dhbwka.java.exercise.control;

import java.util.Random;
import java.util.Scanner;

public class NumberGuess {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.print("Wie ist dein Name? ");
		String name = scan.next();
		
		Random rnd = new Random();
		int zufall, con, guess, count = 0;
		
		// auch mit switch case
		
		do {
			zufall = rnd.nextInt(100)+1;
			count = 0;
		
			do {
				count++;
				System.out.print(name + ", rate eine Zahl [1-100]: ");
				guess = scan.nextInt();
				if(guess > zufall)
					System.out.println("Versuch " + count + ": " + guess + " ist zu hoch.");
				else if(guess < zufall)
					System.out.println("Versuch " + count + ": " + guess + " ist zu klein.");
			
			} while (guess != zufall);
			
			System.out.println("Versuch " + count + ": " + guess + " ist korrekt. \n");
			System.out.println("Was möchtest du tun? \n 0 - Das Spiel beenden \n 1 - Das Spiel fortsetzen");
			
			con = scan.nextInt();
			
		}while(con == 1);
		
		
		scan.close();

	}

}