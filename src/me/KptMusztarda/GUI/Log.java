package me.KptMusztarda.GUI;

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
        super.append(str + "\n");
    }
}
