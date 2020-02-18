package de.dhbwka.java.exercise.uebungsklausuren.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatSession {

	private String user;
	private Network network;
	
	private JFrame frame;
	private JButton nick;
	private JButton who;
	private JButton logoff;
	private JButton clear;
	private JTextArea chat;
	private JTextField text;
	
	public ChatSession(String name, Network network) {
		
		this.user = name;
		this.network = network;
		network.addChat(this);
		
		frame = new JFrame("#" + user);
		frame.setLayout(new BorderLayout(5,5));
		
		ButtonGroup btngroup = new ButtonGroup();
		JPanel panel = new JPanel();
		nick   = new JButton("Nick");
		who    = new JButton("Who?");
		logoff = new JButton("Logoff");
		clear  = new JButton("Clear");
		btngroup.add(nick);
		btngroup.add(who);
		btngroup.add(logoff);
		btngroup.add(clear);
		panel.add(nick);
		panel.add(who);
		panel.add(logoff);
		panel.add(clear);
		frame.add(panel, BorderLayout.NORTH);
		
		chat = new JTextArea();
		frame.add(new JScrollPane(chat), BorderLayout.CENTER);
		
		text = new JTextField();
		frame.add(text, BorderLayout.SOUTH);
		
		addListeners();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public void registerMessage(String message) {
		chat.append(message);
	}
	
	private void addListeners() {
		nick.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = JOptionPane.showInputDialog(frame, "How do you want to be named?", "Rename", JOptionPane.QUESTION_MESSAGE);
				
				for (Iterator<ChatSession> iterator = network.getSessions().iterator(); iterator.hasNext();) {
					ChatSession chat = (ChatSession) iterator.next();
					chat.registerMessage(user + " HAS CHANGED HIS NICK TO " + name + System.lineSeparator());
				}
				user = name;
				frame.setTitle("#" + user);
				
			}
		});
		
		who.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String users = "";
				for (Iterator<ChatSession> iterator = network.getSessions().iterator(); iterator.hasNext();) {
					ChatSession chat = (ChatSession) iterator.next();
					users += "#" + chat.getUser() + " ";
				}
				chat.append("INFO>> " + users + System.lineSeparator());
			}
		});
		
		logoff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				logoff();
				for (Iterator<ChatSession> iterator = network.getSessions().iterator(); iterator.hasNext();) {
					ChatSession chat = (ChatSession) iterator.next();
					chat.registerMessage(user + " HAS LOGGED OFF" + System.lineSeparator());
				}
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chat.setText("");
			}
		});
		
		text.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				network.send(text.getText(), user);	
				text.setText("");
			}
		});
		
	}
	
	private void logoff() {
		network.deleteChat(this);
	}
	
	private String getUser() {
		return user;
	}
	
}