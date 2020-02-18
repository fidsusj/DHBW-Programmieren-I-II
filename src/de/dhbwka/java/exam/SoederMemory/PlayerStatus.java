package de.dhbwka.java.exam.SoederMemory;

import java.awt.Color;

public enum PlayerStatus {

	ACTIVE(Color.ORANGE),
	WAITING(Color.BLACK),
	FINISHED(Color.GRAY);
	
	private Color color;

	private PlayerStatus(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}