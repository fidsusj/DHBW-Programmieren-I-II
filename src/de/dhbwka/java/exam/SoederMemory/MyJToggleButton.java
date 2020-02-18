package de.dhbwka.java.exam.SoederMemory;

import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class MyJToggleButton extends JToggleButton{

	private boolean open;
	private String id;
	
	public MyJToggleButton(String id) {
		super();
		this.id = id;
	}

	public boolean isOpen() {
		return open;
	}

	public String getId() {
		return id;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}