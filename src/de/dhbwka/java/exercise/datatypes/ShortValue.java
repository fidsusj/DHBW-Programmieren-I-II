package de.dhbwka.java.exercise.datatypes;

public class ShortValue {

	public static void main(String[] args) {
		short var = 32767;
		System.out.println(var);
		System.out.println(var+=1);
		System.out.println(++var);
		
		short var2 = 32767;
		var2 = (short) (var2 + 1);
		System.out.println(var2);
		
//	short var2 = var + 1; 
//		System.out.println(var2);
		
	}
}
