package de.dhbwka.java.exercise.uebungsklausuren.stadtLandFluss;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Sheet {

	private Player player;
	private Game game;
	
	
	private JFrame frame;
	private JLabel points;
	private JLabel time;
	private JLabel letter;
	private JPanel centerPanel;
	private JButton start;
	private JButton stop;
	private List<JTextField> fields;
	private List<JLabel> labels;
	
	public Sheet(Player player, Game game) {
		this.player = player;
		this.game = game;
		this.fields = new ArrayList<>();
		this.labels = new ArrayList<>();
		
		this.frame = new JFrame(player.getName());
		
		createGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
	}

	private void createGUI() {
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new GridLayout(3, 2));
		this.points = new JLabel("");
		this.time = new JLabel("");
		this.letter = new JLabel("");
		
		panelTop.add(new JLabel("Punkte"));
		panelTop.add(this.points);
		panelTop.add(new JLabel("Verbleibende Sekunden"));
		panelTop.add(this.time);
		panelTop.add(new JLabel("Anfangsbuchstabe"));
		panelTop.add(this.letter);
		
		this.frame.add(panelTop, BorderLayout.NORTH);
		
		this.centerPanel = new JPanel();
		centerPanel.add(new JLabel("Kein Spiel aktiv"));
		
		this.frame.add(centerPanel, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		this.start = new JButton("Start");
		this.start.addActionListener(e -> {
			Sheet.this.game.startGame();
		});
		this.stop = new JButton("Stop");
		this.stop.addActionListener(e -> {
			Sheet.this.game.stopGame();
		});
		this.stop.setEnabled(false);
		panelSouth.add(this.start);
		panelSouth.add(this.stop);
		
		this.frame.add(panelSouth, BorderLayout.SOUTH);
		
	}
	
	public void stop() {
		
		for (int i = 0; i < fields.size(); i++) {
			fields.get(i).setEnabled(false);
		}
		this.time.setText("0");
		this.start.setEnabled(true);
		this.stop.setEnabled(false);
		
	}
	
	public String[] getResult() {
		
		String[] results = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			results[i] = fields.get(i).getText();
		}
		return results;
		
	}
	
	public void receiveResult(int[] result) {
		int sum = 0;
		for (int i = 0; i < labels.size(); i++) {
			this.labels.get(i).setText(result[i] + "");
			sum += result[i];
		}
		this.player.addPoints(sum);
		this.points.setText(this.player.getPoints() + "");		
	}
	
	public void updateUI() {
		List<ColumnType> columns = game.getColumns();
		this.fields = new ArrayList<>();
		this.labels = new ArrayList<>();
		this.centerPanel.removeAll();
		this.centerPanel.setLayout(new GridLayout(columns.size(), 3));
		for (int i = 0; i < columns.size(); i++) {
			this.centerPanel.add(new JLabel(columns.get(i).getTitle()));
			
			JTextField textfield = new JTextField(30);
			this.fields.add(textfield);
			this.centerPanel.add(textfield);
			
			JLabel point = new JLabel("0");
			this.labels.add(point);
			this.centerPanel.add(point);
		}
		this.time.setText(this.game.getTime() + "");
		this.letter.setText(this.game.getLetter() + "");
		this.start.setEnabled(false);
		this.stop.setEnabled(true);
	}
	
	public void setTime(int time) {
		this.time.setText(time + "");
	}

	public Player getPlayer() {
		return player;
	}

	public Game getGame() {
		return game;
	}
	
}