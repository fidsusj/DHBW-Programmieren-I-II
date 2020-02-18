package de.dhbwka.java.exercise.uebungsklausuren.dartscounter;

public class Test {

	public static void main(String[] args) {

		Player[] players = new Player[2];
		players[0] = new Player("Geralt von Riva");
		players[1] = new Player("Yennefer von Vengerberg");
		Board board = new Board();
		Game game = new Game(board, players);
		
		game.start();
		
	}

}