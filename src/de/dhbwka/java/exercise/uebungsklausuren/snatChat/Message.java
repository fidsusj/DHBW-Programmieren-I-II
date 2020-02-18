package de.dhbwka.java.exercise.uebungsklausuren.snatChat;

public class Message {

	private String text;
	private Account account;
	
	public Message(String text, Account account) {
		this.text = text;
		this.account = account;
	}

	public String getText() {
		return text;
	}

	public Account getAccount() {
		return account;
	}
	
	public String toString() {
		return this.account.getName() + ": " + this.text;
	}
	
	public static String rot13(String message) {
		char[] letters = message.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			char letter = letters[i];
			if('A' <= letter && letter <= 'M' || 'a' <= letter && letter <= 'm')
				letters[i] = (char) (letter + 13);
			else if ('N' <= letter && letter <= 'Z' || 'n' <= letter && letter <= 'z')
				letters[i] = (char) (letter - 13);
		}
		return new String(letters);
	}
	
}