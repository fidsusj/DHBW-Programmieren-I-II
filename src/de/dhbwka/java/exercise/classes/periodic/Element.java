package de.dhbwka.java.exercise.classes.periodic;

public class Element {

	private String name;
	private String symbol;
	private int ordinal;
	private char shell;
	// Oder über enum
	private int phase;
	private boolean main;
	
	public Element() {
		this("Wasserstoff", "H", 1, 'K', 1, true);	
	}
	
	public Element(String name, String symbol, int ordinal, char shell, int phase, boolean main) {
		this.name = name;
		this.symbol = symbol;
		
		if(0 < ordinal && ordinal <= 118)
			this.ordinal = ordinal;
		else
			this.ordinal = 1;
			
		switch(shell) {
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
				this.shell = shell;
				break;
			default:
				this.shell = 'K';
		}
		
		if(phase == 1 || phase == 2 || phase == 3)
			this.phase = phase;
		else
			this.phase = 3;
		
		this.main = main;
	}

	@Override
	public String toString() {
		return  "Name: " + name + "\n" +  
				"Symbol: " + symbol + "\n" +
				"Ordinal: " + ordinal + "\n" +
				"Shell: " + shell + "\n" +
				"Phase: " + phase + "\n" +
				"MainGroup: " + main + "\n";
	}
	
	public boolean equals(Object o) {
		if(o instanceof Element) {
			return ((Element) o).getOrdinal() == this.ordinal;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public char getShell() {
		return shell;
	}

	public int getPhase() {
		return phase;
	}

	public boolean isMain() {
		return main;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public void setShell(char shell) {
		this.shell = shell;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	
}