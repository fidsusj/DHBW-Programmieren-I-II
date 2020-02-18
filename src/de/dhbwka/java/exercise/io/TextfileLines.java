package de.dhbwka.java.exercise.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class TextfileLines {

	public static void main(String[] args) { 
	
		//Fängt bei Zeile 0 an zu lesen
		
		try(LineNumberReader reader = new LineNumberReader(new FileReader("./files/textfileLines/beispiel.txt"))) {
			StringBuffer contents = new StringBuffer();
			do{
				String content = reader.readLine();
				if(reader.getLineNumber() > 1) {
					System.out.println(content);
					contents.append(content);		
				}
			}while(reader.getLineNumber() < 6);
			System.out.println(contents);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}