package de.dhbwka.java.exercise.ui.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AlternateBinaryNumber {
    private JFrame frame = new JFrame();
    private JPanel[] panels = new JPanel[2];
    private JToggleButton[] toggleButtons;
    private ActionListener listener  = e -> calculateResult();
    private JLabel result;

    private AlternateBinaryNumber(int length) {
        toggleButtons  = new JToggleButton[length];
        generateLayout();
        viewLayout();
    }

    private void generateLayout() {
        int gap = 2;

        frame.setLayout(new BorderLayout(gap, gap));
        panels[0] = new JPanel();
        panels[0].setLayout(new GridLayout(2, toggleButtons.length, gap, gap));
        generateTopContent();
        frame.add(panels[0], BorderLayout.NORTH);
        panels[1] = new JPanel();
        generateBottomContent();
        frame.add(panels[1], BorderLayout.CENTER);
    }

    private void generateTopContent() {
        ImageIcon imgOff = new ImageIcon("./images/off.png");
        ImageIcon imgOn = new ImageIcon("./images/on.png");

        for (int i = 0; i < toggleButtons.length; i++) {
            toggleButtons[i] = new JToggleButton();
            toggleButtons[i].addActionListener(listener);
            toggleButtons[i].setIcon(imgOff);
            toggleButtons[i].setSelectedIcon(imgOn);
            panels[0].add(toggleButtons[i]);
        }
        for (int i = toggleButtons.length -1; i >= 0; i--) {
            panels[0].add(new JLabel("2^" + i));
        }
    }

    private void generateBottomContent() {
        panels[1].add(result = new JLabel());
        calculateResult();
    }

    private void calculateResult() {
        int result = 0;
        for (int i = 0; i < toggleButtons.length; i++) {
            if (toggleButtons[i].isSelected()) {
                result |= 1 << (toggleButtons.length - 1 - i);
            }
        }
        this.result.setText(Integer.toString(result));
    }

    private void viewLayout() {
        frame.setTitle(this.getClass().getSimpleName());
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AlternateBinaryNumber(8);
    }
}