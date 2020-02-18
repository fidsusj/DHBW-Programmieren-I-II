package de.dhbwka.java.exercise.uebungsklausuren.speedyQuiz;

@SuppressWarnings("serial")
public class GameException extends Exception{
	
	public GameException() {
		super();
	}
	
	public GameException(String text) {
		super(text);
	}

}