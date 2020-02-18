package de.dhbwka.java.exercise.io.textfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class TextFile {

	private File file;
	private StringBuffer content;
	
	public TextFile(File file) {
		this.file = file;
		this.content = new StringBuffer();
		read();
	}
	
	public TextFile(String pfad) {
		this(new File(pfad));
	}
	
	public void read() {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))){
			while(reader.ready()) {
				content.append(reader.readLine() + "\r\n");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write() {
		try(Writer writer = new FileWriter(file)){
			writer.write(content.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int availableLines() {
		try(LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
			reader.skip(content.length());
			return reader.getLineNumber() + 1;
		}catch(IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public String[] getLines() {
		return content.toString().split("\r\n");
	}
	
	public String getLine(int i) throws LineNumberOutOfBoundsException {
		if(i < 1 || i > availableLines()) {
			throw new LineNumberOutOfBoundsException("Diese Zeileneingabe ist nicht gültig");
		} else {
			String[] s = getLines();
			return s[i-1];
		}
	}
	
	public void setLine(int i, String s) throws LineNumberOutOfBoundsException{
		if(i < 1 || i > availableLines()) {
			throw new LineNumberOutOfBoundsException();
		}else {
			String[] newLines = getLines();
			newLines[i-1] = s;
			this.content = new StringBuffer(String.join(System.lineSeparator(), newLines));
		}
	}
	
	public void replaceAll(String regexp, String ersatz) {
		String modified = content.toString().replaceAll(regexp, ersatz);
		this.content = new StringBuffer(modified);
	}
	
	public String getContent() {
		return content.toString();
	}
	
	public void close() {
		try {
			write();
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}