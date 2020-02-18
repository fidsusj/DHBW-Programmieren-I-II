package de.dhbwka.java.exercise.uebungsklausuren.QUp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.*;

public class QueueDisplay implements QueueObserver{

	private WaitingQueue queue;
	
	private JFrame frame;
	private JLabel labelTop;
	private JLabel labelBottom;
	private JScrollPane panelCenter;
	
	private boolean needthread;
	private boolean alreadyrunning;
	
	public QueueDisplay(WaitingQueue queue) {
		this.queue = queue;
		this.queue.addQueueObserver(this);
		this.needthread = true;
		
		this.frame = new JFrame("");
		
		buildGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
		
	}

	private void buildGUI() {
		
		JPanel panelTop = new JPanel(new GridLayout(2,1));
		this.labelTop = new JLabel("", JLabel.CENTER);
		this.labelTop.setFont(new Font("Courier", Font.BOLD, 20));
		panelTop.add(this.labelTop);
		this.labelBottom = new JLabel();
		this.labelBottom.setFont(new Font("Courier", Font.BOLD, 14));
		panelTop.add(this.labelBottom);
		this.frame.add(panelTop, BorderLayout.NORTH);
		
		this.panelCenter = new JScrollPane(new JPanel());
		this.frame.add(this.panelCenter, BorderLayout.CENTER);
		
		JButton button = new JButton("Nummer ziehen");
		button.addActionListener(e -> {
			JOptionPane.showMessageDialog(this.frame, "Sie haben Nummer " + this.queue.getNumber(), "Melrdung", JOptionPane.INFORMATION_MESSAGE);
			this.queue.updateObservers();
		});
		this.frame.add(button, BorderLayout.SOUTH);
		
		this.updateView();
		
	}

	public void updateView() {
		
		Call lastCall = this.queue.getLastCall();
		this.labelTop.setText(lastCall == null ? "---" : lastCall.toString());
		
		this.labelBottom.setText("Noch " + this.queue.getWaitingClients() + " Kunden im Wartebereich.");
		
		List<Call> calls = this.queue.getCurrentCalls();
		
		JPanel panelCenter = new JPanel(new GridLayout(calls.size(), 1));
		for (int i = 0; i < calls.size(); i++) {
			panelCenter.add(new JLabel(calls.get(i).toString()));
		}
		this.panelCenter.setViewportView(panelCenter);
		
		this.needthread = (calls.size() > 1 ? true : false);
		
		if(this.needthread == true && !this.alreadyrunning) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					QueueDisplay.this.alreadyrunning = true;
					while(QueueDisplay.this.needthread) {
						List<Call> calls = QueueDisplay.this.queue.getCurrentCalls();
						while(calls.size() > 1) {
							for (int i = 0; i < calls.size(); i++) {
								QueueDisplay.this.labelTop.setText(calls.get(i).toString());
								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
					QueueDisplay.this.alreadyrunning = false;
				}
			}).start();;
		}
		
	}
	
}