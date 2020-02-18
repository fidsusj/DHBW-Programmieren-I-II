package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

@SuppressWarnings("serial")
public class InvalidParameterException extends Exception{

	public InvalidParameterException() {
		super();
	}
	
	public InvalidParameterException(String message) {
		super(message);
	}
	
}