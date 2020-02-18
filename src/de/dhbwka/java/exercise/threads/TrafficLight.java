package de.dhbwka.java.exercise.threads;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TrafficLight extends JComponent implements Runnable {

	private Thread thread;
	private enum State {RED, REDYELLOW, GREEN, YELLOW};
	private State currentState;
	
	public TrafficLight() {
		super();
		this.currentState = State.RED;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			if(this.currentState.ordinal() == 3)
				currentState = State.values()[0];
			else
				currentState = State.values()[currentState.ordinal() + 1];
			
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.fillRect(100, 100, 80, 210);
		
		switch(currentState) {
		
			case RED: 		g.setColor(new Color(255,0,0)); 
							g.fillOval(115, 115, 50, 50);
							g.setColor(new Color(255,255,0)); 
							g.drawOval(115, 180, 50, 50);
							g.setColor(new Color(0,255,0)); 
							g.drawOval(115, 245, 50, 50);
							break;
			case REDYELLOW: g.setColor(new Color(255,0,0)); 
			  		  		g.fillOval(115, 115, 50, 50);
			  		  		g.setColor(new Color(255,255,0)); 
		  		  			g.fillOval(115, 180, 50, 50);
		  		  		    g.setColor(new Color(0,255,0)); 
		  		  		    g.drawOval(115, 245, 50, 50);
			  		  	 	break;
			case GREEN: 	g.setColor(new Color(255,0,0)); 
							g.drawOval(115, 115, 50, 50);
							g.setColor(new Color(255,255,0)); 
							g.drawOval(115, 180, 50, 50);
							g.setColor(new Color(0,255,0)); 
							g.fillOval(115, 245, 50, 50);
							break;
			case YELLOW: 	g.setColor(new Color(255,0,0)); 
							g.drawOval(115, 115, 50, 50);
							g.setColor(new Color(255,255,0)); 
							g.fillOval(115, 180, 50, 50);
							g.setColor(new Color(0,255,0)); 
							g.drawOval(115, 245, 50, 50);
							break;
		
		}
		
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Dancing Text");
		
		frame.add(new TrafficLight());
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720, 540);
		frame.setVisible(true);
		
	}
	
}