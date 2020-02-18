package de.dhbwka.java.exercise.uebungsklausuren.sNatter;

import java.util.ArrayList;
import java.util.List;

public class SnatterService {

	private List<SnatterFrontend> frontends;
	
	public SnatterService() {
		this.frontends = new ArrayList<>();
	}

	public void connect(SnatterFrontend fe) {
		if(!frontends.contains(fe)) {
			frontends.add(fe);
			for (int i = 0; i < frontends.size(); i++) {
				frontends.get(i).updateUserList();
			}
		}
	}
	
	public void disconnect(SnatterFrontend fe) {
		frontends.remove(fe);
		for (int i = 0; i < frontends.size(); i++) {
			frontends.get(i).receiveMessage(fe.getUser().getsNatterName() + " has left sNatter");
			frontends.get(i).updateUserList();
		}
	}
	
	public void send(Message msg) {
		for (int i = 0; i < frontends.size(); i++) {
			frontends.get(i).receiveMessage(msg);
		}
	}
	public void send(String msg) {
		for (int i = 0; i < frontends.size(); i++) {
			frontends.get(i).receiveMessage(msg);
		}
	}
	
	public List<User> getUserList(){
		List<User> users = new ArrayList<>();
		for (int i = 0; i < frontends.size(); i++) {
			users.add(frontends.get(i).getUser());
		}
		return users;
	}
	
}