package de.dhbwka.java.exercise.uebungsklausuren.jBay;

public class jBay {

	public static void main(String[] args) {
		Auktionshaus jBay = new Auktionshaus();
		
		jBay.addAuktion(new Auktion(new Ware("Turnschuhe", "Tolle Turnschuhe, kaum getragen"), 1));
		jBay.addAuktion(new Auktion(new Ware("iPad", "Nagelneues iPad 3"), 4));
		jBay.addAuktion(new Auktion(new Ware("Currywurst", "Scharf, ohne Pommes"), 5));
		
		new BieterTerminal(new Bieter("Mickey", "Maus"), jBay);
		new BieterTerminal(new Bieter("Donald", "Duck"), jBay);
		
	}
	
}