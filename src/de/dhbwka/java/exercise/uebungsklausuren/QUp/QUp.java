package de.dhbwka.java.exercise.uebungsklausuren.QUp;

public class QUp {

	public static void main(String[] args) {
		
		WaitingQueue qu = new WaitingQueue();
		for (int i = 0; i < 5; i++) {
			System.out.println("Nummer " + qu.getNumber() + " gezogen");
		}
		//new DeskApp("Sachbearbeitung 1", "Raum 102", "Herr Maier", qu);
		new DeskApp(qu, 2, 104, "Herr Schmid");
		new DeskApp(qu, 4, 103, "Herr Müller");
		//new QueueDisplay(qu);
		new QueueDisplay(qu);
		
	}

}