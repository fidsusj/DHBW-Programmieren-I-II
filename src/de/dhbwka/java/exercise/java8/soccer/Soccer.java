package de.dhbwka.java.exercise.java8.soccer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Soccer {

	private static int comparePlayerByNumber(Player p1, Player p2) {
		return Integer.valueOf(p1.getNumber()).compareTo(p2.getNumber());
	}
	
	private static int comparePlayerByName(Player p1, Player p2) {
		return p1.getName().compareTo(p2.getName());
	}
	
	
	public static void main(String[] args) {
		
		Path path = Paths.get("./files/soccer/soccer.txt");
		
		try {
			List<Player> players = Files.readAllLines(path)
							.stream()
							.map(e -> e.split(";"))
							.map(e -> new Player(Integer.parseInt(e[0]),e[1],e[2],e[3],e[4],Integer.parseInt(e[5]),Integer.parseInt(e[6])))
							.collect(Collectors.toList());
			
			players.stream().sorted((p1,p2) -> comparePlayerByNumber(p1,p2)).forEach(System.out::println);
			players.stream().filter(p1 -> (p1.getGames() > 50)).sorted(Soccer::comparePlayerByName).forEach(System.out::println);
			players.stream().map(e -> new String(e.getClub())).distinct().forEach(System.out::println);
		
			long amountOfPlayers = players.stream().filter(p1 -> (p1.getGoals() < 5)).count();
			long amountOfGoals =    players.stream().mapToInt(e -> e.getGoals()).sum();
			System.out.println(amountOfPlayers);
			System.out.println(amountOfGoals);
		} catch (IOException e) {
			System.err.println("Something went wrong while reading this File");
		}
		
	}
		
}