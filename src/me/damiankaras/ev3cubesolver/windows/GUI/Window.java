package me.damiankaras.ev3cubesolver.windows.GUI;

import javax.swing.*;


public class Window extends JFrame {

    MPanel mainPanel;

    public Window() {
        setLayout(null);
        setSize(850, 675);
        setTitle("cube");

        mainPanel = new MPanel();
        mainPanel.setSize(getWidth(), getHeight());
        setContentPane(mainPanel);

//        JTextArea tArea= new JTextArea();
//        tArea.setBounds(450, 10, 280, 130);
//        tArea.setLineWrap(true);
//        tArea.setWrapStyleWord(true);
//        add(tArea);
//
//
//        int x = 0;
//        int y = 0;
//        JButton button = new JButton("U");
//        button.setBounds(x + 10, y + 10, 45, 30);
//        button.addActionListener(e -> {
//            System.out.println("Clicked");
//        });




    }

//    @Override
//    public Component add(Component comp) {
//        return mainPanel.add(comp);
//    }
}