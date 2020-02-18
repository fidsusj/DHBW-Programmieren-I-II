package de.dhbwka.java.exercise.uebungsklausuren.snatChat;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class MyJRadioButton extends JRadioButton{
	
	private State state;
	
	public MyJRadioButton() {
		this("", null);
	}

	public MyJRadioButton(String text) {
		this(text, null);	
	}
	
	public MyJRadioButton(String text, State state) {
		super(text);
		this.state = state;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}