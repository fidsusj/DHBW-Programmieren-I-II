package de.dhbwka.java.exercise.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PrimesTest {

	public static void main(String[] args) {
	
		//Iterationsalgorithmus
		
		PrimesFile primeFile = new PrimesFile(10000);
		
		StringBuffer content = new StringBuffer();
		try(BufferedReader reader = new BufferedReader(new FileReader(primeFile.getFile()))){
			while(reader.ready()) {
				content.append(reader.readLine() + "\r\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		String[] primes = content.toString().split("\r\n");
	
		
		//---------------------
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Bitte geben Sie eine Zahl ein, die auf Primfaktor geprüft werden soll!");
		Integer number = scan.nextInt();
		boolean prim = false;
		
		for (int i = 0; i < primes.length; i++){
			if(primes[i].equals(number.toString())) {
				System.out.println(number.toString() + " ist eine Primzahl");
				prim = true;
			}
		}
		
		if(!prim) {
			System.out.println(number.toString() + " ist keine Primzahl");
		}
		
		scan.close();
		
	}

}