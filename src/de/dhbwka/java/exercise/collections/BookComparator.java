package de.dhbwka.java.exercise.collections;

public class BookComparator implements java.util.Comparator<Book>{

	private SortCriteria criteria;
	
	public BookComparator(SortCriteria criteria){
		this.criteria = criteria;
	}
	
	@Override
	public int compare(Book arg0, Book arg1) {
		switch(criteria) {
			case Title: return arg0.getTitle().compareTo(arg1.getTitle());
			case Author: return arg0.getAuthor().compareTo(arg1.getAuthor());
			case Year: return arg0.getYear() - arg1.getYear();
			case Publisher: return arg0.getPublisher().compareTo(arg1.getPublisher());
		}
		return 0;
	} 

}