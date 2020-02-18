package de.dhbwka.java.exercise.temp.sNatter;

public class Message {

	private String text;
	private User user;
	
	public Message(String text, User user) {
		super();
		this.text = text;
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public User getUser() {
		return user;
	}
	
}