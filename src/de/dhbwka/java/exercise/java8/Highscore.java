package de.dhbwka.java.exercise.java8;

public class Highscore {

	private String name;
	private int score;
	
	public Highscore(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public static int compareScore(Highscore h1, Highscore h2) {
		return Integer.valueOf(h1.getScore()).compareTo(h2.getScore());
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
	
}