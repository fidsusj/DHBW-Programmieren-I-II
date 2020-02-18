package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

import java.awt.Color;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SeatButton extends JButton{

	private Sitz seat;
	
	public SeatButton(Sitz seat) {
		this("", seat);
	}
	
	public SeatButton(String text, Sitz seat) {
		super(text);
		this.seat = seat;
		this.setColor();
		this.setEnabled(!this.seat.getStatus().equals(Buchungsstatus.GEBUCHT));
	}
	
	private void setColor() {
		switch (this.seat.getStatus()) {
		case FREI:
			this.setBackground(Color.WHITE);
			break;
		case PENDING:
			this.setBackground(Color.RED);
			break;
		case GEBUCHT:
			this.setBackground(Color.WHITE);
			break;
		}
	}
	
	public Sitz getSeat() {
		return this.seat;
	}

	public void setStatus(Buchungsstatus status) {
		this.seat.setStatus(status);
		this.setEnabled(!this.seat.getStatus().equals(Buchungsstatus.GEBUCHT));
		this.setColor();
	}
	
}