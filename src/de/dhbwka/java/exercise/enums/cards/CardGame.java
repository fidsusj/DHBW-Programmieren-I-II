package de.dhbwka.java.exercise.enums.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CardGame {

	private Stack<PlayingCard> stack;
	
	public CardGame() {
		stack = new Stack<>();
		for (Suit suit: Suit.values()) {
			for (CardValue card: CardValue.values()) {
				stack.add(new PlayingCard(suit,card));
			}
		}
		shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(stack);
	}
	
	public void sort() {
		Collections.sort(stack);
	}
	
	public PlayingCard get() {
		return stack.pop();
	}
	
	public List<PlayingCard> all(){
		List<PlayingCard> list = new LinkedList<>();
		for (int i = 0; i < stack.size(); i++) {
			list.add(stack.get(i));
		}
		return list;
	}
	
}