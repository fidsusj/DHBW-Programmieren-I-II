package de.dhbwka.java.exercise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;


public class CrossTotalFile {
		
		private String value;
		private int crossTotal;

		public CrossTotalFile(String value) {
			
			this.value = value;
			
			for(int i = 0; i < value.length(); i++) {
				// Auto Unboxing dabei
				this.crossTotal += Integer.valueOf(value.charAt(i)-'0');
			}
			
			// Character.getNumericValue(value.charAt(i))
			// substring(i,i+1)
			
			/**
			for(int i = 0; i < value.length(); i++) {
				this.crossTotal += Integer.parseInt("" + value.charAt(i));
			}
			*/
			
		}
	
		public static void main(String[] args) {
		
			Random rdm = new Random();
			CrossTotalFile ct;
			
			File dir = new File("./files/crossTotal");
			// Am besten in Try Catch Block packen, da evtl keine Rechte
			dir.mkdirs();
			File ctf = new File(dir, "crossTotal.txt");
			try {
				ctf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try(Writer writer = new FileWriter(ctf, true)){
				//PrintWriter -> Methode println -> Kein \r\n nötig  FileWriter immer in Printwriter Packen!!!!!
				for (int i = 0; i < 10; i++) {
					ct = new CrossTotalFile(new StringBuffer().append(rdm.nextInt(10001)).toString());
					writer.write("Quersumme von " + ct.value + " ist: " + ct.crossTotal + "\r\n");
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("-------------finished-------------");
		
	}

}