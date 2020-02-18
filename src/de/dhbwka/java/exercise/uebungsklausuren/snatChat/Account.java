package de.dhbwka.java.exercise.uebungsklausuren.snatChat;

import java.awt.Color;
import java.util.Random;

public class Account {

	private final Random rdm = new Random();
	
	private String name;
	private State state;
	private Color color;
	
	public Account(String name) {
		this.name = name;
		this.state = State.AVAILABLE;
		this.color = new Color(rdm.nextInt(201),rdm.nextInt(201),rdm.nextInt(201));
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
}