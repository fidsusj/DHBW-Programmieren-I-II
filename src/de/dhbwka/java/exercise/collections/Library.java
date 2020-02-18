package de.dhbwka.java.exercise.collections;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Library extends JFrame implements ActionListener {

	private JTextField[] textfields;
	private JButton[] buttons;
	private List<Book> books;
	
	public Library() {
		
		books = new ArrayList<Book>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader("./files/library/library.txt"))){
			while(reader.ready()) {
				String title = reader.readLine();
				String author = reader.readLine();
				int year = Integer.valueOf(reader.readLine()).intValue();
				String publisher = reader.readLine();
				books.add(new Book(title, author, year, publisher));
			}
		} catch (IOException | NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Something went wrong during the initialisation");
		}
		
		this.setTitle("Library");
		textfields = new JTextField[4];
		
		buttons = new JButton[5];
		buttons[0] = new JButton("Save entry");
		buttons[1] = new JButton("Title");
		buttons[2] = new JButton("Author");
		buttons[3] = new JButton("Year");
		buttons[4] = new JButton("Publisher");
		
		JLabel[] labels = new JLabel[4];
		labels[0] = new JLabel("Title");
		labels[1] = new JLabel("Author");
		labels[2] = new JLabel("Year");
		labels[3] = new JLabel("Publisher");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 5, 5));
		for (int i = 0; i < 4; i++) {
			textfields[i] = new JTextField(20);
			panel.add(labels[i]);
			panel.add(textfields[i]);
		}
		this.add(panel, BorderLayout.NORTH);
		
		buttons[0].addActionListener(this);
		this.add(buttons[0], BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.add(new JLabel("Ordered output:"));
		for (int i = 1; i < 5; i++) {
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		this.add(panel, BorderLayout.SOUTH);		
		
		this.setLocationRelativeTo(null);
		this.setSize(640, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		//Library.this zum referenzieren aus anonymen inneren Klassen
		switch (((JButton) e.getSource()).getText()) {
			case "Save entry":
				for (int i = 0; i < textfields.length; i++) {
					if(textfields[i].getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Please  enter all required Information");
						return;
					}
				}
				
				try {
					Book book = new Book(textfields[0].getText(),textfields[1].getText(), Integer.valueOf(textfields[2].getText()).intValue(),textfields[3].getText());
					if(!books.contains(book)) {
						try(Writer writer = new FileWriter("./files/library/library.txt", true)){
							for (int i = 0; i < textfields.length; i++) {
								writer.write(textfields[i].getText() + System.lineSeparator());
							}
						}catch(IOException ex) {
							JOptionPane.showMessageDialog(this, "Something went wrong during saving process");
						}
						books.add(book);
					}
					else {
						JOptionPane.showMessageDialog(this, "This book is already saved in the collection");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Please enter the year in the correct format");
				}
				
				for (int i = 0; i < textfields.length; i++) {
					textfields[i].setText("");
				}
				
				break;
			case "Title":
				Collections.sort(books, new BookComparator(SortCriteria.Title));
				JOptionPane.showMessageDialog(this, toString(), "Books ordered by Title", JOptionPane.INFORMATION_MESSAGE);
	  			break;
			case "Author":
				Collections.sort(books, new BookComparator(SortCriteria.Author));
				JOptionPane.showMessageDialog(this, toString(), "Books ordered by Author", JOptionPane.INFORMATION_MESSAGE);
	  			break;
			case "Year":
				Collections.sort(books, new BookComparator(SortCriteria.Year));
				JOptionPane.showMessageDialog(this, toString(), "Books ordered by Year", JOptionPane.INFORMATION_MESSAGE);
	  			break;
			case "Publisher":
				Collections.sort(books, new BookComparator(SortCriteria.Publisher));
				JOptionPane.showMessageDialog(this, toString(), "Books ordered by Publisher", JOptionPane.INFORMATION_MESSAGE);
	  			break;
		}
		
	}
	
	public String toString() {
		String content = "";
		for (Book buch : books) {
			content +=  buch.toString() + System.lineSeparator();
		}
		return content;
	}
	
	public static void main(String[] args) {
		new Library();
	}
	
}