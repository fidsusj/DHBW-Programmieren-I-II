package de.dhbwka.java.exercise.uebungsklausuren.speedyQuiz;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class QuestionNumerLabel extends JLabel{

	private Status status;
	
	public QuestionNumerLabel() {
		super();
		setStatus(Status.PENDING);
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public QuestionNumerLabel(String text) {
		super(text);
		setStatus(Status.PENDING);
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void setStatus(Status status) {
		this.status = status;
		this.setBackground(this.status.getColor());
	}
	
}