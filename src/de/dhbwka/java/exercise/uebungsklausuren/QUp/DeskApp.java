package de.dhbwka.java.exercise.uebungsklausuren.QUp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;

public class DeskApp implements QueueObserver{

	private WaitingQueue queue;
	private Call call;
	
	private int number;
	private int room;
	private String name;
	
	private JFrame frame;
	private JLabel labelTop;
	private JLabel labelCenter;
	private JButton start;
	private JButton next;
	
	public DeskApp(WaitingQueue queue, int number, int room, String name) {
		this.queue = queue;
		this.queue.addQueueObserver(this);
		
		this.number = number;
		this.room = room;
		this.name = name;	
		
		this.frame = new JFrame("Sachbearbeitung " + this.number);
		
		buildUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
	}

	private void buildUI() {
		
		this.labelTop = new JLabel();
		this.frame.add(this.labelTop, BorderLayout.NORTH);
		
		this.labelCenter = new JLabel();
		this.frame.add(this.labelCenter, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel(new FlowLayout());
		this.start = new JButton("Bearbeitung beginnen");
		this.start.setActionCommand("1");
		this.start.addActionListener(e -> {
			switch(e.getActionCommand()) {
				case "1":  this.start.setText("Bearbeitung abgeschlossen");
						   this.queue.confirmCall(this.call);
						   ((JButton) e.getSource()).setActionCommand("2");
						   this.queue.updateObservers();
						   break;
				case "2":  this.start.setText("Bearbeitung beginnen");
						   this.start.setActionCommand("1");
						   this.start.setEnabled(false);
						   this.next.setEnabled(true);
						   this.labelCenter.setText("---");
						   break;
			}
		});
		this.start.setEnabled(false);
		this.next = new JButton("Nächster Kunde");
		this.next.addActionListener(e -> {
			this.call = this.queue.getNextCall(this);
			if(this.call == null) {
				JOptionPane.showMessageDialog(this.frame, "Keine wartenden Kunden!", "Meldung", JOptionPane.WARNING_MESSAGE);
			} else {
				this.start.setEnabled(true);
				this.next.setEnabled(false);
				this.queue.updateObservers();
			}
		});
		panelSouth.add(this.start);
		panelSouth.add(this.next);
		this.frame.add(panelSouth, BorderLayout.SOUTH);
		
		updateView();

	}

	public WaitingQueue getQueue() {
		return queue;
	}

	public int getNumber() {
		return number;
	}

	public int getRoom() {
		return room;
	}

	public String getName() {
		return name;
	}

	public void updateView() {
		this.labelTop.setText("Noch " + this.queue.getWaitingClients() + " Kunden im Wartebereich.");
		this.labelCenter.setText(this.call == null ? "---" : "Kunde Nummer " + this.call.getNumber());
	}

}