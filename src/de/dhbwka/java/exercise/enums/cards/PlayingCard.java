package de.dhbwka.java.exercise.enums.cards;

public class PlayingCard implements Comparable<PlayingCard> {

	private Suit suit;
	private CardValue value;
	
	public PlayingCard(Suit suit, CardValue value) {
		this.suit = suit;
		this.value = value;
	}
	
	public String toString() {
		return "" + suit + " " + value;
	}

	@Override
	public int compareTo(PlayingCard card) {

		if(this.suit.equals(card.getSuit())) {
			return this.value.ordinal() - card.getValue().ordinal();
		}
		else {
			return this.suit.ordinal() - card.getSuit().ordinal();
		}
		
	}

	public Suit getSuit() {
		return suit;
	}

	public CardValue getValue() {
		return value;
	}
	
}