package de.dhbwka.java.exercise.uebungsklausuren.jBay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;

public class BieterTerminal {

	private Bieter bieter;
	private Auktionshaus auktionshaus;
	
	private JFrame frame;
	private JLabel timeLabel;
	private JPanel panelCenter;
	
	public BieterTerminal(Bieter bieter, Auktionshaus auktionshaus) {
		this.bieter = bieter;
		this.auktionshaus = auktionshaus;
		
		this.auktionshaus.register(this);
		
		
		this.frame = new JFrame(this.bieter.getFullName() + "'s Terminal");
		
		this.buildGUI();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					BieterTerminal.this.timeLabel.setText(Calendar.getInstance().getTime().toString());
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
		this.frame.setSize(1280, 120);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
		
	}

	private void buildGUI() {
		
		this.timeLabel = new JLabel(Calendar.getInstance().getTime().toString(), JLabel.CENTER);
		this.frame.add(this.timeLabel, BorderLayout.NORTH);
		
		this.panelCenter = new JPanel();
		this.updateTerminal();
		this.frame.add(this.panelCenter, BorderLayout.CENTER);
		
	}
	
	public void updateTerminal() {
		List<Auktion> auktionen = this.auktionshaus.getAuktionen();
		this.panelCenter.removeAll();
		this.panelCenter.setLayout(new GridLayout(auktionen.size(),4));
		
		for (Auktion auktion: this.auktionshaus.getAuktionen()) {
			this.panelCenter.add(new JLabel(auktion.getWare().getTitle()));
			this.panelCenter.add(new JLabel(auktion.getCurrentPrice() + ""));
			this.panelCenter.add(new JLabel((auktion.getHoehstgebot() == null) ? "---" : auktion.getHoehstgebot().getBieter().getFullName()));
			this.panelCenter.add(new JLabel(auktion.getTimeEnds().getTime().toString()));
			JButton button = new JButton("Gebot");
			
			// Oder Über eigene Klasse, die von JButton erbt und eine Auktion speichert
			// Und später AuktionButton auktion = ((AuktionsButtons= e.getSource()).getAuktion();
			button.addActionListener(e -> {
				if(Calendar.getInstance().after(auktion.getTimeEnds())) {
					JOptionPane.showMessageDialog(this.frame, "Die Auktion ist leider schon vorbei", "Meldung", JOptionPane.WARNING_MESSAGE);
				} else {
					String input = JOptionPane.showInputDialog(this.frame, "Bitte neues Gebot eingeben" + System.lineSeparator() + "Mindestens " + ((double) (auktion.getCurrentPrice() + Auktion.increment)) + " Euro", "Eingabe", JOptionPane.QUESTION_MESSAGE);
					double amount = 0.0D;
					boolean success = false;
					try {
						amount = Double.parseDouble(input);
					} catch (Exception ex) {
						amount = 0.0D;
					} finally {
						success = auktion.gebotAbgeben(new Gebot(amount, this.bieter));
					}
					
					if(success) {
						JOptionPane.showMessageDialog(this.frame, "Sie sind Höchstbietender!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this.frame, "Gebot zu gering!", "Meldung", JOptionPane.INFORMATION_MESSAGE);
					}
					this.auktionshaus.updateTerminals();
				}
			});
			this.panelCenter.add(button);
		}
	}

	public Bieter getBieter() {
		return bieter;
	}

	public Auktionshaus getAuktionshaus() {
		return auktionshaus;
	}
	
}