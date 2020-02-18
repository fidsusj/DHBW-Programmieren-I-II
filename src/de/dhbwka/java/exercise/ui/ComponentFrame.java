package de.dhbwka.java.exercise.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ComponentFrame extends JFrame{
	
	//Warum schließen sich zwei Fenster gleichzeitig?
	
	public ComponentFrame() {
		
		this.setTitle("Swing-Grundkomponenten");
		this.add(getComponentPanel());
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
	}
	
	private JPanel getComponentPanel() {
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("JLabel");
		JTextField textField = new JTextField(8);
		JPasswordField passwordField = new JPasswordField(8);
		JButton button = new JButton("JButton");
		JToggleButton toggleButton = new JToggleButton("JToggleButton");
		JCheckBox checkBox = new JCheckBox("JCheckBox");
		
		// Auch String Array bei der Übergabe als Parameter im Konstruktor möglich 
		JComboBox<String> comboBox = new JComboBox<String>();
		ButtonGroup buttonGroup = new ButtonGroup();
		
		textField.setText("JTextField");
		button.createToolTip();
		button.setToolTipText("ToolTip");
		toggleButton.createToolTip();
		toggleButton.setToolTipText("ToolTip");
		
		for (int i = 0; i < 4; i++) {
			comboBox.addItem("Item " + i);
		}
		
		panel.add(label);
		panel.add(textField);
		panel.add(passwordField);
		panel.add(button);
		panel.add(toggleButton);
		panel.add(checkBox);
		panel.add(comboBox);
		
		for (int i = 1; i < 4; i++) {
			JRadioButton radioButton = new JRadioButton("Radio-Button" + i);
			buttonGroup.add(radioButton);
			panel.add(radioButton);
		}
		
		return panel;
		
	}
	
	public static void main(String[] args) {
		new ComponentFrame();
	}
	
}