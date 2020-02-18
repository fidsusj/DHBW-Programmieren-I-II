package de.dhbwka.java.exercise.threads.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;

public class ReadURL {
	
	public static String getStringContentFromUrl(String url, String encoding) {
		StringBuilder buffer = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), encoding))) {
			while (br.ready() && buffer.length() < 50) {
				buffer.append(br.readLine()).append(System.lineSeparator());
			}
		} catch (IOException ex) {
		}
		return buffer.toString();
	}
	
}