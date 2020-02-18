package de.dhbwka.java.exercise.classes;

public class Complex {

	private double a;
	private double b;
	
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public Complex add(Complex comp) {
		return new Complex(a + comp.getReal(), b + comp.getImag());
	}
	
	public Complex sub(Complex comp) {
		return new Complex(a - comp.getReal(), b - comp.getImag());
	}
	
	public Complex mult(Complex comp) {
		return new Complex(a*comp.getReal() - b*comp.getImag(), a*comp.getImag() + b*comp.getReal());
	}
	
	public Complex div(Complex comp) {
		return new Complex((a*comp.getReal() + b*comp.getImag())/(Math.pow(comp.getReal(),2) + Math.pow(comp.getImag(), 2)), (b*comp.getReal() - a*comp.getImag())/(Math.pow(comp.getReal(),2) + Math.pow(comp.getImag(), 2)));
	}
	
	public double getMagnitude() {
		return (Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
	}
	
	public boolean isLess(Complex comp) {
		return (this.getMagnitude() < comp.getMagnitude()); 
	}
	
	public String toString() {
		return a + " " + getAugury(b) + " " + Math.abs(b) + "i";
	}
	
	private String getAugury(double val) {
		return (String) (val < 0 ? "-" : "+");
	}
	
	public double getReal() {
		return a;
	}
	
	public double getImag() {
		return b;
	}
	
}