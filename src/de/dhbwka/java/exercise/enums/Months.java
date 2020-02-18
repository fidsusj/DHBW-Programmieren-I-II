package de.dhbwka.java.exercise.enums;

import java.util.Calendar;

public enum Months{

	Januar(31,new String[]{"Hartung", "Eismond"}),
	Februar(28, new String[]{"Hornung", "Schmelzmond", "Taumond", "Narrenmond", "Rebmond", "Hintester"}),
	Maerz(31, new String[]{"Lenzing", "Lenzmond"}),
	April(30, new String[]{"Launing", "Ostermond"}),
	Mai(31, new String[]{"Winnemond","Blumenmond"}),
	Juni(30, new String[]{"Brachet","Brachmond"}),
	Juli(31, new String[]{"Heuert","Heumond"}),
	August(31, new String[]{"Ernting","Erntemond","Bisemond"}),
	September(30, new String[]{"Scheiding","Herbstmond"}),
	Oktober(31, new String[]{"Gilbhart","Gilbhard","Weinmond"}),
	November(30, new String[]{"Nebelung","Windmond","Wintermond"}),
	Dezember(31, new String[]{"Julmond","Heilmond","Christmond","Dustermond"});
	
	private int days;
	private String[] names;
	
	private Months(int days, String[] names) {
		this.days = days;
		this.names = names;
	}

	public int getDays() {
		return days;
	}

	public String[] getNames() {
		return names;
	}

	public static void main(String[] args) {
		int month = Calendar.getInstance().get(Calendar.MONTH);
		Months m = Months.values()[month];
		System.out.println("Der " + m + " hat " + m.getDays() + " Tage.");
		System.out.print("Seine Alternativnamen sind: ");
		for (String string : m.getNames())
			System.out.print(string + " ");
	}
	
}