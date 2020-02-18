package de.dhbwka.java.exercise.ui.event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BMICalculator implements ActionListener{

	private JFrame frame;
	private JTextField weight;
	private JTextField height;
	
	private JRadioButton male;
	private JRadioButton female;
	private JButton calc;
	
	private JTextField bmi;
	private JTextField analysis;
	
	public BMICalculator() {
		
		JFrame frame = new JFrame("BMI Calculator");
		frame.setLayout(new GridLayout(6,1));
		
		JPanel panel = new JPanel();
		weight = new JTextField(10);
		weight.addActionListener(this);
		panel.add(new JLabel("Weight[kg]"));
		panel.add(weight);
		frame.add(panel);
		
	    panel = new JPanel();
		height = new JTextField(10);
		height.addActionListener(this);
		panel.add(new JLabel("Body Height [m]"));
		panel.add(height);
		frame.add(panel);
		
		panel = new JPanel();
		ButtonGroup buttongroup = new ButtonGroup();
		male = new JRadioButton("male");
		male.setSelected(true);
		female = new JRadioButton("female");
		buttongroup.add(male);
		buttongroup.add(female);
		panel.add(male);
		panel.add(female);
		frame.add(panel);
		
		panel = new JPanel();
		calc = new JButton("Calculate");
		calc.addActionListener(this);
		panel.add(calc);
		frame.add(panel);
		
		panel = new JPanel();
		bmi = new JTextField(10);
		panel.add(new JLabel("BMI"));
		panel.add(bmi);
		frame.add(panel);
		
		panel = new JPanel();
		analysis = new JTextField(20);
		panel.add(analysis);
		frame.add(panel);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(340,240);
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			double calc = (Double.parseDouble(weight.getText()) / Math.pow(Double.parseDouble(height.getText()), 2));
			bmi.setText((Math.ceil(calc*100)/100) + "");
			
			if(male.isSelected()) {
				if((int)Math.round(calc) < 20)
					analysis.setText("Short weight");
				else if((int)Math.round(calc) <= 25)
					analysis.setText("Normal weight");
				else if((int)Math.round(calc) <= 30)
					analysis.setText("Overweight");
				else if((int)Math.round(calc) <= 40)
					analysis.setText("Adiposity");
				else
					analysis.setText("Massive Adiposity");
			}
			else {
				if((int)Math.round(calc) < 19)
					analysis.setText("Short weight");
				else if((int)Math.round(calc) <= 24)
					analysis.setText("Normal weight");
				else if((int)Math.round(calc) <= 30)
					analysis.setText("Overweight");
				else if((int)Math.round(calc) <= 40)
					analysis.setText("Adiposity");
				else
					analysis.setText("Massive Adiposity");
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Bitte tragen sie nur Zahlen in die entsprechenden Felder ein!");
		}
	}
	
	public static void main(String[] args) {
	
		new BMICalculator();
		
	}

}