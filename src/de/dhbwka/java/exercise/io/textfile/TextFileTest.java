package de.dhbwka.java.exercise.io.textfile;

public class TextFileTest {

	public static void main(String[] args) throws LineNumberOutOfBoundsException {
			
			TextFile file = new TextFile("./files/textFile/file.txt");
			System.out.println(file.getContent());
			System.out.println(file.availableLines() + "\n");
			String[] lines = file.getLines();
			
			for (int i = 0; i < lines.length; i++) {
				System.out.printf("%-35s | Zeilenende \n", lines[i]);
			}
			
			System.out.println("\n" + file.getLine(2));
			
			file.setLine(1, "Ersetzte Zeile");
			System.out.println("\n" + file.getContent());
			
			//file.close();
			
	}

}