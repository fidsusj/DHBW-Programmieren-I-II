package de.dhbwka.java.exercise.strings;

public class RomanNumber {

	private String romanNumber;
	
	public RomanNumber(String romanNumber) {
		this.romanNumber = romanNumber;
	}
	
	private int getValue(char c) {
		switch(c) {
			case 'M': return 1000;
			case 'D': return 500;
			case 'C': return 100;
			case 'L': return 50;
			case 'X': return 10;
			case 'V': return 5;
			case 'I': return 1;
			default: return 0;
		}
	}
	
	// Andere Strategie: mit String.replace alle IX, IV, ... umwandeln
	
	public int getValue() {
		
		int value = 0;
		
		for (int i = 0; i < romanNumber.length();) {
			if(i == romanNumber.length()-1) {
				value += getValue(romanNumber.charAt(i));
				i++;
			}
			else if(getValue(romanNumber.charAt(i)) >= getValue(romanNumber.charAt(i+1))){
				value += getValue(romanNumber.charAt(i));
				i++;
			}
			else {
				value += getValue(romanNumber.charAt(i+1)) - getValue(romanNumber.charAt(i));
				i+=2;
			}
		}
		
		return value;
	}
	
}