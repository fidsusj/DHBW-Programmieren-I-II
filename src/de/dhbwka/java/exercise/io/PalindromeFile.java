package de.dhbwka.java.exercise.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class PalindromeFile {

	private String word;
	private String palindrom;
	
	public PalindromeFile(String word) {
		this.word = word;
		
		//StringBuffer sb = new StringBuffer(word);
		//sb.reverse();
		
		StringBuffer sb = new StringBuffer();
		for(int i = word.length() - 1; i >= 0; i--) {
			sb.append(word.charAt(i));
		}
		
		this.palindrom = sb.toString();
	}
	
	public boolean isPalindrom() {
		return word.equalsIgnoreCase(palindrom);
	}

	public String getWord() {
		return word;
	}

	public String getPalindrom() {
		return palindrom;
	}
	
	public static void main(String[] args) {
		
		File dir = new File("./files/palindrome");
		dir.mkdirs();
		File file = new File(dir, "palindrome.txt");
		try {
			file.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		try(Writer writer = new FileWriter(file,true)){
			Scanner scan = new Scanner(System.in);
			PalindromeFile palinfile;
			System.out.println("Wie viele Palindrome wollen sie eingeben?");
			int repeats = scan.nextInt();
			for(int i = 0; i < repeats; i++) {
				System.out.println("Bitte ein Wort eingeben: ");
				palinfile = new PalindromeFile(scan.next());
				//System.lineSeparator();
				if(palinfile.isPalindrom())
					writer.write(palinfile.getPalindrom() + "\r\n");
			}
			scan.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			System.out.println("\nAlle bisher gefundenen Palindrome: ");
			while(reader.ready()) {
				System.out.println(reader.readLine());
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}