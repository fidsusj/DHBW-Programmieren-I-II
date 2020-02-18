package de.dhbwka.java.exercise.classes;

public class Account {

	private int accountNumber;
	private String owner;
	private int balance;
	private int limit;
	
	public Account(int accountNumber, String owner) {
		this(accountNumber, owner, 0, 0);
	}
	
	public Account(int accountNumber, String owner, int balance, int limit) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = balance;
		this.limit = limit;
	}
	
	public void deposit(int amount) {
		if(amount > 0)
			this.balance += amount;
	}
	
	public void withdraw(int amount) {
		if(balance - amount >= limit && amount > 0) {
			this.balance -= amount;
			System.out.println("New bank balance: " + balance);
		}
		else {;
			System.err.println("Please select a valid value");
		}
	}
	
	public int getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", owner=" + owner + ", balance=" + balance + ", limit="
				+ limit + "]";
	}

	public static void main(String[] args) {
		
		Account acc = new Account(70497, "Felix", 10000, -200);
		System.out.println(acc.toString());		
		
		acc.withdraw(300);
		System.out.println(acc.toString());
		
	}

}