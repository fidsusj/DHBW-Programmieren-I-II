package de.dhbwka.java.exercise.classes;

public class Polynomial {

	private double a,b,c;
	private int grad;
	
	public Polynomial() {
		this(1,0,0);
	}
	
	public Polynomial(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;

		if(a != 0) {
			grad = 2;
		}
		else if(a == 0 && b!= 0) {
			grad = 1;
		}
	}
	
	// Strg + Alt + 2/3 -> ²³
	
	public String printPolynomial() {
		String output;
		switch(grad) {
			case 2: if(b == 0) {
					output = (a + "x^2 " + getAugury(c) + " " + Math.abs(c));
					}
					else if(c == 0){
						output = (a + "x^2 " + getAugury(b) + " " + Math.abs(b) + "x");
					}
					else if(b == 0 && c == 0) {
						output = (a + "x^2");
					}
					else {
						output = (a + "x^2 " + getAugury(b) + " " + Math.abs(b) + "x " + getAugury(c) + " " + Math.abs(c));
					}
					break;
			case 1: if (c != 0){
						output = (b + "x " + getAugury(c) + " " + Math.abs(c)); 
					}
					else {
						output = (b + "x");
					}
					break;
			case 0: output = "" + c;
					break;
			default: output = "Something went wrong";
		}	
		
		return output;
	}
	
	public double getFunctionValue(double x) {
		return (a*Math.pow(x, 2) + b*x + c);
	}
	
	public Polynomial addPolynom(Polynomial poly) {
		return new Polynomial(a + poly.getA(), b + poly.getB(), c + poly.getC());
	}
	
	public Polynomial multSkalar(double val) {
		return new Polynomial(val * a, val * b, val * c);
	}
	
	public double[] getZero() {
		double[] zeros;
		double determ = Math.pow(b, 2) - 4*a*c;
		if(determ > 0) {
			zeros = new double[2];
			zeros[0] = (-b + Math.sqrt(determ))/(2*a);
			zeros[1] = (-b - Math.sqrt(determ))/(2*a);
		}
		else if(determ == 0) {
			zeros = new double[1];
			zeros[0] = (-b)/(2*a);
		}
		else {
			zeros = new double[0];
		}
		return zeros;
	}
	
	private String getAugury(double val) {
		return (String) (val < 0 ? "-" : "+");
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public double getC() {
		return c;
	}
}