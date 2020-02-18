package de.dhbwka.java.exercise.control;

public class ShoeSize {

	public static void main(String[] args) {
		
		System.out.printf("%-14s|%6s \n", "Zentimeter", "Größe");
		System.out.println("--------------+------");
		
		int size = 0; 
		double xmin, xmax = 20.0;
		
		while(size < 50) {
			
			size = (int) (Math.round(1.5 * xmax));
			xmin =  (xmax - (2./3));
			System.out.printf("%2.2f - %2.2f | %2d \n", xmin, xmax, size);
			xmax = xmax + (2./3);
			
		}
		
	}

}