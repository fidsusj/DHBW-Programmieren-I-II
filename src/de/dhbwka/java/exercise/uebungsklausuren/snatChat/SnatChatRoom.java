package de.dhbwka.java.exercise.uebungsklausuren.snatChat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class SnatChatRoom {

	private String name;
	private List<SnatChatFrontend> frontends;
	private List<String> history;
	
	public SnatChatRoom(String name) {
		this.name = name;
		this.frontends = new ArrayList<>();
		this.history = new ArrayList<>();
		loadLog();
	}
	
	private void loadLog() {
		File file = new File("./files/snatChat/" + this.name + ".txt");
		if(file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				while (reader.ready()) {
					String line = reader.readLine();
					this.history.add(Message.rot13(line));
				}
			} catch (IOException e) {
				System.err.println("errorMessage");
				e.printStackTrace();
			}
		}
	}
	
	private void log(String msg) {
		File fileDir = new File("./files/snatChat");
		fileDir.mkdirs();
		File file = new File(fileDir, this.getRoomName() + ".txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("Error while trying to create new file");
		}

		try (Writer writer = new FileWriter(file, true)) {
			writer.write(Message.rot13(msg) + System.lineSeparator());
		} catch (IOException e) {
			System.err.println("Error while trying to write into file");
			e.printStackTrace();
		}
	}

	public String getRoomName() {
		return this.name;
	}
	
	public void register(SnatChatFrontend s) {
		if(!this.frontends.contains(s)) {
			this.frontends.add(s);
			for (String message : history.subList(history.size() > 9 ? history.size() - 10 : 0, history.size())) {
				s.receiveMessage(message);
			}
		}
	}
	
	public void unregister(SnatChatFrontend s) {
		this.frontends.remove(s);
	}
	
	public void sendMessage(Message msg) {
		for (SnatChatFrontend snatChatFrontend : frontends) {
			snatChatFrontend.receiveMessage(msg);
		}
		this.history.add(msg.toString());
		this.log(msg.toString());
	}
	
	public void sendMessage(String msg) {
		for (SnatChatFrontend snatChatFrontend : frontends) {
			snatChatFrontend.receiveMessage(msg);
		}
		this.history.add(msg);
		this.log(msg);
	}
	
}