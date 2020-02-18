package de.dhbwka.java.exercise.collections;

public class Book {

	private String title;
	private String author;
	private int year;
	private String publisher;
	
	public Book(String title, String author, int year, String publisher) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.publisher = publisher;
	}
	
	public String toString() {
		return title + " " + author + " " + year + " " + publisher;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public String getPublisher() {
		return publisher;
	}
	
}