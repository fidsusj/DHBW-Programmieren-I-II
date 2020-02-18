package de.dhbwka.java.exercise.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTree<T extends Comparable<T>> {

	private T value;
	private BinaryTree<T> leftNode;
	private BinaryTree<T> rightNode;
	
	public BinaryTree(T value){
		
		this.value = value;
		this.leftNode = null;
		this.rightNode = null;
		
	}
	
	//Links größer, rechts kleiner
	public boolean add(T newValue) {
		
		switch((int) Math.signum(this.value.compareTo(newValue))) {
			case 0:
				return false;
			case 1:
				if(rightNode == null)
					rightNode = new BinaryTree<T>(newValue);
				else
					rightNode.add(newValue);
				return true;
			case -1:
				if(leftNode == null)
					leftNode = new BinaryTree<T>(newValue);
				else
					leftNode.add(newValue);
				return true;
			default: 
				return false;
		}
		
	}
	
	public T getValue() {
		return value;
	}
	
	public List<T> traverse(){
		List<T> list = new ArrayList<>();
		if(rightNode != null)
			list.addAll(rightNode.traverse());
		list.add(this.value);
		if(leftNode != null)
			list.addAll(leftNode.traverse());
		return list;
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.valueOf(10));
		Random rdm = new Random();
		
		System.out.print("Zufallszahlen: ");
		for (int i = 0; i < 10; i++) {
			Integer value = rdm.nextInt(101);
			tree.add(value);
			System.out.print(value + " ");
		}
		System.out.println();
		System.out.println("----------");
		System.out.print("Sortierte Zahlen: ");
		List<Integer> list = tree.traverse();
		for (Integer element : list) {
			System.out.print(element + " ");
		}
	}
	
}