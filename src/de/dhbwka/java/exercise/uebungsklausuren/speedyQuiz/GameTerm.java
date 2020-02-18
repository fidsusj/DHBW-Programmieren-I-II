package de.dhbwka.java.exercise.uebungsklausuren.speedyQuiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GameTerm implements GameClient{

	private String name;
	private Game game;
	private int points;
	private Question question;
	
	private JFrame frame;
	private List<QuestionNumerLabel> labels;
	private JLabel questionLabel;
	private JLabel time;
	private List<JButton> buttons;
	
	
	public GameTerm(String name, Game game) {
		
		this.name = name;
		this.game = game;
		
		this.frame = new JFrame(this.name);
		
		createGUI();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(500, 200);
		this.frame.setLocation((int) ((dimension.getWidth() - this.frame.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.frame.getHeight()) / 2));
		this.frame.setVisible(true);

	}

	private void createGUI() {
		
		this.labels = new ArrayList<>();
		this.buttons = new ArrayList<>();
		int amountQuestions = this.game.getQuestionsCount();
		
		JPanel panelNorth = new JPanel(new GridLayout(1,amountQuestions));
		for (int i = 0; i < amountQuestions; i++) {
			QuestionNumerLabel label = new QuestionNumerLabel((i+1 + ""));
			this.labels.add(label);
			panelNorth.add(label);
		}
		this.frame.add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel(new GridLayout(2,1));
		this.questionLabel = new JLabel("", JLabel.CENTER);
		this.time = new JLabel("10", JLabel.CENTER);
		panelCenter.add(this.questionLabel);
		panelCenter.add(this.time);
		this.frame.add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel(new GridLayout(2,2));
		for (int i = 0; i < 4; i++) {
			JButton button = new JButton();
			button.setActionCommand(i + "");
			button.addActionListener(e -> {
				this.game.answerSelected(GameTerm.this, Integer.parseInt(e.getActionCommand()));
			});
			this.buttons.add(button);
			panelSouth.add(button);
		}
		this.frame.add(panelSouth, BorderLayout.SOUTH);
		
	}

	@Override
	public String getPlayerName() {
		return this.name;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setQuestion(int questionIndex, Question question) {
		this.question = question;
		
		this.questionLabel.setText(this.question.getText());
		this.labels.get(questionIndex).setStatus(Status.ACTIVE);
		String[] answers = this.question.getAnswers();
		for (int i = 0; i < answers.length; i++) {
			this.buttons.get(i).setText(answers[i]);
		}
		
	}

	@Override
	public void setRemainingSeconds(int seconds) {
		if(seconds >= 0)
			this.time.setText(seconds + "");
	}

	@Override
	public void gameIsOver() {
		for (int i = 0; i < this.buttons.size(); i++) {
			this.buttons.get(i).setEnabled(false);
		}
	}

	@Override
	public void setAnswerState(int questionIndex, Status status) {
		this.labels.get(questionIndex).setStatus(status);
		this.points += status.getPoint();
		if(this.points < 0)
			this.points = 0;
	}

}