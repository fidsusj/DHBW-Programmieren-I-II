package de.dhbwka.java.exercise.ui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultEditorKit;

public class Editor implements ActionListener{

	private JFrame frame;
	private JMenuItem neu;
	private JMenuItem oeffnen;
	private JMenuItem speichern;
	private JMenuItem beenden;
	private JMenuItem ersetzen;
	
	private JTextPane editPane;
	private File file;
	
	public Editor() {
		frame = new JFrame("Editor");
		frame.setJMenuBar(createMenuBar());
		
		editPane = new JTextPane();
		editPane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, System.lineSeparator());
		JScrollPane scrollPane = new JScrollPane(editPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(scrollPane);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
	
	private JMenuItem createJMenuItem(String name, boolean enabled, ActionListener act, int accelerator) {
		JMenuItem menuItem = new JMenuItem(name);
		KeyStroke shortcut = KeyStroke.getKeyStroke(accelerator, KeyEvent.CTRL_DOWN_MASK);
		menuItem.setEnabled(enabled);
		menuItem.addActionListener(act);
		menuItem.setAccelerator(shortcut);
		return menuItem;
	}
	
	private JMenuItem createJMenuItem(String name, boolean enabled) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setEnabled(enabled);
		return menuItem;
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menubar = new JMenuBar();

		JMenu datei = new JMenu("Datei");
		JMenu bearbeiten = new JMenu("Bearbeiten");
		JMenu senden = new JMenu("Senden an");
		menubar.add(datei);
		menubar.add(bearbeiten);

		neu = createJMenuItem("Neu", true, this, KeyEvent.VK_N);
		datei.add(neu);
		oeffnen = createJMenuItem("Öffnen", true, this, KeyEvent.VK_O);
		datei.add(oeffnen);
		datei.add(new JSeparator());
		datei.add(createJMenuItem("Schließen", false));
		datei.add(new JSeparator());
		speichern = createJMenuItem("Speichern", true, this, KeyEvent.VK_S);
		datei.add(speichern);
		datei.add(createJMenuItem("Speichern unter...", false));
		datei.add(createJMenuItem("Als Webseite Speichern", false));
		datei.add(createJMenuItem("Suchen", false));
		datei.add(new JSeparator());
		datei.add(createJMenuItem("Versionen", false));
		datei.add(new JSeparator());
		datei.add(createJMenuItem("Webseitenvorschau", false));
		datei.add(new JSeparator());
		datei.add(createJMenuItem("Seite einrichten...", false));
		datei.add(createJMenuItem("Seitenansicht", false));
		datei.add(createJMenuItem("Drucken", false));
		datei.add(new JSeparator());
		datei.add(senden);
		datei.add(createJMenuItem("Eigenschaften", false));
		datei.add(new JSeparator());
		datei.add(createJMenuItem("bilanz_2017.doc", false));
		datei.add(createJMenuItem("bericht_2018_01.doc", false));
		datei.add(createJMenuItem("ziele.doc", false));
		datei.add(new JSeparator());
		beenden = createJMenuItem("Beenden", true, this, KeyEvent.VK_B);
		datei.add(beenden);
		
		
		senden.add(createJMenuItem("E-Mail-Empfänger", false));
		senden.add(createJMenuItem("E-Mail-Empfänger (zur Überarbeitung)", false));
		senden.add(createJMenuItem("E-Mail-Empfänger (als Anlage)", false));
		senden.add(new JSeparator());
		senden.add(createJMenuItem("Verteilerempfänger...", false));
		senden.add(createJMenuItem("Onlinebesprechungsteilnehmer", false));
		senden.add(createJMenuItem("Exchange-Ordner...", false));
		senden.add(createJMenuItem("Fax-Empfänger...", false));
		senden.add(new JSeparator());
		senden.add(createJMenuItem("Microsoft PowerPoint", false));
		
		bearbeiten.add(createJMenuItem("Rückgängig", false));
		bearbeiten.add(createJMenuItem("Wiederholen", false));
		bearbeiten.addSeparator();
		bearbeiten.add(createJMenuItem("Ausschneiden", false));
		bearbeiten.add(createJMenuItem("Kopieren", false));
		bearbeiten.add(createJMenuItem("Office-Zwischenablage", false));
		bearbeiten.add(createJMenuItem("Einfügen", false));
		bearbeiten.add(createJMenuItem("Inhalte einfügen", false));
		bearbeiten.add(createJMenuItem("Als Hyperlink einfügen", false));
		bearbeiten.addSeparator();
		bearbeiten.add(createJMenuItem("Löschen", false));
		bearbeiten.add(createJMenuItem("Alles markieren", false));
		bearbeiten.addSeparator();
		bearbeiten.add(createJMenuItem("Suchen...", false));
		ersetzen = createJMenuItem("Ersetzen...", true, this, KeyEvent.VK_F);
		bearbeiten.add(ersetzen);
		bearbeiten.add(createJMenuItem("Gehe zu...", false));
		bearbeiten.addSeparator();
		bearbeiten.add(createJMenuItem("Verknüpfungen...", false));
		bearbeiten.add(createJMenuItem("Objekt", false));

		return menubar;
	}
	
	public static void main(String[] args) {

		new Editor();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem menuItem = (JMenuItem) e.getSource();
		switch (menuItem.getText()) {
			case "Neu":
						editPane.setText("");
						file = null;
						break;
			case "Öffnen": 
						editPane.setText("");
						JFileChooser fc = new JFileChooser("./files");
						
						fc.setFileFilter(new FileFilter() {
							
							@Override
							public String getDescription() {
								return "Text Files";
							}
				
							@Override
							public boolean accept(File f) {
								return f.isDirectory() || f.getAbsolutePath().toLowerCase().endsWith(".txt");
							}
						});
						
						int state = fc.showOpenDialog(frame);
						if (state == JFileChooser.APPROVE_OPTION) {
							file = fc.getSelectedFile();
							try(BufferedReader reader = new BufferedReader(new FileReader(file))){
								while(reader.ready()) {
									editPane.setText(editPane.getText() + reader.readLine() + System.lineSeparator());
								}		
							} catch (IOException ex) {
								JOptionPane.showMessageDialog(frame, "Ein Fehler beim Einlesen der Datei ist vorgefallen");
							}
						}
						break;
			case "Speichern": 
						if(file != null) {
							try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
								writer.println(editPane.getText());	
							} catch (IOException ex) {
								JOptionPane.showMessageDialog(frame, "Ein Fehler beim Einlesen der Datei ist vorgefallen");
							}
						}
						else {
							JOptionPane.showMessageDialog(frame, "Es wurde noch keine Datei eingelesen");
						}
						break;
			case "Beenden":
						int option = JOptionPane.showConfirmDialog(frame, "Sind sie sicher, dass sie die Anwendung schließen wollen?");
						if(option == JOptionPane.OK_OPTION)
							System.exit(0);
						break;
			case "Ersetzen...":			
						JTextField suchen = new JTextField(20);
						JTextField ersetzen = new JTextField(20);

						JComponent[] fields = {
								new JLabel("Suchen: "),
								suchen,
								new JLabel("Ersetzen: "),
								ersetzen
						};
						
						int ok = JOptionPane.showConfirmDialog(frame, fields, "Suchen und Ersetzen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

						if(ok == JOptionPane.OK_OPTION)
							editPane.setText(editPane.getText().replace(suchen.getText(), ersetzen.getText()));

						break;
		}
		
		
	}

}