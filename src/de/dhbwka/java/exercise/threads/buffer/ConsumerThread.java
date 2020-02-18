package de.dhbwka.java.exercise.threads.buffer;

import java.util.Random;

class ConsumerThread extends Thread{

	private MyBuffer buffer;
	
	public ConsumerThread(MyBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		Random rdm = new Random();
		for (int i = 0; i < 100; i++) {
			buffer.get();
			
			try {
				Thread.sleep(rdm.nextInt(1000)+1);
			} catch (InterruptedException e) {
				System.err.println("Something went wrong");
			}
		}
	}
}