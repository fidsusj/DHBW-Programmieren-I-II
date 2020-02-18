package de.dhbwka.java.exercise.ui;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TextFrameJLabel extends JFrame{

public TextFrameJLabel(String path, int height, int width) {
		
		File file = new File(path);
		
		this.setTitle(file.getName());
		this.setLayout(new GridLayout(10,1,5,5));
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			for (int i = 0; i < 10; i++) {
				String line = reader.readLine();
				this.add(new JLabel(line + System.lineSeparator()));
			}
		}
		catch(IOException e) {
			System.err.println("Beim Einlesen der Datei ist etwas schiefgelaufen");
			e.printStackTrace();
		}
		
		this.setSize(height, width);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}