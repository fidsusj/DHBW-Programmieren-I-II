package de.dhbwka.java.exercise.uebungsklausuren.dartscounter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {

	private Board board;
	private Player[] players;
	private int round;
	
	public Game(Board board, Player[] players) {
		this.board = board;
		this.players = players;
		this.round = 0;
	}
	
	public void start() {
		Scanner scan = new Scanner(System.in);
		StringBuffer zug = new StringBuffer();
		
		while(!isFinished()) {
			for (int i = 0; i < players.length; i++) {
				System.out.println("Player: " + players[i].getName() + ", " + players[i].getRemainingPoints() + " points remaining.");
				System.out.println("Score to reach: " + players[i].getRemainingPoints());
				System.out.println("Checkout Opportunity: " + getCheckOut(players[i].getRemainingPoints()));
				System.out.print("Enter Visit: ");
				
				zug = new StringBuffer(scan.nextLine());
				String[] hits = zug.toString().split(" ");
				Field[] fields = new Field[hits.length];
				for (int j = 0; j < fields.length; j++) {
					fields[j] = board.parseField(hits[j]);
				}
				
				try {
					Visit visit = new Visit(fields);
					System.out.println("Scored: " + visit.getValue());
					if(!players[i].addVisit(visit))
						System.out.println("Dieser Wurfdurchgang war ungültig!");
					System.out.println("=======================================");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		
		scan.close();
		
	}
	
	private boolean isFinished() {
		boolean finished = false;
		this.round++;
		
		if(round == 11) {
			System.out.println("Spielzugüberschreitung!");
			finished = true;
		}
		
		for (int i = 0; i < players.length; i++) {
			if(players[i].getRemainingPoints() == 0) {
				writeHighScore(players[i]);
				System.out.println("Spieler " + players[i].getName() + " hat gewonnen!");
				finished = true;
			}
		}
		return finished;
	}
	
	private void writeHighScore(Player winner) {
		File dir = new File("files/uebungsklausur/dartscounter");
		try {
			dir.mkdirs();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		File data = new File(dir, "highscore.txt");
		try {
			data.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(PrintWriter printer = new PrintWriter(new FileWriter(data,true))){
				printer.println(winner.getName() + " won with " + winner.getCountDartsThrown());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getCheckOut(int value) {
		File data = new File(new File("files/uebungsklausur/dartscounter"), "checkouts.txt");
		
		try(LineNumberReader reader = new LineNumberReader(new FileReader(data))){
				while(reader.ready()) {
					String checkout = reader.readLine();
					if(reader.getLineNumber() == value) {
						return checkout;
					}
				}
				return "Not available";
		} catch (IOException e) {
			e.printStackTrace();
			return "No checkout file found";
		}
		
	}

}