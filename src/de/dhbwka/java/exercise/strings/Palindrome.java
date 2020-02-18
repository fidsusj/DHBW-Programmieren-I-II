package de.dhbwka.java.exercise.strings;

public class Palindrome {

	private String word;
	private String palindrom;
	
	public Palindrome(String word) {
		this.word = word;
		
		//StringBuffer sb = new StringBuffer(word);
		//sb.reverse();
		
		StringBuffer sb = new StringBuffer();
		for(int i = word.length() - 1; i >= 0; i--) {
			sb.append(word.charAt(i));
		}
		
		this.palindrom = sb.toString();
	}
	
	public boolean isPalindrom() {
		return word.equalsIgnoreCase(palindrom);
	}

	public String getWord() {
		return word;
	}

	public String getPalindrom() {
		return palindrom;
	}
	
}