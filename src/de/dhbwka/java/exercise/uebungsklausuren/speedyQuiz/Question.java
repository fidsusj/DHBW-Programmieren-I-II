package de.dhbwka.java.exercise.uebungsklausuren.speedyQuiz;

public class Question {

	private String text;
	private String[] answers;
	private int correctAnswer;
	
	public Question(String text, String[] answers, int correctAnswer) {
		this.text = text;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	public String getText() {
		return text;
	}

	public String[] getAnswers() {
		return answers;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
}