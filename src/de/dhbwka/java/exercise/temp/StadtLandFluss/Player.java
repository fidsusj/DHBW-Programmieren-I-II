package de.dhbwka.java.exercise.temp.StadtLandFluss;

public class Player {

	private String name;
	private int points;
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}
	
}