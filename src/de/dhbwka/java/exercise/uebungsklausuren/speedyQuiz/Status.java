package de.dhbwka.java.exercise.uebungsklausuren.speedyQuiz;

import java.awt.Color;

public enum Status {

	ACTIVE(Color.ORANGE,0),
	CORRECT(Color.GREEN,1),
	WRONG(Color.RED,-1),
	PENDING(Color.WHITE,0),
	NO_ANSWER(Color.GRAY,0);
	
	private Color color;
	private int point;
	
	private Status(Color color, int point) {
		this.color = color;
		this.point = point;
	}

	public Color getColor() {
		return color;
	}

	public int getPoint() {
		return point;
	}
	
}