package de.dhbwka.java.exercise.uebungsklausuren.zoo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Zoo {

	private static final int MAX_CAPACITY = 5;
	private int capacity;
	private int amountOfAnimals;
	private ZooTier[] animals;
	
	public Zoo() {
		this.capacity = MAX_CAPACITY;
		this.animals = new ZooTier[capacity];
	}
	
	public Zoo(int capacity) throws ZooCapacityException {
		if(0 < capacity && capacity <= MAX_CAPACITY ){
			this.capacity = capacity;
			this.animals = new ZooTier[capacity];
		}
		else
			throw new ZooCapacityException("Die maximale Kapazität des Zoos wurde erreicht");
	}
	
	public void addAnimal(ZooTier tier) throws ZooCapacityException{
		for (int i = 0; i < animals.length; i++) {
			if(animals[i] == null) {
				this.animals[i] = tier;
				this.amountOfAnimals++;
				System.out.println("Tier erfolgreich hinzugefügt");
				return;
			}
		}
		throw new ZooCapacityException("Die maximale Zookapazität wurde überschritten");
	}
	
	public ZooTier[] getAnimals() {
		ZooTier[] animals = new ZooTier[amountOfAnimals];
		System.arraycopy(this.animals, 0, animals, 0, animals.length);
		return animals;
	}
	
	public boolean existsAnimal(String name) {
		for (int i = 0; i < animals.length; i++) {
			if(animals[i].getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	public void fuettern(String futter) {
		for (int i = 0; i < animals.length; i++) {
			animals[i].fuettern(futter);
		}
		System.out.println("Tiere erfolgreich gefüttert");
	}
	
	public void saveToFile(String filename) throws ZooFileException {
		File dir = new File("files/uebungsklausur/zoo");
		File file = new File(dir, filename);
		
		try {
			file.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		try(FileWriter writer = new FileWriter(file)){
			for (int i = 0; i < animals.length; i++) {
				writer.write(animals[i].getArt() + ";" + animals[i].getName() + ";" + animals[i].getClass().getSimpleName() + System.lineSeparator());
			}
			System.out.println("Datei erfolgreich gespeichert");
		}
		catch(IOException e) {
			throw new ZooFileException("Die Datei wurde nicht gefunden");
		}
	}
	
	public static void main(String[] args) {
		Zoo zoo = new Zoo();
		
		try {
			zoo.addAnimal(new Raubtier("Tiger", "Fred"));
			zoo.addAnimal(new Raubtier("Tiger", "Lisa"));
			zoo.addAnimal(new Singvogel("Kleiber", "Hansi"));
			zoo.addAnimal(new Singvogel("Schneeammer", "Sina"));
			zoo.addAnimal(new Singvogel("Zaunkoenig", "Henry"));
			
			System.out.println(zoo.existsAnimal("lisa"));
		
			zoo.saveToFile("zootiere.txt");
		}
		catch (ZooCapacityException e){
			System.err.println(e.getMessage());
		}
		catch (ZooFileException e){
			System.err.println(e.getMessage());
		}

		zoo.fuettern("Körner");
		
	}
	
}