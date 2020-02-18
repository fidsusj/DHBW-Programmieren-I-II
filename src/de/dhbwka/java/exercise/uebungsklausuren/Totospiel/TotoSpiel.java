package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

public class TotoSpiel {

	//Benötigt zum einfacheren und schnelleren Programmieren: Maps, Callbacks
	
	public static void main(String[] args) {
		
		Totalisator t = new Totalisator("1. Bundesliga 2012/2013, 13. Spieltag");
		t.addPaarung(new Paarung(new Verein("Fortuna Düsseldorf"),new Verein("Hamburger SV")));
		t.addPaarung(new Paarung(new Verein("Bayern München"),new Verein("Hannover 96")));
		t.addPaarung(new Paarung(new Verein("FC Schalke 04"),new Verein("Eintracht Frankfurt")));
		t.addPaarung(new Paarung(new Verein("VfL Wolfsburg"),new Verein("Werder Bremen")));
		t.addPaarung(new Paarung(new Verein("1. FSV Mainz 05"),new Verein("Borussia Dortmund")));
		t.addPaarung(new Paarung(new Verein("SpVgg Greuther Fürth"),new Verein("1. FC Nürnberg")));
		t.addPaarung(new Paarung(new Verein("SC Freiburg"),new Verein("VfB Stuttgart")));
		t.addPaarung(new Paarung(new Verein("1899 Hoffenheim"),new Verein("Bayer Leverkusen")));
		t.addPaarung(new Paarung(new Verein("FC Augsburg"),new Verein("Borussia M'gladbach")));
		t.sortPaarungen();
		//t.addTipper(new Tipper("Michael","Backhaus",t));
		t.addTipper(new Tipper("Felix","Hausberger",t));
	}

}