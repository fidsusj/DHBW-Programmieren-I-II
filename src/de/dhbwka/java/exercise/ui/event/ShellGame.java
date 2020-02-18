package de.dhbwka.java.exercise.ui.event;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShellGame{

	private JFrame frame;
	private JTextField name;
	private JButton[] guess;
	private JButton stats, exit;
	private JTextField result;
	
	private int attempts;
	private int numberActual;
	
	public ShellGame() {
		frame = new JFrame("Shell Game");
		frame.setLayout(new GridLayout(4, 1));
		
		
		JPanel panel = new JPanel();
		name = new JTextField(20);
		panel.add(new JLabel("Player Name"));
		panel.add(name);
		frame.add(panel);
		
		panel = new JPanel();
		guess = new JButton[3];
		for (int i = 0; i < guess.length; i++) {
			guess[i] = new JButton("Shell " + (i+1));
			guess[i].addActionListener(e -> handleInput(Character.getNumericValue(new StringBuilder(((JButton)e.getSource()).getText()).reverse().charAt(0))));
			panel.add(guess[i]);
		}
		frame.add(panel);
		
		panel = new JPanel();
		stats = new JButton("Statistics");
		stats.addActionListener(e -> calcStats());
		exit = new JButton("Exit");
		exit.addActionListener(e -> System.exit(0));
		panel.add(stats);
		panel.add(exit);
		frame.add(panel);
		
		panel = new JPanel();
		result = new JTextField(30);
		panel.add(result);
		frame.add(panel);
		
		Random rdm = new Random();
		numberActual = rdm.nextInt(3) + 1;
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
		
		
	}
	
	private void calcStats() {

		String user = name.getText();
		double sumattempts = 0;
		double runs = 0;
		
		try(BufferedReader reader = new BufferedReader(new FileReader("./files/shellgame/stats.txt"))){
			
			while(reader.ready()) {
				String[] line = reader.readLine().split(": ");
				if(line[0].equals(user)) {
					sumattempts += Double.valueOf(line[1]).doubleValue();
					runs++;
				}
			}
			
		} catch (IOException e) {			
			JOptionPane.showMessageDialog(frame, "Bei dem Einlesen der Statistiken ist etwas schief gelaufen");
		}
		
		double res= sumattempts/runs;
		
		if(Double.isNaN(res))
			JOptionPane.showMessageDialog(frame, "Der Spieler existiert nicht");
		else
			result.setText("Success after " + res + " attempts!");
		
	}

	private void handleInput(int pick) {
		
		Random rdm = new Random();
		
		if(name.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "Bitte zunächst einen Namen eingeben");
			return;
		}
		
		attempts++;
		if(pick != numberActual) {
			result.setText("Attempt " + attempts + ": Lose --> Ball was below shell " + numberActual);
			numberActual = rdm.nextInt(3) + 1;
		}
		else {
			result.setText("Attempt " + attempts + ": Win --> Ball was below shell " + numberActual);
			
			try(PrintWriter writer = new PrintWriter(new FileWriter("./files/shellgame/stats.txt", true))){		
				writer.println(name.getText() + ": " + attempts);	
			} catch (IOException e) {			
				JOptionPane.showMessageDialog(frame, "Bitte zunächst einen Namen eingeben");
			}
			
			attempts = 0;
			numberActual = rdm.nextInt(3) + 1;
		}
		
	}
	
	public static void main(String[] args) {

		new ShellGame();

	}

}