package de.dhbwka.java.exercise.uebungsklausuren.chat;

import java.util.ArrayList;
import java.util.Iterator;

public class Network {

	private ArrayList<ChatSession> sessions;
	
	public Network() {
		sessions = new ArrayList<ChatSession>();
	}
	
	public void addChat(ChatSession chat) {
		sessions.add(chat);
	}
	
	public void deleteChat(ChatSession chat) {
		sessions.remove(chat);
	}
	
	public void send(String message, String nickname) {
		for (Iterator<ChatSession> iterator = sessions.iterator(); iterator.hasNext();) {
			ChatSession chatSession = (ChatSession) iterator.next();
			chatSession.registerMessage(nickname + ">> " + message + System.lineSeparator());
			
		}
	}
	
	public void send(String message) {
		for (Iterator<ChatSession> iterator = sessions.iterator(); iterator.hasNext();) {
			ChatSession chatSession = (ChatSession) iterator.next();
			chatSession.registerMessage("Info>> " + message + System.lineSeparator());
			
		}
	}

	public ArrayList<ChatSession> getSessions() {
		return sessions;
	}
	
}