package de.dhbwka.java.exercise.io;

import java.io.File;
import java.io.IOException;

public class Files {

	public static void main(String[] args) {
		
		File fileDir = new File("myDir");
		fileDir.mkdir();
		
		File fileFoo1 = new File(fileDir, "foo1.txt");
		File fileFoo2 = new File(fileDir, "foo2.txt");
		File fileFoo3 = new File(fileDir, "foo3.txt");
		
		try{
			fileFoo1.createNewFile();
			fileFoo2.createNewFile();
			fileFoo3.createNewFile();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println(fileFoo1.getAbsolutePath());
		String[] files = fileDir.list();
		for(String s: files) {
			System.out.println(s);
		}
		
		fileFoo1.delete();
		fileFoo2.delete();
		fileFoo3.delete();		
		fileDir.delete();
		
	}
	
}