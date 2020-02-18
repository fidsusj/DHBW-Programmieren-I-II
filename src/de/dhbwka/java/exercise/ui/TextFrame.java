package de.dhbwka.java.exercise.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TextFrame extends JFrame{
	
	public TextFrame(String path, int height, int width) {
		
		File file = new File(path);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		this.setTitle(file.getName());
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			while(reader.ready()) {
				String line = reader.readLine();
				textArea.append(line + System.lineSeparator());
			}
		}
		catch(IOException e) {
			System.err.println("Beim Einlesen der Datei ist etwas schiefgelaufen");
			e.printStackTrace();
		}
		
		//this.add(textArea);
		//Komponente für JScrollPane immer im Konstruktor, nicht durch add() hinzufügen
		this.add(new JScrollPane(textArea));
		this.setSize(height, width);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		if(args.length != 3)
			System.err.println("Bitte die 3 Konsolenparamter <Pfad zur Datei> <Höhe des Fensters> <Breite des Fensters> hinterlegen!");
		else {
			try {
				new TextFrame(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			}
			catch(NumberFormatException e) {
				System.err.println("In den Argumenten wurde die Pixelangabe nicht in Zahlenformat hinterlegt!");
				e.printStackTrace();
			}
		}
	}
	
}