package de.dhbwka.java.exercise.uebungsklausuren.speedyQuiz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;


public class Game {

	private boolean running;
	private long startTime;
	private int currentQuestionIndex;
	
	private List<Question> questions;
	private List<GameClient> clients;
	
	public Game(List<Question> questions, int amountQuestions) throws GameException {
		
		if(questions.size() < amountQuestions) {
			throw new GameException("Too few questions available");
		}
		
		this.currentQuestionIndex = -1;
		this.questions = new ArrayList<>();
		this.clients = new ArrayList<>();

		Collections.shuffle(questions);
		for (int i = 0; i < amountQuestions; i++) {
			this.questions.add(questions.get(i));
		}
		
	}
	
	public void registerClient(GameClient client) {
		if(!this.clients.contains(client) && !this.running) {
			this.clients.add(client);
		}
	}
	
	public void startGame() {
		this.running = true;
		this.startTime = System.currentTimeMillis();
		nextQuestion();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (Game.this.running) {
					int counter = 10;
					int startIndex = Game.this.currentQuestionIndex;
					while (counter >= 0 && startIndex == Game.this.currentQuestionIndex) {
						for (int i = 0; i < Game.this.clients.size(); i++) {
							Game.this.clients.get(i).setRemainingSeconds(counter);
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						counter--;
					}
					if(counter == -1)
						answerSelected(null, Game.this.currentQuestionIndex);
				}
			}
		}).start();
		
	}
	
	public void answerSelected(GameClient client, int index) {
		for (int i = 0; i < this.clients.size(); i++) {
			GameClient cli = this.clients.get(i);
			if(cli.equals(client)) {
				if(this.questions.get(this.currentQuestionIndex).getCorrectAnswer() == index) {
					cli.setAnswerState(this.currentQuestionIndex, Status.CORRECT);
				}
				else {
					cli.setAnswerState(this.currentQuestionIndex, Status.WRONG);
				}
			} else {
				cli.setAnswerState(this.currentQuestionIndex, Status.NO_ANSWER);
			}
		}
		this.nextQuestion();
	}
	
	public int getQuestionsCount() {
		return this.questions.size();
	}

	public Question getCurrentQuestion() {
		return this.questions.get(currentQuestionIndex);
	}

	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}
	
	private void nextQuestion() {
		if(this.currentQuestionIndex < this.questions.size() - 1) {
			this.currentQuestionIndex++;
			for (int i = 0; i < this.clients.size(); i++) {
				this.clients.get(i).setQuestion(this.currentQuestionIndex, this.questions.get(currentQuestionIndex));
			}
		} else {
			this.running = false;
			for (int i = 0; i < this.clients.size(); i++) {
				this.clients.get(i).gameIsOver();
			}
			
			String message = "Game has finished after " + ((System.currentTimeMillis() - startTime)/1000) + " seconds, score: ";
			String[] playerScore = new String[this.clients.size()];
			for (int i = 0; i < this.clients.size(); i++) {
				GameClient cli = this.clients.get(i);
				playerScore[i] = cli.getPlayerName() + " (" + cli.getPoints() + ")";
			}
			message += String.join(", ", playerScore);
			
			JOptionPane.showMessageDialog(null, message, "Medlung", JOptionPane.INFORMATION_MESSAGE);

			File fileDir = new File("./files/exam");
			fileDir.mkdirs();
			File file = new File(fileDir, "highscore.txt");
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Error while trying to create new file");
			}

			try (Writer writer = new FileWriter(file, true)) {
				writer.write(message + System.lineSeparator());
			} catch (IOException e) {
				System.err.println("Error while trying to write into file");
				e.printStackTrace();
			}
		}
	}
	
}