package de.dhbwka.java.exercise.uebungsklausuren.sNatter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SnatterBox implements SnatterFrontend{

	private SnatterService service;
	private User user;
	private List<String> taglist;
	private List<String> bannedTags;
	
	private JFrame frame;
	private JMenu follow;
	private JTextArea history;
	private JPanel taglistPanel;
	
	public SnatterBox(SnatterService service, User user) {
		this.service = service;
		this.user = user;
		this.taglist = new ArrayList<>();
		this.bannedTags = new ArrayList<>();	
		
		this.frame = createGUI(); 

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
		
		service.connect(this);
	}
	
	private JFrame createGUI() {
		JFrame frame = new JFrame(this.user.getsNatterName() + " @ sNatter");
		JMenuBar menu = new JMenuBar();
		
		//First Menu
		JMenu sNatter = new JMenu("sNatter");
		JMenuItem clear = new JMenuItem("Clear Taglist");
		clear.addActionListener(e -> {
			for (int i = 0; i < this.taglist.size(); i++) {
				this.bannedTags.add(this.taglist.get(i));
			}
			this.taglist = new ArrayList<>();
			this.taglistPanel.removeAll();
			this.taglistPanel.updateUI();
		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e -> {
			this.service.disconnect(SnatterBox.this);
			SnatterBox.this.frame.dispose();
		});
		sNatter.add(clear);
		sNatter.add(new JSeparator());
		sNatter.add(exit);
		menu.add(sNatter);
		
		
		//Second Menu
		this.follow = new JMenu("Follow");
		updateUserList();
		menu.add(this.follow);
		frame.setJMenuBar(menu);
		
		//Message Input Field
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout());
		JTextField message = new JTextField(50);
		message.addActionListener(e -> {
				JTextField src = (JTextField) e.getSource();
				Message msg = new Message(src.getText(), user);
				SnatterBox.this.service.send(msg);
				src.setText("");
		});
		panelTop.add(new JLabel("Message: "));
		panelTop.add(message);
		frame.add(panelTop, BorderLayout.NORTH);
		
		//Chat History
		this.history = new JTextArea(20,50);
		this.history.setEditable(false);
		this.history.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.history);
		frame.add(scroll, BorderLayout.CENTER);
		
		//Tag List 
		this.taglistPanel = new JPanel();
		frame.add(this.taglistPanel, BorderLayout.SOUTH);
		
		return frame;
	}
	
	private void handleHashTags() {
			
		Collections.sort(this.taglist);
		
		for (int i = 0; i < this.taglist.size(); i++) {
			String hashtag = this.taglist.get(i);
			JButton button = new JButton(hashtag);
			button.addActionListener(e -> {
				this.taglist.remove(hashtag);
				this.bannedTags.add(hashtag);
				this.taglistPanel.remove(button);
				this.taglistPanel.updateUI();
			});
			this.taglistPanel.add(button);
		}
		this.taglistPanel.updateUI();
			
	}

	public void receiveMessage(Message msg) {
		if(!msg.getUser().equals(this.user)) {
			String message = "@" + msg.getUser().getsNatterName() + ": " + msg.getText();
			this.history.append(message + System.lineSeparator());
		}
		
		String[] words = msg.getText().split(" ");
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			
			if(word.startsWith("#") && word.length() >= 4) {
				boolean correctHashTag = true;
				for (int j = 1; j < word.length(); j++) {
					if(!(Character.isAlphabetic(word.charAt(j)) || Character.isDigit(word.charAt(j)))){
						correctHashTag = false;
					}
				}
				if(correctHashTag) {
					if(!this.taglist.contains(word) && !this.bannedTags.contains(word)) {
						this.taglist.add(word);
						handleHashTags();
					}
					else {
						new Thread(() -> {
								SnatterBox.this.history.setBackground(Color.RED);
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								SnatterBox.this.history.setBackground(Color.WHITE);
						}).start();	
					}
				}				
			}
		}	
	}

	public void receiveMessage(String text) {
		this.history.append(text.toUpperCase() + System.lineSeparator());
	}

	public void updateUserList() {
		List<User> users = service.getUserList();
		this.follow.removeAll();
		Collections.sort(users, (o1, o2) -> {
				return o1.getsNatterName().compareTo(o2.getsNatterName());
		});
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if(!user.equals(this.user)) {
				this.follow.add(new JMenuItem(user.getsNatterName() + " (" + user.getName() + ")"));
			}
		}
	}

	public User getUser() {
		return this.user;
	}

}