package de.dhbwka.java.exercise.uebungsklausuren.jBay;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

public class Auktion {
	
	public static final double increment = 1.0D;

	private Ware ware;
	private Gebot hoehstgebot;
	private double currentPrice;
	private Calendar timeEnds;
	private int dauer;
	
	public Auktion(Ware ware, int dauer) {
		this.ware = ware;
		this.dauer = dauer;
		this.timeEnds = Calendar.getInstance();
		this.timeEnds.setTimeInMillis(System.currentTimeMillis() + 60000 * this.dauer);
	}
	
	public boolean gebotAbgeben(Gebot g) {
		
		this.log(g);
		
		if(this.currentPrice + Auktion.increment > g.getPrice()){
			return false;
		} else if(this.hoehstgebot == null) {
			this.currentPrice = Auktion.increment;
			this.hoehstgebot = g;
			return true;
		} else if(this.hoehstgebot.getBieter().equals(g.getBieter())) {
			if(this.hoehstgebot.getPrice() < g.getPrice()) {
				this.hoehstgebot = g;
				return true;
			}
			return false;
		} else if(g.getPrice() >= this.currentPrice + Auktion.increment && g.getPrice() <= this.hoehstgebot.getPrice()) {
			this.currentPrice = Math.min(g.getPrice() + Auktion.increment, this.hoehstgebot.getPrice());
			return false;
		} else if(g.getPrice() >= this.currentPrice + Auktion.increment && g.getPrice() > this.hoehstgebot.getPrice()) {
			this.currentPrice = Math.min(g.getPrice(), this.hoehstgebot.getPrice() + Auktion.increment);
			this.hoehstgebot = g;
			return true;
		}
		return false;
	}
	
	private void log(Gebot g) {
		File fileDir = new File("./files/jBay");
		fileDir.mkdirs();
		File file = new File(fileDir, "jBay.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("Error while trying to create new file");
		}

		try (Writer writer = new FileWriter(file, true)) {
			writer.write(String.format("[%s] Gebot von %s für %s: %s Euro.", Calendar.getInstance().getTime().toString(), g.getBieter().getFullName(), this.ware.getTitle(), g.getPrice() + "") + System.lineSeparator());
		} catch (IOException e) {
			System.err.println("Error while trying to write into file");
			e.printStackTrace();
		}
	}

	public Ware getWare() {
		return this.ware;
	}

	public Gebot getHoehstgebot() {
		return this.hoehstgebot;
	}

	public double getCurrentPrice() {
		return this.currentPrice;
	}

	public Calendar getTimeEnds() {
		return this.timeEnds;
	}
	
	public int getDauer() {
		return this.dauer;
	}
	
}