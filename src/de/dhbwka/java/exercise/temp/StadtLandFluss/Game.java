package de.dhbwka.java.exercise.temp.StadtLandFluss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class Game {

	private final Random rdm = new Random();
	
	private char letter;
	private List<ColumnType> columns;
	private List<Sheet> sheets;
	private int time;
	private int remainingSeconds;
	private boolean running;
	
	private int min;
	private int max;
	
	private Map<Sheet, List<String>> results;
	private List<String> validwords;
 	
	public Game(int min, int max, int seconds) {
		this.columns = new ArrayList<>();
		this.sheets = new ArrayList<>();
		this.validwords = new ArrayList<>();
		loadValidWords();
		this.time = seconds;
		
		this.min = min <= 3 ? min : 3;
		this.max = max >= min ? max : min;
		
		this.results = new HashMap<>();
	}
	
	private char createFirstCharacter() {
		return (char) (this.rdm.nextInt(26) + ((int)'A'));
	}
	
	private List<ColumnType> createColumns(){
		List<ColumnType> result = new ArrayList<>();
		List<ColumnType> columns = Arrays.asList(ColumnType.values());
		result.add(ColumnType.CITY);
		result.add(ColumnType.COUNTRY);
		result.add(ColumnType.RIVER);
		int additionalColumns = this.rdm.nextInt(max-min+1);
		for (int i = 0; i < additionalColumns; i++) {
			Collections.shuffle(columns);
			ColumnType column = columns.get(0);
			if(!result.contains(column))
				result.add(column);
		}
		return result;
	}
	
	private void loadValidWords() {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("./files/stadtLandFluss/validwords.txt")))) {
			while (reader.ready()) {
				String line = reader.readLine();
				this.validwords.add(line);
			}
		} catch (IOException e) {
			System.err.println("Something went wrong while reading valid words");
			e.printStackTrace();
		}
	}
	
	public void register(Sheet sheet) {
		if(!this.sheets.contains(sheet)) {
			this.sheets.add(sheet);
		}
	}
	
	public void startGame() {
		if(!this.running) {
			this.letter = createFirstCharacter();
			this.columns = createColumns();
			this.running = true;
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					Game.this.remainingSeconds = Game.this.time;
					for (int i = Game.this.remainingSeconds; i > 0 && Game.this.running; i--) {
						for (Sheet sheet : Game.this.sheets) {
							sheet.updateTime();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Game.this.remainingSeconds--;
					}
					if(Game.this.running) {
						Game.this.stopGame();
					}
				}
			}).start();
			
			for (int i = 0; i < this.sheets.size(); i++) {
				this.sheets.get(i).updateUI();
			}
		}
	}
	
	public void stopGame() {
		this.running = false;
		for (int i = 0; i < this.sheets.size(); i++) {
			this.sheets.get(i).gameIsOver();
		}
		evaluateResults();
	}
	
	private void evaluateResults() {
		for (int i = 0; i < this.sheets.size(); i++) {
			List<String> result = this.sheets.get(i).getResults();
			this.results.put(sheets.get(i), result);
		}
		
		for (int i = 0; i < this.sheets.size(); i++) {
			List<Integer> points = getResultForSheet(sheets.get(i));
			sheets.get(i).receiveResult(points);
		}
	}
	
	private List<Integer> getResultForSheet(Sheet s) {
		List<Integer> points = new ArrayList<>();
		List<String> result = this.results.get(s);
		for (int i = 0; i < result.size(); i++) {
			if(!correctTerm(result.get(i), s.getGame().getColumns().get(i))) {
				points.add(0);
				continue;
			} else {
				boolean only = true;
				boolean found = false;
				for (int j = 0; j < this.sheets.size(); j++) {
					if(this.sheets.get(j).equals(s)) {
						continue;
					} else {
						String peerResult = this.results.get(sheets.get(j)).get(i);
						if(correctTerm(peerResult, s.getGame().getColumns().get(i))){
							only = false;
							if(peerResult.equals(result.get(i))) {
								found = true;
								break;
							} else {
								found = false;
							}
						}
					}
				}
				if(!only && found) {
					points.add(5);
				} else if (!only && !found) {
					points.add(10);
				} else {
					points.add(20);
				}
			}
		}
		return points;
	}
	
	private boolean correctTerm(String term, ColumnType col) {
		if(term.length() >= 3 && new String(term.charAt(0) + "").equalsIgnoreCase(this.letter + "")) {
			if(!this.validwords.contains(term)) {
				int ok =  JOptionPane.showConfirmDialog(null, "Ist " + term + " korrekt für Kategorie '" + col.getTitle() + "'", "Option auswählen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			
				if(ok == JOptionPane.OK_OPTION) {
					this.validwords.add(term);
					try (Writer writer = new FileWriter(new File("./files/stadtLandFluss/validwords.txt"), true)) {
						writer.write(term + System.lineSeparator());
					} catch (IOException e) {
						System.err.println("Error while trying to write into file");
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	public char getLetter() {
		return letter;
	}

	public List<ColumnType> getColumns() {
		return columns;
	}

	public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public boolean isRunning() {
		return running;
	}
	
}