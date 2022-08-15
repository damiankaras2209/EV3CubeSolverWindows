package me.damiankaras.ev3cubesolver.windows.GUI;

import me.damiankaras.ev3cubesolver.windows.Network;
import me.damiankaras.ev3cubesolver.windows.NetworkData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JPanel {

    private static final Dimension BUTTON_SIZE = new Dimension(145,40);
    Network net;

    JLabel status;

    public GUI(int x, int y) {
        setLayout(null);
        setBounds(x, y, 310, 500);
        setOpaque(true);

        net = Network.getInstance();

        JButton connect = new JButton("Connect");
        connect.setLocation(0, 0);
        connect.setSize(BUTTON_SIZE);
        connect.addActionListener(e -> net.connect());
        add(connect);

        status = new JLabel("Status:");
        status.setSize(status.getPreferredSize());
        status.setLocation(BUTTON_SIZE.width + 5, 20 - status.getHeight()/2);
        add(status);

        JButton disconnect = new JButton("Disconnect");
        disconnect.setLocation(0, BUTTON_SIZE.height + 5);
        disconnect.setSize(BUTTON_SIZE);
        disconnect.addActionListener(e -> net.closeSocket());
        add(disconnect);

        JButton solve = new JButton("Solve");
        solve.setLocation(0, 2 * (BUTTON_SIZE.height + 5));
        solve.setSize(BUTTON_SIZE);
        solve.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "solve"));
        add(solve);

        JButton reset = new JButton("Reset");
        reset.setLocation(BUTTON_SIZE.width + 5, 2 * (BUTTON_SIZE.height + 5));
        reset.setSize(BUTTON_SIZE);
        reset.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "reset"));
        add(reset);

        JButton basketCW = new JButton("basketCW");
        basketCW.setLocation(0, 3 * (BUTTON_SIZE.height + 5));
        basketCW.setSize(BUTTON_SIZE);
        basketCW.addMouseListener(new MouseAdapter() {
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
        add(basketCW);

        JButton basketCCW = new JButton("basketCCW");
        basketCCW.setLocation(BUTTON_SIZE.width + 5, 3 * (BUTTON_SIZE.height + 5));
        basketCCW.setSize(BUTTON_SIZE);
        basketCCW.addMouseListener(new MouseAdapter() {
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
        add(basketCCW);

        JButton lock = new JButton("lock");
        lock.setLocation(0, 4 * (BUTTON_SIZE.height + 5));
        lock.setSize(BUTTON_SIZE);
        lock.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "lock"));
        add(lock);

        JButton release = new JButton("release");
        release.setLocation(BUTTON_SIZE.width + 5, 4 * (BUTTON_SIZE.height + 5));
        release.setSize(BUTTON_SIZE);
        release.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "release"));
        add(release);

        JButton rotatebottomCW = new JButton("rotatebottomCW");
        rotatebottomCW.setLocation(0, 5 * (BUTTON_SIZE.height + 5));
        rotatebottomCW.setSize(BUTTON_SIZE);
        rotatebottomCW.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "rotatebottomCW"));
        add(rotatebottomCW);

        JButton rotatebottomCCW = new JButton("rotatebottomCCW");
        rotatebottomCCW.setLocation(BUTTON_SIZE.width + 5, 5 * (BUTTON_SIZE.height + 5));
        rotatebottomCCW.setSize(BUTTON_SIZE);
        rotatebottomCCW.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "rotatebottomCCW"));
        add(rotatebottomCCW);

        JButton rotateYCW = new JButton("rotateYCW");
        rotateYCW.setLocation(0, 6 * (BUTTON_SIZE.height + 5));
        rotateYCW.setSize(BUTTON_SIZE);
        rotateYCW.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateYCW"));
        add(rotateYCW);

        JButton rotateYCCW = new JButton("rotateYCCW");
        rotateYCCW.setLocation(BUTTON_SIZE.width + 5, 6 * (BUTTON_SIZE.height + 5));
        rotateYCCW.setSize(BUTTON_SIZE);
        rotateYCCW.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateYCCW"));
        add(rotateYCCW);

        JButton rotateZ = new JButton("rotateZ");
        rotateZ.setLocation(0, 7 * (BUTTON_SIZE.height + 5));
        rotateZ.setSize(BUTTON_SIZE);
        rotateZ.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateZ"));
        add(rotateZ);

        JButton testMove = new JButton("testMove");
        testMove.setLocation(BUTTON_SIZE.width + 5, 7 * (BUTTON_SIZE.height + 5));
        testMove.setSize(BUTTON_SIZE);
        testMove.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "testMove"));
        add(testMove);

        JButton fillSolved = new JButton("fillSolved");
        fillSolved.setLocation(0, 8 * (BUTTON_SIZE.height + 5));
        fillSolved.setSize(BUTTON_SIZE);
        fillSolved.addActionListener(e -> net.send(NetworkData.DATATYPE_COMMAND, "fillSolved"));
        add(fillSolved);
    }

    public void updateStatus(String status) {
        this.status.setText("Status: " + status);
        this.status.setSize(this.status.getPreferredSize());
    }


}
