package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

import de.dhbwka.java.exercise.uebungsklausuren.BookingSystem.InvalidParameterException;

public class BookingSystem {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Destination frankfurt = new Destination("Flughafen Berlin Brandenburg", "Frankfurt am Main", "FRA");
		Destination newyork = new Destination("Int. Flughafen 'John F. Kennedy'", "New York City", "JFK");
		Airline lufthansa = new Airline("DLH", "Deitsche Lufthansa");
		try {
			Flug f = new Flug("LH4711", lufthansa, frankfurt, newyork, 15, 4);
			BookingForm bf = new BookingForm(f);
		} catch (InvalidParameterException e) {
			System.err.println("Invalid distribution of seats");
		}
		
	}

}