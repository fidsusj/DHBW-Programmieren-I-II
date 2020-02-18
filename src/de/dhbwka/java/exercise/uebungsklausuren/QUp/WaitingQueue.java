package de.dhbwka.java.exercise.uebungsklausuren.QUp;

import java.util.ArrayList;
import java.util.List;

public class WaitingQueue {

	private List<Integer> queue;
	private List<QueueObserver> observer;
	private List<Call> calls;
	private Call lastCall;
	
	public WaitingQueue() {
		
		this.queue = new ArrayList<>();
		this.observer = new ArrayList<>();
		this.calls = new ArrayList<>();
		
	}
	
	public int getNumber() {
		int number = this.queue.size() + 1;
		this.queue.add(number);
		return number;
	}
	
	public void addQueueObserver(QueueObserver ob) {
		if(!this.observer.contains(ob))
			this.observer.add(ob);
	}

	public void updateObservers() {
		for (QueueObserver queueObserver : this.observer) {
			queueObserver.updateView();
		}
	}
	
	public Call getNextCall(DeskApp desk) {
		if(!this.queue.isEmpty()) {
			Call call = new Call(this.queue.remove(0), desk);
			this.calls.add(call);
			this.lastCall = call;
			return call;
		} else {
			return null;
		}
	}
	
	public void confirmCall(Call call) {
		this.calls.remove(call);
		if(lastCall != null && this.lastCall.equals(call)) {
			this.lastCall = null;
		}
	}
	
	public Call getLastCall() {
		return this.lastCall;
	}
	
	public List<Call> getCurrentCalls(){
		return this.calls;
	}
	
	public int getWaitingClients() {
		return this.queue.size();
	}
	
}