package de.dhbwka.java.exercise.temp.sNatter;

public interface SnatterFrontend {

	public void receiveMessage(Message msg);
	
	public void receiveMessage(String msg);
	
	public void updateUserList();
	
	public User getUser();
	
}