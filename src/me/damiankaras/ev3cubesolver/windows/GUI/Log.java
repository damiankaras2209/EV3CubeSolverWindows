package me.damiankaras.ev3cubesolver.windows.GUI;

import javax.swing.*;
import java.awt.*;

public class Log extends JTextArea {

    private JScrollPane scrollPane;

    public Log(int x, int y, int width, int height) {
        setLayout(null);
        scrollPane = new JScrollPane(this);
        scrollPane.setBounds(x, y, width, height);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    public JScrollPane getScroll() {
        return scrollPane;
    }

    @Override
    public void append(String str) {
        String text = str.substring(0, str.indexOf('/'));
        String breakLine = str.substring(str.indexOf('/')+1);
        super.append(text + (Integer.parseInt(breakLine) == 1 ? "\n" : ""));
    }
}
