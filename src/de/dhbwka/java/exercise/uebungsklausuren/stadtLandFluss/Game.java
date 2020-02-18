package de.dhbwka.java.exercise.uebungsklausuren.stadtLandFluss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class Game {
	
	private final Random rdm = new Random();

	private char letter;
	private int time;
	private int remainingSeconds;
	private boolean running;
	
	private List<ColumnType> columns;
	private int min;
	private int max;
	
	private List<Sheet> sheets;
	private Map<Player, String[]> results;
	private List<String> validWords;
	
	public Game(int min, int max, int time) {
		
		this.min = (min >= 3 ? min : 3);
		this.max = (max >= min ? max : min);
		this.time = time;
		this.remainingSeconds = time;
		this.sheets = new ArrayList<>();
		this.validWords = new ArrayList<>();
		this.letter = createFirstCharacter();
		this.columns = createColumns();
		
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("./files/stadtLandFluss/validwords.txt")))) {
			while (reader.ready()) {
				String line = reader.readLine();
				validWords.add(line);
			}
		} catch (IOException e) {
			System.err.println("File not found :(");
			e.printStackTrace();
		}
		
	}
	
	private char createFirstCharacter() {
		return (char) ('A' + this.rdm.nextInt(26));
	}
	
	private List<ColumnType> createColumns() {
		List<ColumnType> columns = new ArrayList<>();
		columns.add(ColumnType.CITY);
		columns.add(ColumnType.COUNTRY);
		columns.add(ColumnType.RIVER);
		
		int originalSize = columns.size();
		int additionalSize = this.rdm.nextInt(max-min+1);
		ColumnType[] values = ColumnType.values();
		while (columns.size() < originalSize + additionalSize) {
			ColumnType type = values[rdm.nextInt(values.length)];
			if(!columns.contains(type)) {
				columns.add(type);
			}
		}
		
		return columns;
	}

	public void register(Sheet sheet) {
		if(!this.sheets.contains(sheet))
			this.sheets.add(sheet);
	}
	
	public void startGame() {
		if(!running) {
			this.running = true;
			this.letter = createFirstCharacter();
			this.columns = createColumns();
			for (int i = 0; i < sheets.size(); i++) {
				sheets.get(i).updateUI();
			}
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (Game.this.running) {
						if(remainingSeconds == 0) {
							Game.this.stopGame();
						}
						else {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							remainingSeconds--;
							for (int i = 0; i < sheets.size(); i++) {
								sheets.get(i).setTime(remainingSeconds);
							}
						}
					}
					Game.this.remainingSeconds = Game.this.time;
					for (int i = 0; i < sheets.size(); i++) {
						sheets.get(i).setTime(0);
					}
				}
			}).start();
		}
	}
	
	public void stopGame() {
		this.running = false;
		for (int i = 0; i < sheets.size(); i++) {
			sheets.get(i).stop();
		}
		getResults();
	}
	
	private void getResults() {
		results = new HashMap<>();
		for (int i = 0; i < sheets.size(); i++) {
			results.put(sheets.get(i).getPlayer(), sheets.get(i).getResult());
		}
		for (int i = 0; i < sheets.size(); i++) {
			evaluate(sheets.get(i));
		}
	}
	
	public void evaluate(Sheet sheet) {
		
		String[] result = sheet.getResult();
		int[] points = new int[columns.size()];
		
		for (int i = 0; i < columns.size(); i++) {
			if(!isCorrect(result[i], columns.get(i))) {
				points[i] = 0;
				continue;
			}
			boolean only = true;
			for (int j = 0; j < sheets.size(); j++) {
				if(!sheets.get(j).equals(sheet)) {
					if(!this.results.get(this.sheets.get(j).getPlayer())[i].equals("")) {
						only = false;
						break;
					}
				}
			}
			if(only) {
				points[i] = 20;
				continue;
			}
			for (int j = 0; j < sheets.size(); j++) {
				if(!sheets.get(j).equals(sheet)) {
					if(result[i].equals(this.results.get(this.sheets.get(j).getPlayer())[i])) {
						points[i] = 5;
					}
					else {
						points[i] = 10;
					}
				}
			}
		}
		
		sheet.receiveResult(points);
		
	}
	
	private boolean isCorrect(String term, ColumnType column) {
		if(term.length() > 1 && new String(term.charAt(0) + "").equalsIgnoreCase(letter + "")) {
			if(validWords.contains(term)) {
				return true;
			}
			else {
				int confirm = JOptionPane.showConfirmDialog(null, "Ist " + term + " korrekt für die Kategorie " + column.getTitle(), "Option auswählen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
				if(confirm == JOptionPane.YES_OPTION) {
					validWords.add(term);
					try (Writer writer = new FileWriter(new File("./files/stadtLandFluss/validwords.txt"), true)) {
						writer.write(term + System.lineSeparator());
					} catch (IOException e) {
						System.err.println("Error while trying to write into file");
						e.printStackTrace();
					}
					
					return true;
				}
				else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
	
	public char getLetter() {
		return letter;
	}

	public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public boolean isRunning() {
		return running;
	}

	public List<ColumnType> getColumns() {
		return columns;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	public int getTime() {
		return remainingSeconds;
	}

}