package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.dhbwka.java.exercise.uebungsklausuren.BookingSystem.InvalidParameterException;

public class Flug {

	private String name;
	private Airline airline;
	private Destination destinationFrom;
	private Destination destinationTo;
	private Calendar departure;
	private Calendar landing;
	private int rows;
	private int seatsPerRow;
	
	private List<Sitz> seats;
	
	public Flug(String name, Airline airline, Destination destinationFrom, Destination destinationTo, int rows, int seatsPerRow) throws InvalidParameterException {
		this(name, airline, destinationFrom, destinationTo, Calendar.getInstance(), Calendar.getInstance(), rows, seatsPerRow);
	}
	
	public Flug(String name, Airline airline, Destination destinationFrom, Destination destinationTo, Calendar departure, Calendar landing, int rows, int seatsPerRow) throws InvalidParameterException {
		this.name = name;
		this.airline = airline;
		this.destinationFrom = destinationFrom;
		this.destinationTo = destinationTo;
		this.departure = departure;
		this.landing = landing;
		
		if(rows >= 1)
			this.rows = rows;
		else
			throw new InvalidParameterException();
			
		if(seatsPerRow >= 1 && seatsPerRow <= 26)
			this.seatsPerRow = seatsPerRow;
		else
			throw new InvalidParameterException();
		
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
		
		this.seats = new ArrayList<>();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.seatsPerRow; j++) {
				this.seats.add(new Sitz(i,(char)(65+j)));
			}
		}
		
	}
	
	public int freiePlaetze() {
		int counter = 0;
		for (Sitz sitz : seats) {
			counter += sitz.getStatus().equals(Buchungsstatus.FREI) ? 1 : 0;
		}
		return counter;
	}

	public String getName() {
		return name;
	}

	public Airline getAirline() {
		return airline;
	}

	public Destination getDestinationFrom() {
		return destinationFrom;
	}

	public Destination getDestinationTo() {
		return destinationTo;
	}

	public Calendar getDeparture() {
		return departure;
	}

	public Calendar getLanding() {
		return landing;
	}

	public int getRows() {
		return rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public List<Sitz> getSeats() {
		return seats;
	}
	
}