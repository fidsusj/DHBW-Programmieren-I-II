package de.dhbwka.java.exercise.threads.buffer;

import java.util.LinkedList;
import java.util.List;

public class MyBuffer {

	private final int MAXSIZE;
	private List<Integer> values;
	private boolean empty;
	
	public MyBuffer(int capacity) {
		this.MAXSIZE = capacity;
		this.values = new LinkedList<>();
		this.empty = true;
	}

	public synchronized void put(Integer v) {
		if(values.size() == MAXSIZE) {
			try{
				this.wait();
			} 
			catch(InterruptedException e) {
				System.err.println("Something went wrong");
			}
		}
		
		this.values.add(v);
		this.empty = false;
		this.notify();
		System.out.println("Put: "+ v);
		System.out.println("Fill level after put: " + values.size());
	}
	
	public synchronized Integer get() {
		Integer v = null;
		if(this.empty) {
			try{
				this.wait();
			}
			catch(InterruptedException e) {
				System.err.println("Something went wrong");
			}
		}
		
		v = ((LinkedList<Integer>) this.values).removeFirst();
		this.empty= this.values.isEmpty();
		this.notify();
		System.out.println("Get: "+ v);
		System.out.println("Fill level after get: " + values.size());
		return v;
	}
	
	public static void main(String[] args) {
		MyBuffer buf= new MyBuffer(2);
		ProducerThread thread1= new ProducerThread(buf);
		thread1.start();
		ConsumerThread thread2= new ConsumerThread(buf);
		thread2.start();
	}
}