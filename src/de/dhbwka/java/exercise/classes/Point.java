package de.dhbwka.java.exercise.classes;

public class Point {

	private double xPos;
	private double yPos;
	
	public Point() {
		
	}
	
	public Point(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public Point mirrorPoint() {
		return new Point(-this.xPos, -this.yPos);
	}
	
	public Point mirrorPointX() {
		return new Point(this.xPos, -this.yPos);
	}
	
	public Point mirrorPointY() {
		return new Point(-this.xPos, this.yPos);
	}
	
	public double getDistance() {
		return getDistance(new Point(0,0));
	}
	
	public double getDistance(Point p) {
		return (Math.sqrt(Math.pow(Math.abs(p.getxPos()- this.getxPos()),2) + Math.pow(Math.abs(p.getyPos()- this.getyPos()),2)));
	}
	
	@Override
	public String toString() {
		return "Point [xPos=" + xPos + ", yPos=" + yPos + "]";
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public static void main(String[] args) {
		
		Point pointAlpha = new Point(3,4);
		Point pointBeta = new Point(4,6);
		
		System.out.println(pointAlpha.getDistance());
		System.out.println(pointAlpha.getDistance(pointBeta));
		System.out.println(pointAlpha.toString());
		
		System.out.println(pointAlpha.mirrorPoint().toString());
		
	}

}