package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class TipperTerminal {

	private Tipper tipper;
	private Totalisator totalisator;
	
	private JFrame frame;
	private List<JTextField> textfields;
	
	public TipperTerminal(Tipper tipper, Totalisator totalisator) {
		this.tipper = tipper;
		this.totalisator = totalisator;
		this.textfields = new ArrayList<>();
		
		this.frame = new JFrame(this.tipper.getVorname() + " " + this.tipper.getNachname() + "'s Terminal");
		
		buildGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
	}

	private void buildGUI() {
		List<Paarung> paarungen = this.totalisator.getPaarungen();
		
		
		this.frame.add(new JLabel(this.totalisator.getName(), JLabel.CENTER), BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel(new GridLayout(paarungen.size(),3));
		for (Paarung paarung : paarungen) {
			panelCenter.add(new JLabel(paarung.getHeim().getName()));
			panelCenter.add(new JLabel(": " + paarung.getGast().getName()));
			JTextField field = new JTextField(20);
			field.addActionListener(e -> {
				if(correctInput(field.getText())) {
					int index = this.textfields.indexOf(field);
					this.textfields.get(this.textfields.size() > (index + 1) ? (index + 1) : 0).requestFocus();
				} else {
					JOptionPane.showMessageDialog(this.frame, "Bitte Eingabe überprüfen!", "Meldung", JOptionPane.WARNING_MESSAGE);
				}
			});
			this.textfields.add(field);
			panelCenter.add(field);
		}
		this.frame.add(panelCenter, BorderLayout.CENTER);
		
		JButton button = new JButton("Tipp abschließen");
		button.addActionListener(e -> {
			boolean correct = true;
			for (JTextField field : this.textfields) {
				if(!this.correctInput(field.getText())){
					correct = false;
					field.requestFocus();
					break;
				}
			}
			if(correct) {
				JOptionPane.showMessageDialog(this.frame, "Eingabe korrekt. Warten Sie auf das Ergebnis", "Meldung", JOptionPane.INFORMATION_MESSAGE);
				for (int i = 0; i < paarungen.size(); i++) {
					this.tipper.addTipp(new Tipp(paarungen.get(i), Integer.parseInt(this.textfields.get(i).getText())));
				}
				this.totalisator.notifyCompleteTipp(this.tipper);
				button.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(this.frame, "Bitte Eingabe überprüfen!", "Meldung", JOptionPane.WARNING_MESSAGE);
			}
		});
		this.frame.add(button, BorderLayout.SOUTH);
		
	}
	
	public void receiveResult(int sum) {
		JOptionPane.showMessageDialog(this.frame, this.tipper.getVorname() + " " + this.tipper.getNachname() + " hat " + sum + " richtige Tipps", "Meldung", JOptionPane.INFORMATION_MESSAGE);
		List<Paarung> paarungen = this.totalisator.getPaarungen();
		for(int i = 0; i < paarungen.size(); i++) {
			this.textfields.get(i).setText(this.textfields.get(i).getText() + String.format(" (Ergebnis: %s)", paarungen.get(i).getSpielstand()));
		}
	}
	
	private boolean correctInput(String input) {
		return Arrays.asList(new String[] {"0","1","2"}).contains(input);
	}

	public Tipper getTipper() {
		return tipper;
	}

	public Totalisator getTotalisator() {
		return totalisator;
	}
	
}