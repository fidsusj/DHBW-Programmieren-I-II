package de.dhbwka.java.exercise.temp.StadtLandFluss;

public class StadtLandFluss {

	public static void main(String[] args) {
		
		Game slf = new Game(4,6,10);
		slf.register(new Sheet(new Player("Otto"), slf));
		slf.register(new Sheet(new Player("Anna"), slf));
		
	}

}