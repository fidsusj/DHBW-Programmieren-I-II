package de.dhbwka.java.exam.SoederMemory;

public class Player {

	private String name;
	private int points;
	private PlayerStatus status;
	
	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.status = PlayerStatus.WAITING;
	}
	
	public void addPoint() {
		this.points++;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

}