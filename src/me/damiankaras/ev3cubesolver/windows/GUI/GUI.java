package me.damiankaras.ev3cubesolver.windows.GUI;

import me.damiankaras.ev3cubesolver.windows.Cube;
import me.damiankaras.ev3cubesolver.windows.Network;
import me.damiankaras.ev3cubesolver.windows.NetworkData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JPanel {

    private static final Dimension BUTTON_SIZE = new Dimension(145,40);

    private Network net;
    private JLabel status;
    private ArrayList<JButton> debugButtons = new ArrayList<>();

    private int[] inCol = {0, 2};

    private void addButton(boolean debug, JButton b, int col) {
        b.setSize(BUTTON_SIZE);
        b.setLocation(col * (BUTTON_SIZE.width + 5), inCol[col] * (BUTTON_SIZE.height + 5));
        inCol[col]++;
        if(debug)
            debugButtons.add(b);
        add(b);
    }

    private void addButton(boolean debug, int col, String text, ActionListener al) {
        JButton b = new JButton(text);
        b.addActionListener(al);
        addButton(debug, b, col);
    }

    private void addButton(boolean debug, int col, String text, MouseListener m) {
        JButton b = new JButton(text);
        b.addMouseListener(m);
        addButton(debug, b, col);
    }

    public GUI(int x, int y) {
        setLayout(null);
        setBounds(x, y, 310, 500);
        setOpaque(true);

        net = Network.getInstance();

        addButton(false, 0, "Connect",e -> net.connect());

        status = new JLabel("Status:");
        status.setSize(status.getPreferredSize());
        status.setLocation(BUTTON_SIZE.width + 5, 5);
        add(status);

        JCheckBox fixedOrientation = new JCheckBox("Fixed orientation", true);
        fixedOrientation.setSize(fixedOrientation.getPreferredSize());
        fixedOrientation.setLocation(BUTTON_SIZE.width + 5, status.getHeight() + 5);
        fixedOrientation.addItemListener(e -> Cube.getInstance().setStaticOrientation(e.getStateChange() == ItemEvent.SELECTED));
        add(fixedOrientation);

        JCheckBox debug = new JCheckBox("Debug", false);
        debug.setSize(debug.getPreferredSize());
        debug.setLocation(BUTTON_SIZE.width + 5, 2 * (status.getHeight() + 5));
        debug.addItemListener(e -> {
            Cube.getInstance().setDrawIndexes(e.getStateChange() == ItemEvent.SELECTED);
            for(JButton b : debugButtons) {
                b.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        add(debug);

        addButton(false, 0, "Disconnect", e -> net.closeSocket());

        addButton(false, 0, "Scan" ,e -> net.send(NetworkData.DATATYPE_COMMAND, "scan"));
        addButton(false, 1, "Solve" ,e -> net.send(NetworkData.DATATYPE_COMMAND, "solve"));

        addButton(false, 0, "Reset", e -> net.send(NetworkData.DATATYPE_COMMAND, "reset"));
        addButton(true, 1, "testMove", e -> net.send(NetworkData.DATATYPE_COMMAND, "testMove"));

        addButton(true, 0, "basketCW", new MouseAdapter() {
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
        addButton(true, 1, "basketCCW", new MouseAdapter() {
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

        addButton(true, 0, "Arm lock", e -> net.send(NetworkData.DATATYPE_COMMAND, "lock"));
        addButton(true, 1, "Arm release", e -> net.send(NetworkData.DATATYPE_COMMAND, "release"));

        addButton(true, 0, "rotatebottomCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotatebottomCW"));
        addButton(true, 1, "rotatebottomCCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotatebottomCCW"));

        addButton(true, 0, "rotateYCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateYCW"));
        addButton(true, 1, "rotateYCCW", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateYCCW"));

        addButton(true, 0, "rotateZ", e -> net.send(NetworkData.DATATYPE_COMMAND, "rotateZ"));
        addButton(true, 1, "fillSolved", e -> net.send(NetworkData.DATATYPE_COMMAND, "fillSolved"));

        for(JButton b : debugButtons) {
            b.setVisible(debug.isSelected());
        }
    }

    public void updateStatus(String status) {
        this.status.setText("Status: " + status);
        this.status.setSize(this.status.getPreferredSize());
    }

}
