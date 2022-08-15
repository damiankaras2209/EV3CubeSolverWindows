package me.damiankaras.ev3cubesolver.windows.GUI;

import me.damiankaras.ev3cubesolver.windows.Cube;

import javax.swing.*;
import java.awt.*;



public class Renderer extends JPanel {

    private static final int FACE_SPACING = 2;
    private static final int FACELET_SIZE = 45;
    private static final int FACELET_SPACING= 1;

    private Cube cube;

    public Renderer(int x, int y) {
        setLayout(null);
//        setLocation(x, y);
        setBounds(x, y, 4 * (getFaceSize()  + FACE_SPACING) - FACE_SPACING, 3 * (getFaceSize()  + FACE_SPACING) - FACE_SPACING);
        setBorder(null);
        setOpaque(false);
    }

    @Override
    public void paintComponent (Graphics g) {

        System.out.println("paintComponent()");

        int face = 0;

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1 || i == 1) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(
                            i * (getFaceSize() + FACE_SPACING),
                            j * (getFaceSize() + FACE_SPACING),
                            getFaceSize(),
                            getFaceSize()
                    );
                }
            }
        }

        drawFace(g, 0, 1, 0);
        drawFace(g, 1, 2, 1);
        drawFace(g, 2, 1, 1);
        drawFace(g, 3, 1, 2);
        drawFace(g, 4, 0, 1);
        drawFace(g, 5, 3, 1);

    }

    private void drawFace(Graphics g, int face, int i, int j) {
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                g.setColor(Cube.cube[face][k][l]);
                g.fillRect(
                        i * (getFaceSize() + FACE_SPACING) + k * (FACELET_SIZE + FACELET_SPACING) + FACELET_SPACING,
                        j * (getFaceSize() + FACE_SPACING) + l * (FACELET_SIZE + FACELET_SPACING) + FACELET_SPACING,
                        FACELET_SIZE,
                        FACELET_SIZE
                );
                g.setColor(Color.BLACK);
                g.drawString(face + "," + k + "," + l,
                        i * (getFaceSize() + FACE_SPACING) + k * (FACELET_SIZE + FACELET_SPACING) + FACELET_SPACING + 5,
                        j * (getFaceSize() + FACE_SPACING) + l * (FACELET_SIZE + FACELET_SPACING) + FACELET_SPACING + 25);
//                        g.setColor(Color.pink);
//                        g.drawString(Integer.toString(i) + "," + Integer.toString(j) + "," + Integer.toString(k), StartCoords.values()[i].getX(j) + 160, StartCoords.values()[i].getY(k) + 10);
            }
        }
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    private int getFaceSize() {
        return 3 * (FACELET_SIZE + FACELET_SPACING) + FACELET_SPACING;
    }
}
