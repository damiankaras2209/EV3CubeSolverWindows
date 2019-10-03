package me.KptMusztarda.GUI;

import me.KptMusztarda.Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener {

    private static final Dimension BUTTON_SIZE = new Dimension(100,40);
    JButton connect, send;
    Network net;

    public GUI(int x, int y) {
        setLayout(null);
        setBounds(x, y, 200, 200);
        setOpaque(true);

        net = Network.getInstance();

        connect = new JButton("Connect");
        connect.setLocation(0, 0);
        connect.setSize(BUTTON_SIZE);
        connect.addActionListener(this);
        add(connect);
        send = new JButton("Send");
        send.setLocation(0, 45);
        send.setSize(BUTTON_SIZE);
        send.addActionListener(this);
        add(send);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(connect)) {
            net.connect();
        } else if(e.getSource().equals(send)) {
            net.send(Integer.toString((int)(Math.random()*1000+.5f)));
        }
    }
}
