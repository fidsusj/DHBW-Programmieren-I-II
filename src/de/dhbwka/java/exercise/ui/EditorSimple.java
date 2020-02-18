package de.dhbwka.java.exercise.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class EditorSimple {

	public EditorSimple() {
		JFrame frame = new JFrame("Editor");
		frame.setJMenuBar(createMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 150);
		frame.setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menubar = new JMenuBar();

		JMenu datei = new JMenu("Datei");
		JMenu bearbeiten = new JMenu("Bearbeiten");
		JMenu senden = new JMenu("Senden an");
		menubar.add(datei);
		menubar.add(bearbeiten);

		datei.add(new JMenuItem("Neu"));
		datei.add(new JMenuItem("Öffnen"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("Schließen"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("Speichern"));
		datei.add(new JMenuItem("Speichern unter..."));
		datei.add(new JMenuItem("Als Webseite Speichern"));
		datei.add(new JMenuItem("Suchen"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("Versionen"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("Webseitenvorschau"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("Seite einrichten..."));
		datei.add(new JMenuItem("Seitenansicht"));
		datei.add(new JMenuItem("Drucken"));
		datei.add(new JSeparator());
		datei.add(senden);
		datei.add(new JMenuItem("Eigenschaften"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("bilanz_2017.doc"));
		datei.add(new JMenuItem("bericht_2018_01.doc"));
		datei.add(new JMenuItem("ziele.doc"));
		datei.add(new JSeparator());
		datei.add(new JMenuItem("Beenden"));

		senden.add(new JMenuItem("E-Mail-Empfänger"));
		senden.add(new JMenuItem("E-Mail-Empfänger (zur Überarbeitung)"));
		senden.add(new JMenuItem("E-Mail-Empfänger (als Anlage)"));
		senden.add(new JSeparator());
		senden.add(new JMenuItem("Verteilerempfänger..."));
		senden.add(new JMenuItem("Onlinebesprechungsteilnehmer"));
		senden.add(new JMenuItem("Exchange-Ordner..."));
		senden.add(new JMenuItem("Fax-Empfänger..."));
		senden.add(new JSeparator());
		senden.add(new JMenuItem("Microsoft PowerPoint"));

		bearbeiten.add(new JMenuItem("Rückgängig"));
		bearbeiten.add(new JMenuItem("Wiederholen"));
		bearbeiten.addSeparator();
		bearbeiten.add(new JMenuItem("Ausschneiden"));
		bearbeiten.add(new JMenuItem("Kopieren"));
		bearbeiten.add(new JMenuItem("Office-Zwischenablage"));
		bearbeiten.add(new JMenuItem("Einfügen"));
		bearbeiten.add(new JMenuItem("Inhalte einfügen"));
		bearbeiten.add(new JMenuItem("Als Hyperlink einfügen"));
		bearbeiten.addSeparator();
		bearbeiten.add(new JMenuItem("Löschen"));
		bearbeiten.add(new JMenuItem("Alles markieren"));
		bearbeiten.addSeparator();
		bearbeiten.add(new JMenuItem("Suchen..."));
		bearbeiten.add(new JMenuItem("Ersetzen..."));
		bearbeiten.add(new JMenuItem("Gehe zu..."));
		bearbeiten.addSeparator();
		bearbeiten.add(new JMenuItem("Verknüpfungen..."));
		bearbeiten.add(new JMenuItem("Objekt"));

		return menubar;
	}

	public static void main(String[] args) {

		new EditorSimple();

	}

}