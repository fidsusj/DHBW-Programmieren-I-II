package de.dhbwka.java.exercise.temp.sNatter;

public class Snatter {

	public static void main(String[] args) {
		
		SnatterService snatServ = new SnatterService();
		new SnatterBox(snatServ, new User("Donald Duck", "donald"));
		new SnatterBox(snatServ, new User("Daisy Duck", "daisy"));
		new SnatterBox(snatServ, new User("Gustav Gans", "gustav"));
		
	}

}