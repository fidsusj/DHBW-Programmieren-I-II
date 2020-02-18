package de.dhbwka.java.exercise.classes.abstr;

public class Ambulance extends Car {

	private boolean blueLightOn;
	
	public Ambulance(double vcurr, boolean turnedOn) {
		super(220,vcurr);
		this.blueLightOn = turnedOn;
	}
	
	public void turnOn() {
		this.blueLightOn = true;
	}
	
	public void turnOff() {
		this.blueLightOn = false;
	}
	
	public String toString() {
		return super.toString() + 
				" , blue Light on: " + blueLightOn;
	}
	
}