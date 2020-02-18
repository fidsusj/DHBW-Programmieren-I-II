package de.dhbwka.java.exercise.uebungsklausuren.jBay;

import java.util.ArrayList;
import java.util.List;

public class Auktionshaus {

	private List<Auktion> auktionen;
	private List<BieterTerminal> terminals;
	
	public Auktionshaus() {
		this.auktionen = new ArrayList<>();
		this.terminals = new ArrayList<>();
	}
	
	public void addAuktion(Auktion a) {
		if(!this.auktionen.contains(a))
			this.auktionen.add(a);
	}
	
	public void removeAuktion(Auktion a) {
			this.auktionen.remove(a);
	}
	
	public void register(BieterTerminal b) {
		if(!this.terminals.contains(b))
			this.terminals.add(b);
	}
	
	public void unregister(BieterTerminal b) {
			this.terminals.remove(b);
	}
	
	public void updateTerminals() {
		for (BieterTerminal terminal : this.terminals) {
			terminal.updateTerminal();
		}
	}
	
	public List<Auktion> getAuktionen(){
		return this.auktionen;
	}
	
	public List<BieterTerminal> getTerminals(){
		return this.terminals;
	}
	
}