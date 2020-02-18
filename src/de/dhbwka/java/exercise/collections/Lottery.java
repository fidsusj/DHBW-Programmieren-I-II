package de.dhbwka.java.exercise.collections;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Lottery {

	public static void main(String[] args) {
		
		Random rdm = new Random();
		Set<Integer> pool = new TreeSet<Integer>();
		Integer zusatzzahl = null;
		while(pool.size() < 7) {
			pool.add(zusatzzahl = Integer.valueOf(rdm.nextInt(49)+1));
		}
		pool.remove(zusatzzahl);
		
		for (Integer integer : pool)
			System.out.print(integer + " ");
		System.out.print("Zusatzzahl: " + zusatzzahl);

	}

}