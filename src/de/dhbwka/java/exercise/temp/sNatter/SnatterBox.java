package de.dhbwka.java.exercise.temp.sNatter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.*;

public class SnatterBox implements SnatterFrontend {

	private SnatterService service;
	private User user;
	
	private List<String> currentTags;
	private List<String> bannedTags;
	
	private JFrame frame;
	private JMenu sNatter;
	private JMenu follow;
	private JTextField textfield;
	private JTextArea area;
	private JPanel tagPanel;
	
	public SnatterBox(SnatterService service, User user) {
		this.service = service;
		this.user = user;
		
		this.currentTags = new ArrayList<>();
		this.bannedTags = new ArrayList<>();
		
		
		this.frame = new JFrame(this.user.getsNatterName() + " @ sNatter");
		this.frame.setLayout(new BorderLayout(10,10));
		
		buildGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
		
		this.service.connect(this);
	}

	private void buildGUI() {
		
		//Menu
		JMenuBar menubar = new JMenuBar();
		this.sNatter = new JMenu("sNatter");
		JMenuItem clearTagList = new JMenuItem("Clear Taglist");
		clearTagList.addActionListener(e -> {
			this.tagPanel.removeAll();
			this.currentTags.forEach(bannedTags::add);
			this.currentTags = new ArrayList<>();
			this.tagPanel.updateUI();
		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e -> {
			this.service.disconnect(this);
			this.frame.dispose();
		});
		this.sNatter.add(clearTagList);
		this.sNatter.add(new JSeparator());
		this.sNatter.add(exit);
		menubar.add(this.sNatter);
		this.follow = new JMenu("Follow");
		updateUserList();
		menubar.add(this.follow);
		this.frame.setJMenuBar(menubar);
		
		//PanelTop
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(new JLabel("Message:"), BorderLayout.WEST);
		this.textfield = new JTextField();
		this.textfield.addActionListener(e -> {
			this.service.send(new Message(((JTextField)e.getSource()).getText(), this.user));
			this.textfield.setText("");
		});
		panelTop.add(this.textfield, BorderLayout.CENTER);
		this.frame.add(panelTop, BorderLayout.NORTH);
		
		//PanelCenter
		this.area = new JTextArea();
		this.area.setEditable(false);
		this.frame.add(new JScrollPane(this.area), BorderLayout.CENTER);
		
		//PanelSouth
		this.tagPanel = new JPanel();
		this.frame.add(this.tagPanel, BorderLayout.SOUTH);

	}

	public void receiveMessage(Message msg) {
		User user = msg.getUser();
		if(!user.equals(this.user)) {
			this.area.setText(this.area.getText() + "@" + user.getsNatterName() + ": " + msg.getText() + System.lineSeparator());
		}
		handleHashTags(msg);
	}

	public void receiveMessage(String msg) {
		this.area.setText(this.area.getText() + msg + System.lineSeparator());
	}
	
	private void handleHashTags(Message msg) {
		
		String[] words = msg.getText().split(" ");
		Stream.of(words).filter(e -> {
			return e.startsWith("#");
		}).filter(e -> {
			if(e.length() < 4 || this.bannedTags.contains(e)) {
				return false;
			}
			if(this.currentTags.contains(e)) {
				new Thread(() -> {
					this.area.setBackground(Color.RED);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					this.area.setBackground(Color.WHITE);
				}).start();
				return false;
			}
			boolean valid = true;
			for (int i = 1; i < 4; i++) {
				if(!(Character.isAlphabetic(e.charAt(i)) || Character.isDigit(e.charAt(i)))) {
					valid = false;
					break;
				}
			}
			return valid;
		}).sorted((o1,o2) -> {
			return o1.compareTo(o2);
		}).forEach(this.currentTags::add);
		
		this.tagPanel.removeAll();
		
		Stream.of(this.currentTags.toArray()).sorted((o1,o2) -> {
			return ((String) o1).compareTo((String) o2);
		}).forEach(e -> {
			JButton button = new JButton(((String) e).toUpperCase());
			button.setActionCommand((String) e);
			button.addActionListener(d -> {
				if(!this.bannedTags.contains(d.getActionCommand()))
					this.bannedTags.add(d.getActionCommand());
				this.tagPanel.remove(button);
				this.tagPanel.updateUI();
			});
			this.tagPanel.add(button);
		});
		
		this.tagPanel.updateUI();
	}

	public void updateUserList() {
		List<User> users = service.getUserList();
		Collections.sort(users, new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getsNatterName().compareTo(o2.getsNatterName());
			}
		});
		this.follow.removeAll();
		for (User user : users) {
			this.follow.add(new JMenuItem(String.format("%s (%s)", user.getsNatterName(), user.getName())));
		}
		this.follow.updateUI();
	}

	public User getUser() {
		return this.user;
	}
	
}