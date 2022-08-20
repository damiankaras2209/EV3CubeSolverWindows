package me.damiankaras.ev3cubesolver.windows.GUI;

import me.damiankaras.ev3cubesolver.windows.Network;
import me.damiankaras.ev3cubesolver.windows.NetworkData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends JPanel {

    private static final Dimension BUTTON_SIZE = new Dimension(145,40);

    private Network net;
    private JLabel status;

    private int[] inCol = {0, 2};

    private void addButton(JButton b, int col) {
        b.setSize(BUTTON_SIZE);
        b.setLocation(col * (BUTTON_SIZE.width + 5), inCol[col] * (BUTTON_SIZE.height + 5));
        inCol[col]++;
        add(b);
    }

    private void addButton(int col, String text, ActionListener al) {
        JButton b = new JButton(text);
        b.addActionListener(al);
        addButton(b, col);
    }

    private void addButton(int col, String text, MouseListener m) {
        JButton b = new JButton(text);
        b.addMouseListener(m);
        addButton(b, col);
    }

    public GUI(int x, int y) {
        setLayout(null);
        setBounds(x, y, 310, 500);
        setOpaque(true);

        net = Network.getInstance();

        addButton(0, "Connect",e -> net.connect());

        status = new JLabel("Status:");
        status.setSize(status.getPreferredSize());
        status.setLocation(BUTTON_SIZE.width + 5, 20 - status.getHeight()/2);
        add(status);


        addButton(0, "Disconnect", e -> net.closeSocket());

        addButton(0, "Solve" ,e -> net.send(NetworkData.DATATYPE_COMMAND, "solve"));
        addButton(1, "Reset", e -> net.send(NetworkData.DATATYPE_COMMAND, "reset"));

        addButton(0, "basketCW", new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                net.send(NetworkData.DATATYPE_COMMAND, "basketCW");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                net.send(NetworkData.DATATYPE_COMMAND, "basketSTOP");
            }
        });
        addButton(1, "basketCCW", new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                net.send(NetworkData.DATATYPE_COMMAND, "basketCCW");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                net.send(NetworkData.DATATYPE_COMMAND, "basketSTOP");
            }
        });

        addButton(0, "Arm lock", e -> net.send(NetworkData.DATATYPE_COMMAND, "lock"));
        addButton(1, "Arm release", e -> net.send(NetworkData.DATATYPE_COMMAND, "release"));

        addButton(0, "rotatebottomCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotatebottomCW"));
        addButton(1, "rotatebottomCCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotatebottomCCW"));

        addButton(0, "rotateYCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateYCW"));
        addButton(1, "rotateYCCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateYCCW"));

        addButton(0, "rotateZ", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateZ"));
        addButton(1, "testMove", e -> net.send(NetworkData.DATATYPE_COMMAND, "testMove"));

        addButton(0, "fillSolved", e -> net.send(NetworkData.DATATYPE_COMMAND, "fillSolved"));
    }

    public void updateStatus(String status) {
        this.status.setText("Status: " + status);
        this.status.setSize(this.status.getPreferredSize());
    }

}
