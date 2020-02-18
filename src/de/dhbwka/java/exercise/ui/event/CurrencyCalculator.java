package de.dhbwka.java.exercise.ui.event;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CurrencyCalculator extends JFrame implements ActionListener{
	
	private JTextField textfield;
	private JButton eur;
	private JButton usd;
	private JButton cancel;
	
	private final double course = 1.14D;
	
	public CurrencyCalculator() {
		
		this.setTitle("Currency converter");
		this.setLayout(new BorderLayout(10,10));
		
		textfield = new JTextField(10);
		textfield.setText("Please enter amount to convert!");
		// .addActionListener(clicked -> toUSD()); Lambda Expression
		textfield.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	            textfield.setText("");
	            textfield.setForeground(new Color(0,0,0));
	        }

			@Override
			public void focusLost(FocusEvent arg0) {
				return;
			}
	    });
		this.add(textfield,BorderLayout.NORTH);
		
		eur = new JButton("EUR => USD");
		eur.setActionCommand("toUSD");
		eur.addActionListener(this);
		usd = new JButton("USD => EUR");
		usd.setActionCommand("toEUR");
		usd.addActionListener(this);
	    cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		
		this.add(eur,BorderLayout.WEST);
		this.add(usd,BorderLayout.CENTER);
		this.add(cancel,BorderLayout.EAST);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,100);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			switch(e.getActionCommand()) {
				case "toUSD":  textfield.setText(Double.parseDouble(textfield.getText())*course + "");
							   break;
				case "toEUR":  textfield.setText(Double.parseDouble(textfield.getText())/course + "");
							   break;
				case "cancel": System.exit(0);
							   break;
			}
		}
		catch(NumberFormatException ex) {
			textfield.setText("Bitte geben Sie einen gültigen Zahlenwert ein!");
			textfield.setForeground(new Color(255,0,0));
		}
	}
	
	public static void main(String[] args) {

		new CurrencyCalculator();
		
	}

}