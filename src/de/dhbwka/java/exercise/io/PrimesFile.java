package de.dhbwka.java.exercise.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class PrimesFile {

	private int[] potPrim;
	private File file;
	
	public PrimesFile(int grenze) {
	
		potPrim = new int[grenze+1];
		for (int i = 0; i < potPrim.length; i++) {
			potPrim[i] = i;
		}
		
		for (int i = 2; i < potPrim.length/2; i++) {
			if(potPrim[i] == 0)
				continue;
			
			for (int j = 2*potPrim[i]; j < potPrim.length; j+= potPrim[i]) {
				potPrim[j] = 0;
			}
		}
		
		file = new File("./files/prim");
		file.mkdirs();
		file = new File(file, "prim.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(Writer writer = new FileWriter(file)){
			for (int i = 2; i < potPrim.length; i++) {
				if(potPrim[i] != 0)
					writer.write(Integer.valueOf(potPrim[i]).toString() + "\r\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public int[] getPotPrim() {
		return potPrim;
	}

	public File getFile() {
		return file;
	}
	
	
	
}