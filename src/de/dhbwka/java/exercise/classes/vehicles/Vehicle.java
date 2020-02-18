package de.dhbwka.java.exercise.classes.vehicles;

public class Vehicle {

	protected int wheels;
	protected double vmax;
	protected double vcurr;
	protected double pos;
	
	public Vehicle() {

	}
	
	public Vehicle(int wheels, double vmax, double vcurr, double pos) {
		this.wheels = wheels;
		this.vmax = vmax;
		this.vcurr = vcurr;
		this.pos = pos;
	}
	
	protected void setSpeed(double speed) {
		if(speed <= 0)
			this.vcurr = 0;
		else if(speed >= vmax)
			this.vcurr = vmax;
		else
			this.vcurr = speed;
	}
	
	protected void drive(double minutes) {
		pos += vcurr * (minutes/60);
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + 
				":\twheels: " + wheels + 
				" , position: " + pos + 
				" , current velocity: " + vcurr +
				" , maximum velocity: " + vmax;
	}
	
	
}