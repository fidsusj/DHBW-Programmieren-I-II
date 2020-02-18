package de.dhbwka.java.exercise.classes.abstr;

public class Bicycle extends Vehicle {

	public Bicycle(double vcurr) {
		super(2,30,0,0);
		setSpeed(vcurr);
	}
	
	public void info() {
		System.out.println(this.toString());
	}
	
}