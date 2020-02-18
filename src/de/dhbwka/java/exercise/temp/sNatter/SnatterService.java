package de.dhbwka.java.exercise.temp.sNatter;

import java.util.ArrayList;
import java.util.List;

public class SnatterService {

	private List<SnatterFrontend> frontends;
	
	public SnatterService() {
		
		this.frontends = new ArrayList<>();
		
	}
	
	public void connect(SnatterFrontend fe) {
		if(!this.frontends.contains(fe)) {
			this.frontends.add(fe);
			
			for (SnatterFrontend snatterFrontend : frontends) {
				snatterFrontend.updateUserList();
			}
		}
	}
	
	public void disconnect(SnatterFrontend fe) {
		if(this.frontends.contains(fe)) {
			this.frontends.remove(fe);
			
			for (SnatterFrontend snatterFrontend : frontends) {
				snatterFrontend.receiveMessage(fe.getUser().getsNatterName() + " has left sNatter");
				snatterFrontend.updateUserList();
			}
		}
	}
	
	public void send(Message msg) {
		for (SnatterFrontend snatterFrontend : frontends) {
			snatterFrontend.receiveMessage(msg);
		}
	}
	
	public void send(String msg) {
		for (SnatterFrontend snatterFrontend : frontends) {
			snatterFrontend.receiveMessage(msg);
		}
	}
	
	public List<User> getUserList(){
		List<User> users = new ArrayList<>();
		for (SnatterFrontend snatterFrontend : frontends) {
			users.add(snatterFrontend.getUser());
		}
		return users;
	}
	
}