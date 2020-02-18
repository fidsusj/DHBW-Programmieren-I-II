package de.dhbwka.java.exam.SoederMemory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import de.dhbwka.java.exam.SoederMemory.MemoryImages.MemoryImage;

public class MemoryGameTerm {

	private MemoryGame game;
	
	private JFrame frame;
	private List<JLabel> labels;
	private List<MyJToggleButton> buttons;
	
	private int counter;
	private String image1;
	private String image2;

	public MemoryGameTerm(MemoryGame game) {
		this.game = game;
		this.labels = new ArrayList<>();
		this.buttons = new ArrayList<>();
		
		this.frame = new JFrame("Soeder Memory (0)");
	
		buildGUI();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				int counter = 0;
				while (!MemoryGameTerm.this.game.isFinished()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					counter++;
					MemoryGameTerm.this.frame.setTitle("Soeder Memory (" + counter + ")");
				}
			}
		}).start();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(720, 480);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);
	}

	private void buildGUI() {
		
		List<Player> players = this.game.getPlayer();
		
		//North
		JPanel panelTop = new JPanel(new GridLayout(players.size(),1));
		for (int i = 0; i < players.size(); i++) {
			JLabel label = new JLabel();
			this.labels.add(label);
			panelTop.add(label);
		}
		this.frame.add(panelTop,BorderLayout.NORTH);
		
		//Center
		List<MemoryImage> images = this.game.getImages();
		
		JPanel panelCenter = new JPanel(new GridLayout(this.game.getRows(), this.game.getColumns()));
		
		for (int i = 0; i < images.size(); i++) {
			MyJToggleButton button = new MyJToggleButton(images.get(i).getId());
			button.addActionListener(e -> {
				handleButton(button);
			});
			button.setIcon(MemoryImages.getBackside());
			button.setSelectedIcon(images.get(i).getImage());
			button.setActionCommand(images.get(i).getId());
			this.buttons.add(button);
			panelCenter.add(button);
		}
		
		if(this.game.isBlankRequired()) {
			MyJToggleButton button = new MyJToggleButton("empty");
			button.addActionListener(e -> {
				handleButton(button);
			});
			button.setIcon(MemoryImages.getBackside());
			button.setSelectedIcon(MemoryImages.getBlank());
			this.buttons.add(button);
			panelCenter.add(button);
		}
		this.frame.add(panelCenter, BorderLayout.CENTER);
		
		updateUI();
		
	}
	
	private void handleButton(MyJToggleButton button) {
		if(this.game.isFinished()) {
			return;
		}
		if(this.counter == 0) {
			this.image1 = button.getId();
			button.setOpen(true);
			this.counter ++;
			return;
		}
		if(this.counter == 1) {
			this.image2 = button.getId();
			button.setOpen(true);
			this.counter ++;
		}
		if(counter == 2) {
			if(this.image1.equals(this.image2)) {
				this.game.getCurrentPlayer().addPoint();
				for (int j = 0; j < this.buttons.size(); j++) {
					if(this.buttons.get(j).isOpen()) {
						this.buttons.get(j).setEnabled(false);
						this.buttons.get(j).setOpen(false);
					}
				}
				this.game.matchFound();
			} else {
				JOptionPane.showMessageDialog(this.frame, "Wrong", "Sorry, those don't macth", JOptionPane.WARNING_MESSAGE);
				for (int j = 0; j < this.buttons.size(); j++) {
					if(this.buttons.get(j).isOpen()) {
						this.buttons.get(j).setSelected(false);
						this.buttons.get(j).setOpen(false);
					}
				}
				this.game.nextPlayer();
			}
			this.counter = 0;
			this.game.incrementCounter();
			
			if(this.game.isFinished()) {
				for (int j = 0; j < this.game.getPlayer().size(); j++) {
					this.game.getPlayer().get(j).setStatus(PlayerStatus.FINISHED);
				}
			}
			updateUI();
		}
	}
	
	private void updateUI(){
		List<Player> players = this.game.getPlayer();
		for (int i = 0; i < labels.size(); i++) {
			this.labels.get(i).setText(players.get(i).getName() + " (" + players.get(i).getPoints() + ")");
			this.labels.get(i).setForeground(players.get(i).getStatus().getColor());
		}
		this.frame.repaint();
	}

	public MemoryGame getGame() {
		return game;
	}

}