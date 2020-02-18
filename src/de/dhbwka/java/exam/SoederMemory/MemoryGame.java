package de.dhbwka.java.exam.SoederMemory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import de.dhbwka.java.exam.SoederMemory.MemoryImages.MemoryImage;

public class MemoryGame {

	private int rows;
	private int columns;
	private List<Player> player;
	private List<MemoryImage> images;
	private Player currentPlayer;
	
	private int availableCards;
	private boolean isFinished;
	private int counter;
	
	public MemoryGame(List<Player> player, List<MemoryImage> images, int rows, int columns) throws MemoryException {
		
		if(player.size() < 2) {
			throw new MemoryException("At least two players required");
		}
		
		int amountCards = (rows * columns) % 2 == 1 ? columns * rows -1 : columns * rows;
		if(images.size() < amountCards) {
			throw new MemoryException("Too few images available");
		}
		
		this.images = new ArrayList<>();
		
		this.player = player;
		for (int i = 0; i < amountCards / 2; i++) {
			Collections.shuffle(images);
			MemoryImage image1 = images.get(0);
			MemoryImage image2 = images.get(0);
			if(!this.images.contains(image1)) {
				this.images.add(image1);
				this.images.add(image2);
			}
		}
		Collections.shuffle(this.images);
		
		this.rows = rows;
		this.columns = columns;
		
		this.player.get(0).setStatus(PlayerStatus.ACTIVE);
		this.currentPlayer = getCurrentPlayer();
		this.availableCards = this.images.size();
	}
	
	public Player getCurrentPlayer() {
		for (Player player : this.player) {
			if(player.getStatus().equals(PlayerStatus.ACTIVE)) {
				return player;
			}
		}
		return null;
	}
	
	public boolean isBlankRequired() {
		return (rows * columns) % 2 == 1;
	}
	
	public void matchFound() {
		this.availableCards -= 2;
		if(this.availableCards <= 1) {
			this.isFinished = true;
			giveResults();
		}
	}
	
	private void giveResults() {
		String result = "Game ends after " + this.counter;
		String[] points = new String[this.player.size()];
		Collections.sort(this.player, new Comparator<Player>() {
			public int compare(Player o1, Player o2) {
				return Integer.valueOf(o2.getPoints()).compareTo(o1.getPoints());
			}
		});
		for (int i = 0; i < this.player.size(); i++) {
			points[i] = this.player.get(i).getName() + " (" + this.player.get(i).getPoints() + ")";
		}
		result = String.join(",", points);
		
		File fileDir = new File("./files/exam");
		fileDir.mkdirs();
		File file = new File(fileDir, "memory.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Error while trying to create new file");
			}
		}
		
		String history = System.lineSeparator() + System.lineSeparator() + "Last games: " + System.lineSeparator();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("./files/exam/memory.txt")))) {
			while (reader.ready()) {
				String line = reader.readLine();
				history += line + System.lineSeparator();
			}
		} catch (IOException e) {
			System.err.println("errorMessage");
			e.printStackTrace();
		}
		
		try (Writer writer = new FileWriter(file, true)) {
			writer.write(result + System.lineSeparator());
		} catch (IOException e) {
			System.err.println("Error while trying to write into file");
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, result + history, "Score", JOptionPane.INFORMATION_MESSAGE);
	}

	public void nextPlayer() {
		int indexNextPlayer = this.player.indexOf(this.currentPlayer) + 1;
		indexNextPlayer = indexNextPlayer == player.size() ? 0 : indexNextPlayer;
		this.currentPlayer.setStatus(PlayerStatus.WAITING);
		this.currentPlayer = this.player.get(indexNextPlayer);
		this.currentPlayer.setStatus(PlayerStatus.ACTIVE);
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public List<Player> getPlayer() {
		return player;
	}

	public List<MemoryImage> getImages() {
		return images;
	}
	
	public boolean isFinished(){
		return isFinished;
	}

	public void incrementCounter() {
		this.counter++;
	}
	
}