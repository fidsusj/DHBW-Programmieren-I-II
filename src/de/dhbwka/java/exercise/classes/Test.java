package de.dhbwka.java.exercise.classes;


public class Test {

	public static void main(String[] args) {
		
		Lotto lotto = new Lotto(6,49);
		lotto.tippen();
		lotto.ziehen();
		System.out.println(lotto.toString());
		
	}
}