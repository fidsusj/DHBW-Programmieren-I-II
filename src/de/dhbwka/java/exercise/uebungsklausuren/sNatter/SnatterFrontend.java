package de.dhbwka.java.exercise.uebungsklausuren.sNatter;

public interface SnatterFrontend {

	public void receiveMessage(Message msg);
	
	public void receiveMessage(String text);
	
	public void updateUserList();
	
	public User getUser();
	
}