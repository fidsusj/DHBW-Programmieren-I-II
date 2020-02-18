package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

import java.util.Comparator;

public class PaarungComparator implements Comparator<Paarung>{

	@Override
	public int compare(Paarung o1, Paarung o2) {
		return o1.getHeim().getName().compareTo(o2.getHeim().getName());
	}

}