package de.dhbwka.java.exercise.classes.periodic;

public class PeriodicTable {
	
	private Element[] elements;
	
	public PeriodicTable() {
		elements = new Element[118];
	}
	
	public void addElement(Element e) {
		elements[e.getOrdinal()-1] = e;
	}
	
	public boolean hasElement(int o) {
		return elements[o-1] != null;
	}
	
	public Element getElement(int o) {
		return elements[o-1];
	}
	
	
	public Element[] getMetals(){
		int count = 0;
		for (int i = 0; i < elements.length; i++) {
			if(elements[i] instanceof Metal)
				count++;
		}
		Element[] metal = new Element[count];
		int index = 0;
		for (int i = 0; i < elements.length; i++) {
			if(elements[i] instanceof Metal) {
				metal[index] = elements[i];
				index++;
			}
		}
		
		return metal;
	}
	
	private Element[] getElements() {
		return elements;
	}
	
	public static void main(String[] args) {
		PeriodicTable pt = new PeriodicTable();
		pt.addElement(new Element("Wasserstoff", "H", 1, 'K', 3, true));
		pt.addElement(new Element("Helium", "He", 2, 'K', 3, true));
		pt.addElement(new Metal("Natrium", "Na", 11, 'M', 1, true, false, 21E6));
		pt.addElement(new Metal("Eisen", "Fe", 26, 'N', 1, false, false, 10.02E6));
		pt.addElement(new Metal("Germanium", "Ge", 32, 'N', 1, false, true, 1.45));
		pt.addElement(new Element("Brom", "Br", 35, 'N', 2, true));
		pt.addElement(new Metal("Tellur", "Te", 52, 'O', 1, true, true, 5.0E-3));
		pt.addElement(new Metal("Gold", "Au", 79, 'P', 1, false, false, 44E6));
	
		System.out.println(pt.getElements()[0].equals(new Element("Wasserstoff", "H", 1, 'K', 3, true)));
		
		Element[] e = pt.getElements();
		
		for (int i = 0; i < e.length; i++) {
			if(e[i] == null)
				continue;
			System.out.println(e[i].toString());
		}
	
		e = pt.getMetals();
		for (int i = 0; i < e.length; i++) {
			System.out.println(e[i].toString());
		}
		
		System.out.println(pt.getElement(79).toString());
		
	}
	
}