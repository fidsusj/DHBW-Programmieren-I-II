package de.dhbwka.java.exercise.arrays;

import java.util.Random;

public class StandardDeviation {

	public static void main(String[] args) {
		
		Random rdm = new Random();
		int[] zahlen = new int[100];
		
		for (int i = 0; i < zahlen.length; i++) {
			zahlen[i] = rdm.nextInt(11);
		}
		
		double midvalue, stdabw, sum = 0;
		
		for (int val : zahlen) {
			sum += val;
		}
		
		midvalue = sum / zahlen.length;
		sum = 0;
		
		for (int val : zahlen) {
			sum += Math.pow((val - midvalue),2);
		}
		
		stdabw = Math.sqrt(sum/(zahlen.length - 1));
		
		System.out.println("Der Mittelwert der zufälligen 100 Zahlen ist: " + midvalue);
		System.out.println("Die Standardabweichung der zufälligen 100 Zahlen ist: " + stdabw);
		
	}

}