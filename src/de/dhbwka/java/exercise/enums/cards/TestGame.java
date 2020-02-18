package de.dhbwka.java.exercise.enums.cards;

public class TestGame {

	public static void main(String[] args) {
		CardGame game = new CardGame();
		PlayingCard heartSeven = new PlayingCard(Suit.Heart, CardValue.Seven);
		PlayingCard card = null;
		
		for (int i = 0; i < 10; i++) {
		    card = game.get();
			System.out.print(card.toString() + ": ");
			
			String result = "";
			switch((int) Math.signum((float) card.compareTo(heartSeven))) {
				case 0:
					result = "equal";
					break;
				case 1:
					result = "higher";
					break;
				case -1:
					result = "lower";
					break;
				default: 
					result = "error";
					break;
			}
			
			System.out.println(result + ", ");
		}
		
		game.sort();
		
		System.out.println();
		System.out.println("Sorted Stack: ");
		for (PlayingCard c : game.all()) {
			System.out.println(c.toString());
		}
	}
	
}