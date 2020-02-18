package de.dhbwka.java.exercise.operators;

import java.util.Scanner;

public class Easter {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Bitte geben Sie das Jahr ein: ");
		int year = scan.nextInt();
		
		int a,b,c,k,p,q,m,n,d,e,ostern;
		
		a = year % 19;
		b = year % 4;
		c = year % 7;
		k = year / 100;
		p = (8*k + 13) / 25;
		q = k / 4;
		m = (15 + k - p - q) % 30;
		n = (4 + k - q) % 7;
		d = (19*a + m) % 30;
		e = (2*b + 4*c + 6*d + n) % 7;
		ostern = 22 + d + e;
		
		if(ostern > 31) {
			ostern-=31;
			System.out.println(ostern + ".April");
		}else {
			System.out.println(ostern + ".März");
		}
		
		scan.close();

	}

}
