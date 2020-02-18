package de.dhbwka.java.exercise.control;

public class TemperatureTable {

	public static void main(String[] args) {
		
		int f = 0;
		double c = 0;
		System.out.printf("%10s | %7s \n", "Fahrenheit", "Celsuis");
		System.out.println("-----------+--------");
		
		while(f <= 300) {
			c = (5./9) * (f-32);
			System.out.printf("%-10d | %7.1f \n", f, c);
			f += 20;
		}

	}

}