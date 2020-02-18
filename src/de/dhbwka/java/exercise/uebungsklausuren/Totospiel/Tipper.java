package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

import java.util.ArrayList;
import java.util.List;

public class Tipper {

	private String vorname;
	private String nachname;
	private Totalisator totalisator;
	private List<Tipp> tipps;
	private TipperTerminal terminal;
	
	public Tipper(String vorname, String nachname, Totalisator totalisator) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.totalisator = totalisator;
		
		this.tipps = new ArrayList<>();
		this.terminal = new TipperTerminal(this, this.totalisator);
		this.totalisator.addTipper(this);
	}
	
	public void receiveResult(int sum) {
		this.terminal.receiveResult(sum);
	}
	
	public void addTipp(Tipp t) {
		if(!this.tipps.contains(t))
			this.tipps.add(t);
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public Totalisator getTotalisator() {
		return totalisator;
	}

	public List<Tipp> getTipps() {
		return tipps;
	}

	public TipperTerminal getTerminal() {
		return terminal;
	}
	
}