package de.dhbwka.java.exercise.strings;

public class CrossTotal {
	
	private int value;

	public CrossTotal(String value) {
		
		for(int i = 0; i < value.length(); i++) {
			// Auto Unboxing dabei
			this.value += Integer.valueOf(value.charAt(i)-'0');
		}
		
		// Character.getNumericValue(value.charAt(i))
		// substring(i,i+1)
		
		/**
		for(int i = 0; i < value.length(); i++) {
			this.value += Integer.parseInt("" + value.charAt(i));
		}
		*/
		
	}

	public int getCrossTotal() {
		return value;
	}

}