package de.dhbwka.java.exercise.ui.event;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGuess implements ActionListener{

	private JFrame frame;
	private JTextField player;
	private JTextField number;
	private JTextField result;
	
	private JButton game;
	private JButton ok;
	private JButton best;
	private JButton exit;
	
	private final File file = new File("./files/numberGuess/highscores.txt");
	
	private int random;
	private int guess;
	private int rounds;
	
	public NumberGuess() {
		frame = new JFrame("Number Guessing Game");
		frame.setLayout(new GridLayout(4,1,5,5));
		JPanel panel = new JPanel();
		
		player = new JTextField(10);
		panel.add(new JLabel("Player Name"));
		panel.add(player);
		frame.add(panel);
		
		panel = new JPanel();
		panel.add(new JLabel("Enter number between 1 and 1000"));
		number = new JTextField(10);
		number.setActionCommand("OK");
		number.addActionListener(this);
		panel.add(number);
		frame.add(panel);
		
		initButtons();
		
		panel = new JPanel();
		result = new JTextField(40);
		panel.add(result);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	
		reset();
		
	}
	
	private void initButtons() {
		
		JPanel panel = new JPanel();
		
		game = new JButton("New Game");
		game.setActionCommand("New Game");
		game.addActionListener(this);
		panel.add(game);
		
		ok = new JButton("OK");
		ok.setActionCommand("OK");
		ok.addActionListener(this);
		panel.add(ok);
		
		best = new JButton("Best Player");
		best.setActionCommand("Best Player");
		best.addActionListener(this);
		panel.add(best);
		
		exit = new JButton("Exit");
		exit.setActionCommand("Exit");
		exit.addActionListener(this);
		panel.add(exit);
		
		frame.add(panel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "New Game":	reset();
								break;
			case "OK":			if(random == guess) {
									JOptionPane.showMessageDialog(frame, "Du hast schon das richtige Ergebnis!");
									Toolkit.getDefaultToolkit().beep();
								}
								else
									startRound();
								break;			
			case "Best Player":	try(BufferedReader reader = new BufferedReader(new FileReader(file))){
									int min = Integer.MAX_VALUE;
									String player = "";
									while(reader.ready()) {
										String[] current = reader.readLine().split(": ");
										if(current[0].equalsIgnoreCase(""))
											return;
										else if(Integer.parseInt(current[1]) < min) {
											min = Integer.parseInt(current[1]);
											player = current[0];
										}
									}
									result.setText(player + ": " + min);
								} 
								catch (IOException ex) {
									JOptionPane.showMessageDialog(frame, "Die Highscore Datei wurde nicht gefunden!");
									Toolkit.getDefaultToolkit().beep();
								}
								break;	
			case "Exit": 		System.exit(0);
						 		break;
		}
	}
	
	public void startRound() {
		
		try{
			guess = Integer.parseInt(number.getText());
			if(guess < 1 || guess > 1000) {
				JOptionPane.showMessageDialog(frame, "Die Reichweite beträgt nur 1 - 1000!");
				Toolkit.getDefaultToolkit().beep();
			}
			else if(guess > random)
				result.setText("Attempt #" + rounds++ + ": " + guess + " => too big!");
			else if(guess < random)
				result.setText("Attempt #" + rounds++ + ": " + guess + " => too small!");
			else {
				result.setText("Attempt #" + rounds + ": " + guess + " => Correct!");
				
				Writer writer = new FileWriter(file,true);
				writer.write(player.getText() + ": " + rounds + System.lineSeparator());
				writer.close();
			}
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(frame, "Bitte nur Zahlen in das Nummernfeld eintragen!");
			Toolkit.getDefaultToolkit().beep();
		} 
		catch (IOException e) {
			System.err.println("Datei nicht gefunden!");
			Toolkit.getDefaultToolkit().beep();
		}
	}
	
	public void reset() {
		random = new Random().nextInt(1000)+1;
		rounds = 1;
		guess = 0;
		
		player.setText("");
		number.setText("");
		result.setText("New Game!");
	}
	
	public static void main(String[] args) {

		new NumberGuess();
		
	}

}