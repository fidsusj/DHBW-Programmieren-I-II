package de.dhbwka.java.exercise.threads;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class DancingText extends JComponent{
	
	public DancingText() {
		super();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	protected void paintComponent(Graphics g) {
		
		String text = "Dancing Text! :-)";
		for (int i = 0; i < text.length(); i++) {
			g.setColor(getRandomColor());
			g.setFont(new Font("Courier", Font.BOLD, 50));
			g.drawString(text.charAt(i) + "", 50 + 35*i, 250 + getRandomYPOS());
		}
		
	}
	
	private Color getRandomColor() {
		Random rdm = new Random();
		return new Color(rdm.nextInt(256),rdm.nextInt(256),rdm.nextInt(256));
	}
	
	private int getRandomYPOS() {
		Random rdm = new Random();
		return rdm.nextInt(101)-50;
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Dancing Text");
		
		frame.add(new DancingText());
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720, 540);
		frame.setVisible(true);
		
	}
	
}