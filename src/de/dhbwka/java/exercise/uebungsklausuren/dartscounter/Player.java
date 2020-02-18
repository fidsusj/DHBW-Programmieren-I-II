package de.dhbwka.java.exercise.uebungsklausuren.dartscounter;

public class Player {

	private String name;
	private int countDartsThrown;
	private Visit[] visits;
	
	public Player(String name) {
		this.name = name;
		this.visits = new Visit[10];
	}

	public int getRemainingPoints() {
		int sum = 0;		
		for (int i = 0; i < visits.length; i++) {
			if(visits[i] != null)
				sum += visits[i].getValue();
			else {
				break;
			}
		}
		return 501 - sum;
	}
	
	public boolean addVisit(Visit visit) {
		countDartsThrown += visit.getFields().length;
		
		if((getRemainingPoints() - visit.getValue() == 0 && visit.getLastField().isDoubleField()) || getRemainingPoints() - visit.getValue() >= 2) {
			for (int i = 0; i < visits.length; i++) {
				if(visits[i] == null) {
					visits[i] = visit;
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}

	public int getCountDartsThrown() {
		return countDartsThrown;
	}

	public Visit[] getVisits() {
		return visits;
	}
	
}