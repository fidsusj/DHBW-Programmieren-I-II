package de.dhbwka.java.exercise.temp.StadtLandFluss;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Sheet {

	private Player player;
	private Game game;
	
	private JFrame frame;
	private JLabel pointsLabel;
	private JLabel timeLabel;
	private JLabel letterLabel;
	private JPanel centerPanel;
	private JButton start;
	private JButton stop;
	private List<JTextField> fields;
	private List<JLabel> labels;
	
	public Sheet(Player player, Game game) {
		this.player = player;
		this.game = game;
		
		
		this.frame = new JFrame(this.player.getName());
		
		createGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
	}

	private void createGUI() {
		
		//Norden
		JPanel panelNorth = new JPanel(new GridLayout(3,2));
		panelNorth.add(new JLabel("Punkte"));
		this.pointsLabel = new JLabel(this.player.getPoints() + "");
		panelNorth.add(this.pointsLabel);
		panelNorth.add(new JLabel("Verbleibende Sekunden"));
		this.timeLabel = new JLabel();
		panelNorth.add(this.timeLabel);
		panelNorth.add(new JLabel("Anfangsbuchstabe"));
		this.letterLabel = new JLabel();
		panelNorth.add(this.letterLabel);
		this.frame.add(panelNorth, BorderLayout.NORTH);
		
		//Zentrum
		this.centerPanel = new JPanel();
		this.centerPanel.add(new JLabel("Kein Spiel aktiv", JLabel.CENTER));
		this.frame.add(centerPanel, BorderLayout.CENTER);
		
		//Süden
		JPanel panelSouth = new JPanel(new FlowLayout());
		this.start = new JButton("Start");
		this.start.addActionListener(e -> {
			this.game.startGame();
		});
		this.stop = new JButton("Stop");
		this.stop.addActionListener(e -> {
			this.game.stopGame();
		});
		this.stop.setEnabled(false);
		panelSouth.add(this.start);
		panelSouth.add(this.stop);
		this.frame.add(panelSouth, BorderLayout.SOUTH);
		
	}
	
	public void updateUI() {
		this.pointsLabel.setText(this.player.getPoints() + "");
		this.letterLabel.setText(this.game.getLetter() + "");
		
		List<ColumnType> columns = this.game.getColumns();
		this.centerPanel.removeAll();
		this.fields = new ArrayList<>();
		this.labels= new ArrayList<>();
		this.centerPanel.setLayout(new GridLayout(columns.size(), 3));
		for (ColumnType columnType : columns) {
			this.centerPanel.add(new JLabel(columnType.getTitle()));
			
			JTextField field = new JTextField();
			this.fields.add(field);
			this.centerPanel.add(field);
			
			JLabel label = new JLabel();
			this.labels.add(label);
			this.centerPanel.add(label);
		}
		
		this.start.setEnabled(false);
		this.stop.setEnabled(true);
	}
	
	public void updateTime() {
		this.timeLabel.setText(this.game.getRemainingSeconds() + "");
	}
	
	public void gameIsOver() {
		for (JTextField jTextField : fields) {
			jTextField.setEnabled(false);
		}
		this.timeLabel.setText("0");
		this.start.setEnabled(true);
		this.stop.setEnabled(false);
	}
	
	public List<String> getResults() {
		List<String> results = new ArrayList<>();
		for (JTextField jTextField : fields) {
			results.add(jTextField.getText());
		}
		return results;
	}
	
	public void receiveResult(List<Integer> points) {
		int sum = 0;
		for (int i = 0; i < points.size(); i++) {
			this.labels.get(i).setText(points.get(i) + "");
			sum += points.get(i);
		}
		this.player.addPoints(sum);
	}

	public Player getPlayer() {
		return player;
	}

	public Game getGame() {
		return game;
	}

}