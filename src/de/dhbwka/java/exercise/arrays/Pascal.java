package de.dhbwka.java.exercise.arrays;

public class Pascal {

	public static void main(String[] args) {

		int[][] pascal = new int[9][];
		
		pascal[0] = new int[]{1};
		pascal[1] = new int[]{1,1};
		
		for (int i = 2; i < pascal.length; i++) {
			pascal[i] = new int[i+1];
			pascal[i][0] = 1;
			pascal[i][pascal[i].length-1] = 1;
			for (int j = 1; j < pascal[i].length-1; j++) {
				pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
			}
		}
		
		
		
		// Ausgabe
		
		for (int i = 0; i < pascal.length; i++) {
			for (int j = 0; j < pascal[i].length; j++) {
				System.out.printf("%2d ", pascal[i][j]);
			}
			System.out.println("");
		}
		
	}

}