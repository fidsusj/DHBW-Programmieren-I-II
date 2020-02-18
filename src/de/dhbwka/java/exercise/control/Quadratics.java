package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class Quadratics {

	public static void main(String[] args) {
		
		double result, result2, determinante;
		Scanner scan = new Scanner(System.in);
		System.out.println("Bitte geben sie den Koeffizient a ein: ");
		double a = scan.nextDouble();
		System.out.println("Bitte geben sie den Koeffizient b ein: ");
		double b = scan.nextDouble();
		System.out.println("Bitte geben sie den Koeffizient c ein: ");
		double c = scan.nextDouble();
		
		if(a == 0) {
			if(b != 0){
				result = -c / b;
				System.out.println("Die Nullstelle ist " + result + ".");
			}else
				System.out.println("Die Gleichung ist degeneriert.");
		}else {
			determinante = Math.pow(b,2)- 4*a*c;  // Oder b*b
			if(determinante < 0)
				System.out.println("Die Lösung ist konjugiert komplex.");
			else if(determinante == 0) {
				result = (-b + Math.sqrt(determinante)) / (2*a);
				System.out.println("Die Nullstellen sind: x1 = " + result + ".");
			}else {
				result = (-b + Math.sqrt(determinante)) / (2*a);
				result2 = (-b - Math.sqrt(determinante)) / (2*a);
				System.out.println("Die Nullstellen sind: x1 = " + result + " x2 = " + result2 + ".");
			}
		}		
		
		scan.close();

	}

}