package de.dhbwka.java.exercise.uebungsklausuren.dartscounter;

public class Board {

	private Field[] fields;
	
	public Board() {
		
		fields = new Field[63];
		
		for (int i = 0; i < fields.length; i++) {
			
			if(i == 0) {
				fields[i] = new Field("x", 0, false);
			}
			else if(i >= 1 && i <=20 ){
				fields[i] = new Field(Integer.valueOf(i).toString(), i, false);
			}
			else if(i >= 21 && i <=40 ) {
				fields[i] = new Field("D" + Integer.valueOf(i-20).toString(), 2*(i-20), true);
			}
			else if(i >= 41 && i <=60 ){
				fields[i] = new Field("T" + Integer.valueOf(i-40).toString(), 3*(i-40), false);
			}
			else if(i == 61) {
				fields[i] = new Field(Integer.valueOf(25).toString(), 25, false);
			}
			else {
				fields[i] = new Field("BULL", 50, true);
			}
		}
		
	}
	
	public Field parseField(String label) {
		Field field = null;
		for (int i = 0; i < fields.length; i++) {
			if(fields[i].getLabel().equalsIgnoreCase(label)) {
				field = fields[i];
			}
		}
		return field;
	}
	
}