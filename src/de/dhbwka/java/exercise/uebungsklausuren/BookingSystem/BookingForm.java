package de.dhbwka.java.exercise.uebungsklausuren.BookingSystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;

public class BookingForm {

	private Flug flight;
	
	private JFrame frame;
	private JLabel seatsLabel;
	private JLabel timeLabel;
	private List<SeatButton> buttons;

	public BookingForm(Flug flight) {
		this.flight = flight;
		
		
		this.frame = new JFrame("Buchung für Flug " + this.flight.getName());
		
		buildGUI();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					BookingForm.this.timeLabel.setText(Calendar.getInstance().getTime().toString());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
	}

	private void buildGUI() {
		
		JPanel panelNorth = new JPanel(new GridLayout(5,2));
		panelNorth.add(new JLabel("Fluggesellschaft"));
		panelNorth.add(new JLabel(this.flight.getAirline().getName() + " (" + this.flight.getAirline().getCode() + ")"));
		panelNorth.add(new JLabel("Abflugflughafen"));
		panelNorth.add(new JLabel(this.flight.getDestinationFrom().getName() + " (" + this.flight.getDestinationFrom().getCode() + ")"));
		panelNorth.add(new JLabel("Landeflughafen"));
		panelNorth.add(new JLabel(this.flight.getDestinationTo().getName() + " (" + this.flight.getDestinationTo().getCode() + ")"));
		this.seatsLabel = new JLabel(this.flight.freiePlaetze() + "");
		this.timeLabel = new JLabel(Calendar.getInstance().getTime().toString());
		panelNorth.add(new JLabel("Freie Plätze"));
		panelNorth.add(this.seatsLabel);
		panelNorth.add(new JLabel("Zeit"));
		panelNorth.add(this.timeLabel);
		this.frame.add(panelNorth, BorderLayout.NORTH);
		
		
		JPanel panelCenter = new JPanel(new GridLayout(this.flight.getRows(), this.flight.getSeatsPerRow()));
		this.buttons = new ArrayList<>();
		for (Sitz seat : this.flight.getSeats()) {
			SeatButton button = new SeatButton((seat.getRow()+1) + "" + seat.getPosition(), seat);
			this.buttons.add(button);
			button.addActionListener(e -> {
				if(button.getSeat().getStatus().equals(Buchungsstatus.FREI)) {
					button.setStatus(Buchungsstatus.PENDING);
				} else {
					button.setStatus(Buchungsstatus.FREI);
				}
			});
			panelCenter.add(button);
		}
		this.frame.add(panelCenter, BorderLayout.CENTER);
		
		
		JPanel panelSouth = new JPanel(new GridLayout(1,2));
		JButton book = new JButton("Buchen");
		book.addActionListener(e -> {
			for (SeatButton seatButton : buttons) {
				if(seatButton.getSeat().getStatus().equals(Buchungsstatus.PENDING)) {
					seatButton.setStatus(Buchungsstatus.GEBUCHT);
					log(seatButton);
				}
			}
			this.seatsLabel.setText(this.flight.freiePlaetze() + "");
		});
		
		JButton cancel = new JButton("Verwerfen");
		cancel.addActionListener(e -> {
			for (SeatButton seatButton : buttons) {
				if(seatButton.getSeat().getStatus().equals(Buchungsstatus.PENDING))
					seatButton.setStatus(Buchungsstatus.FREI);
			}
		});
		panelSouth.add(book, BorderLayout.WEST);
		panelSouth.add(cancel, BorderLayout.EAST);
		this.frame.add(panelSouth, BorderLayout.SOUTH);
			
	}
	
	private void log(SeatButton button) {
		
		File fileDir = new File("./files/bookingSystems");
		fileDir.mkdirs();
		File file = new File(fileDir, "bookingSystems.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("Error while trying to create new file");
		}

		try (Writer writer = new FileWriter(file, true)) {
			Sitz seat = button.getSeat();
			writer.write(String.format("Gebucht: %s (%s)", seat.getRow() + "" + seat.getPosition(), Calendar.getInstance().getTime().toString()) + System.lineSeparator());
		} catch (IOException e) {
			System.err.println("Error while trying to write into file");
			e.printStackTrace();
		}
	}
	
}