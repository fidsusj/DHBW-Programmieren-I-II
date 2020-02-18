package de.dhbwka.java.exercise.classes;

public class Radio {

	private boolean on;
	private int volume;
	private double frequency;
	
	public Radio() {
		this.on = false;
		this.volume = 5;
		this.frequency = 99.9D;
	}
	
	public Radio(boolean on, int volume, double frequency) {
		this.on = on;
		
		if(volume > 10)
			this.volume = 10;
		else if(volume < 0)
			this.volume = 0;
		else
			this.volume = volume;
		
		if(frequency > 110.0)
			this.frequency = 110.0;
		else if(frequency < 85.0)
			this.frequency = 85.0;
		else
			this.frequency = frequency;
	}
	
	public void incVolume() {
		if(volume<10 && on)
			volume += 1;
	}
	
	public void decVolume() {
		if(volume>0 && on)
			volume -= 1;
	}
	
	public void turnOn() {
		this.on = true;
	}
	
	public void turnOff() {
		this.on = false;
	}
	
	public void setFrequency(double frequency) {
		if(85.0 <= frequency && frequency <= 110.0)
			this.frequency = frequency;
		else
			this.frequency = 99.9;
	}
	
	@Override
	public String toString() {
		return "Radio " + (on ? "an" : "aus") + 
				"; Lautstärke " + volume + 
				"; Frequenz " + frequency + " MHz";
	}

	public static void main(String[] args) {

		Radio radio = new Radio(true, 5, 90.0);
		System.out.println(radio.toString());
		
		for (int i = 0; i < 3; i++) {
			radio.incVolume();
			radio.turnOff();
		}
		System.out.println(radio.toString());
		
		radio.setFrequency(120.0);
		
		System.out.println(radio.toString());
		
	}

}