package de.dhbwka.java.exercise.uebungsklausuren.chat;

public class Chat {

	public static void main(String[] args) {
		
		Network network = new Network();	
	    new ChatSession("Tick", network);
		new ChatSession("Trick", network);
		new ChatSession("Track", network);
		
	}

}