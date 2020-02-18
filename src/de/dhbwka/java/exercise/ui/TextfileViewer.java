package de.dhbwka.java.exercise.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class TextfileViewer {

	public TextfileViewer() {

		JFileChooser filechooser = new JFileChooser();
		filechooser.setCurrentDirectory(new File("../../eclipse-workspace/"));
		filechooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
			}

			@Override
			public String getDescription() {
				return "Text Files";
			}
		});
		int state = filechooser.showOpenDialog(null);
		if (state == JFileChooser.APPROVE_OPTION) {
			new TextFrame(filechooser.getSelectedFile().getAbsolutePath(), 640, 480);
			new TextFrameJLabel(filechooser.getSelectedFile().getAbsolutePath(), 640, 480);
		} else {
			System.out.println("Es wurde keine Datei ausgewählt");
		}

	}

	public static void main(String[] args) {

		new TextfileViewer();
		
	}
}
