package de.dhbwka.java.exercise.uebungsklausuren.snatChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;

public class SnatChatWindow implements SnatChatFrontend{

	private SnatChatRoom room;
	private Account account;
	
	private JFrame frame;
	private ChatMessagesComponent window;
	private JTextField field;
	
	public SnatChatWindow(SnatChatRoom room, Account account) {
		this.room = room;
		this.account = account;
		
		this.frame = new JFrame(this.account.getName() + "(" + this.room.getRoomName() + ")");
		
		builGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
		
		this.room.register(this);
	}

	private void builGUI() {
		//Top
		JLabel name = new JLabel(this.account.getName(), JLabel.CENTER);
		name.setForeground(this.account.getColor());
		this.frame.add(name, BorderLayout.NORTH);
		
		//Middle
		this.window = new ChatMessagesComponent();
		this.frame.add(this.window, BorderLayout.CENTER);
		
		//South
		JPanel panelSouth = new JPanel(new GridLayout(2,1));
		JPanel panelRadio = new JPanel();
		ButtonGroup group = new ButtonGroup();
		for (State state : State.values()) {
			MyJRadioButton button = new MyJRadioButton(state.getLabel(), state);
			button.addActionListener(e -> {
				this.account.setState(button.getState());
				this.room.sendMessage(String.format("User '%s' is now in mode '%s'", this.account.getName(), this.account.getState().getLabel()));
			});
			group.add(button);
			panelRadio.add(button);
			if(state.equals(this.account.getState())) {
				button.setSelected(true);
			}
		}
		panelSouth.add(panelRadio);
		
		JPanel panelText = new JPanel(new BorderLayout());
		this.field = new JTextField();
		field.addActionListener(e -> {
			this.sendMessage();
		});
		panelText.add(this.field, BorderLayout.CENTER);
		JButton button = new JButton("Send");
		button.addActionListener(e -> {
			this.sendMessage();
		});
		panelText.add(button, BorderLayout.EAST);
		panelSouth.add(panelText);
		this.frame.add(panelSouth, BorderLayout.SOUTH);
	}
	
	private void sendMessage() {
		if(this.field.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this.frame, "Dear " + this.account.getName() + ", please enter a message", "Meldung", JOptionPane.WARNING_MESSAGE);
		} else {
			Message msg = new Message(this.field.getText(), this.account);
			this.room.sendMessage(msg);
			this.field.setText("");
		}
	}
	
	private void writeMessage(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setForeground(color);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 30; i > 0; i--) {
					label.setText("");
					label.setText(text + " [" + i + "]");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				SnatChatWindow.this.window.remove(label);
			}
		}).start();
		
		this.window.add(label);
	}

	public void receiveMessage(Message msg) {
		writeMessage(msg.toString(), msg.getAccount().getColor());
	}

	public void receiveMessage(String msg) {
		writeMessage(msg, Color.GRAY);
	}

	public Account getAccount() {
		return this.account;
	}
	
}