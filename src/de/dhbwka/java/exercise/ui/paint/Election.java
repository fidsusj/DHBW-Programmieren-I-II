package de.dhbwka.java.exercise.ui.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Election extends JComponent{

	private ArrayList<Party> list;
	
	public Election(ArrayList<Party> list) {
		super();
		this.list = list;
	}
	
	protected void paintComponent(Graphics g) {
		
		g.setColor(new Color(51, 204, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(new Color(51, 51, 204));
		g.fillRect(0, 0, this.getWidth(), (int)(this.getHeight() * 0.05));
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", Font.BOLD, (int)((this.getHeight() * 0.05) - 0.1 * (this.getHeight() * 0.05))));
		g.drawString("BUNDESTAGSWAHL 2017", (int)(this.getWidth() * 0.02), (int)((this.getHeight() * 0.05) - (this.getHeight() * 0.05) * 0.15));
		g.drawString("in %", (int)(this.getWidth() * 0.90), (int)((this.getHeight() * 0.05) - (this.getHeight() * 0.05) * 0.15));
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, (int)(this.getHeight() * 0.86), this.getWidth(), (int)(this.getHeight() * 0.10));
		
		drawPartyPercentage(g, list);
		
	}
	
	private void drawPartyPercentage(Graphics g, ArrayList<Party> list ) {
		
		double max = maxValue();
		
		for (int i = 0; i < Character.getNumericValue(Double.valueOf(maxValue()).toString().charAt(0)); i++) {
			g.drawLine(0,
					   (int)((0.80*this.getHeight())-(((0.7*this.getHeight())/(Character.getNumericValue(Double.valueOf(maxValue()).toString().charAt(0) + 1)))*(i+1))),
					   this.getWidth(),
					   (int)((0.80*this.getHeight())-(((0.7*this.getHeight())/(Character.getNumericValue(Double.valueOf(maxValue()).toString().charAt(0) + 1)))*(i+1))));
			g.drawString(i*10 + 10 + "", (int)(this.getWidth() * 0.90), (int)((0.78*this.getHeight())-(((0.7*this.getHeight())/(Character.getNumericValue(Double.valueOf(maxValue()).toString().charAt(0) + 1)))*(i+1))));
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			Party party = list.get(i);
			
			g.setColor(party.getColor());
			g.fillRect((int)((this.getWidth() * 0.1 / (list.size()+1))*(i+1) + (this.getWidth() * 0.9 / (list.size()))*i),
					   (int)((this.getHeight() * 0.15) + ((1-(list.get(i).getPercentage() / max))*0.7*this.getHeight())),
					   (int)(this.getWidth() * 0.9 / (list.size())),
					   (int)(((list.get(i).getPercentage() / max))*0.7*this.getHeight()));
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Courier", Font.BOLD, (int)((this.getHeight() * 0.05) - 0.1 * (this.getHeight() * 0.1))));
			g.drawString(party.getName(),
						(int)((this.getWidth() * 0.1 / (list.size()+1))*(i+1) + (this.getWidth() * 0.9 / (list.size()))*i),
						(int)((this.getHeight() * 0.9) - 0.05 * (this.getHeight() * 0.01)));
			g.drawString(party.getPercentage() + "",
						(int)((this.getWidth() * 0.1 / (list.size()+1))*(i+1) + (this.getWidth() * 0.9 / (list.size()))*i),
						(int)((this.getHeight() * 0.95) - 0.05 * (this.getHeight() * 0.01)));
				
		}
		
		
	}
	
	private double maxValue() {
		double max = 0;
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getPercentage() > max)
				max = list.get(i).getPercentage();
		}
		
		return max;
		
	}
	
	public static void main(String[] args) {
	
		ArrayList<Party> list = new ArrayList<Party>();
		list.add(new Party(Color.BLACK, "Union", 33.0D));
		list.add(new Party(Color.RED, "SPD", 22.5D));
		list.add(new Party(Color.BLUE, "AfD", 12.6D));
		list.add(new Party(Color.YELLOW, "FDP", 10.7D));
		list.add(new Party(Color.PINK, "Linke", 9.2D));
		list.add(new Party(Color.GREEN, "Grüne", 8.9D));
		list.add(new Party(Color.BLACK, "Andere", 5.1D));

		JFrame frame = new JFrame("Wahl");
		frame.add(new Election(list));
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720, 540);
		frame.setVisible(true);
		
	}

}

class Party{
	
	private Color color;
	private String name;
	private double percentage;
	
	public Party(Color color, String name, double percentage) {
		
		this.color = color;
		this.name = name;
		this.percentage = percentage;
		
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public double getPercentage() {
		return percentage;
	}
	
}