package de.dhbwka.java.exercise.ui.event;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class BinaryNumber extends JFrame implements ActionListener{

	private final ImageIcon imgOn = new ImageIcon("./images/on.png");
	private final ImageIcon imgOff = new ImageIcon("./images/off.png");
	private JLabel decimal;
	
	public BinaryNumber() {
		
		this.setTitle("Binary Number");
		this.setLayout(new BorderLayout(5,5));	
		JPanel panel = new JPanel();
		
		decimal = new JLabel("0");
		decimal.setFont(new Font("Courier", Font.BOLD, 40));
		panel.add(decimal);
		this.add(panel,BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,8,10,50));
		JToggleButton button;
		for (int i = 0; i < 8; i++) {
			button = new JToggleButton(imgOff);
			button.addActionListener(this);
			button.setActionCommand((7-i) + "");
			panel.add(button);
		}
		for (int i = 0; i < 8; i++) {
			JPanel innerPanel = new JPanel();
			JLabel label = new JLabel("2^" + (7-i));
			label.setFont(new Font("Courier", Font.BOLD, 20));
			innerPanel.add(label);
			panel.add(innerPanel);
		}
		this.add(panel,BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton togglebtn = (JToggleButton) e.getSource();
		if(togglebtn.isSelected()) {
			togglebtn.setIcon(imgOn);
			decimal.setText(
					(int)( Integer.parseInt(decimal.getText()) + 
					  Math.pow(2,Integer.parseInt(togglebtn.getActionCommand())))
					+ "");
		}
		else {
			togglebtn.setIcon(imgOff);
			decimal.setText(
					(int)( Integer.parseInt(decimal.getText()) - 
					  Math.pow(2,Integer.parseInt(togglebtn.getActionCommand())))
					+ "");
		}
	}
	
	public static void main(String[] args) {
		
		new BinaryNumber();
		
	}

}