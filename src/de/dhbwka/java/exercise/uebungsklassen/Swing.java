package de.dhbwka.java.exercise.uebungsklassen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class Swing {

	public Swing() {

		// Frame mit Main Container
		JFrame frame = new JFrame();
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout(10, 10));

		// Norden
		JPanel north = new JPanel();
		north.add(new JLabel("Username"));
		north.add(new JTextField(20));
		main.add(north, BorderLayout.NORTH);

		// Süden
		JTextArea area = new JTextArea();
		main.add(area, BorderLayout.SOUTH);

		// Westen
		JColorChooser cj = new JColorChooser();
		main.add(cj, BorderLayout.WEST);

		// Osten
		JButton button = new JButton("Yo do something");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opt = { "HYFR", "Nah Nah Nigga" };
				int state = JOptionPane.showOptionDialog(frame,
						"Do you really like to choose this or color one of the following colors?", "Hi Kevin",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]);
				if (state == JOptionPane.YES_OPTION) {
					main.setBackground(cj.getColor());
					String[] options = { "Blue", "Red" };
					String state2 = (String) JOptionPane.showInputDialog(frame,
							"Do you want to change the color again?", "Was zur Hölle passiert hier",
							JOptionPane.ERROR_MESSAGE, null, options, options[1]);
					if (state2.equalsIgnoreCase("blue"))
						main.setBackground(Color.BLUE);
				}
			}
		});
		main.add(button, BorderLayout.EAST);

		// Center
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout(5, 5));

			// Center-West
			center.add(new JLabel("Please select a file: "), BorderLayout.WEST);
	
			// Center-Center
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileFilter() {
	
				@Override
				public String getDescription() {
					return "TEXT FILES ONLY";
				}
	
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getAbsolutePath().toLowerCase().endsWith(".txt");
				}
			});
	
			fc.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
						try (BufferedReader reader = new BufferedReader(
								new FileReader(fc.getSelectedFile().getAbsolutePath()))) {
							while (reader.ready()) {
								area.append(reader.readLine() + System.lineSeparator());
							}
						} catch (IOException io) {
							io.printStackTrace();
						}
					}
				}
			});
	
			center.add(fc, BorderLayout.CENTER);
			
			// Center-Osten
			JButton submit = new JButton("Submit here");
			submit.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					JDialog dia = new JDialog(frame, "Test", true);
					JMenuBar menubar = new JMenuBar();
					menubar.add(new JMenu("Datei"));
					menubar.add(new JMenu("Hilfe"));
					dia.setJMenuBar(menubar);
					dia.add(new JLabel("Easter Egg"), BorderLayout.CENTER);
					dia.pack();
					dia.setVisible(true);
				}
			});
		
		
		center.add(submit, BorderLayout.EAST);
		main.add(center, BorderLayout.CENTER);

		
		//Der Rest
		frame.add(new JScrollPane(main));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		new Swing();

	}

}