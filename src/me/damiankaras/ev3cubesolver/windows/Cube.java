package me.damiankaras.ev3cubesolver.windows;

import me.damiankaras.ev3cubesolver.windows.GUI.Renderer;

import java.awt.*;

public class Cube {

    private enum StickerColor {
        white(255,255,255), orange(255,128,0),  yellow(255,255,0), red(255,0,0), green(0,255,0),  blue(0,0,255);

        private Color color;

        StickerColor(int red, int green, int blue) {
            this.color = new Color(red, green, blue);
        }
        private Color getStickerColor() {
            return color;
        }
    }

    private static final int CW = 0;
    private static final int CCW = 1;

    public static Color[][][] cube;
    private String display;
    private String faces;
    int counter;

    me.damiankaras.ev3cubesolver.windows.GUI.Renderer renderer;

    public Cube() {
        cube = new Color[6][3][3];
        for (int i=0;i<6;i++) {
            for (int j=0;j<3;j++) {
                for (int k = 0; k < 3; k++) {
//                    cube[i][j][k] = StickerColor.values()[i].getStickerColor();
                    cube[i][j][k] = Color.GRAY;
                }
            }
        }
    }

    Color getColor(Character c) {
        switch (c) {
            case 'W': return StickerColor.values()[0].getStickerColor();
            case 'O': return StickerColor.values()[1].getStickerColor();
            case 'Y': return StickerColor.values()[2].getStickerColor();
            case 'R': return StickerColor.values()[3].getStickerColor();
            case 'G': return StickerColor.values()[4].getStickerColor();
            case 'B': return StickerColor.values()[5].getStickerColor();
            default: return Color.GRAY;
        }

    }

    void update(String str) {

        if(true) {
            fixDisplay(str);
            str = display;
        }


        for (int i=0; i<6; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
//                    cube[i][j][k] = StickerColor.values()[i].getStickerColor();
                    cube[i][j][k] = getColor(str.charAt(i*9 + j*3 + k));
                }
            }
        }

        renderer.repaint();
    }

    void setRenderer(Renderer r) {
        renderer = r;
    }

    private void fixDisplay(String str) {

        display = str.substring(0, 54);
        faces = str.substring(55);

//        StringBuilder builder = new StringBuilder(6);
//        builder.append(display.charAt(0*9+4));
//        builder.append(display.charAt(4*9+4));
//        builder.append(display.charAt(2*9+4));
//        builder.append(display.charAt(1*9+4));
//        builder.append(display.charAt(5*9+4));
//        builder.append(display.charAt(3*9+4));
//        faces = builder.toString();

        int x = faces.indexOf("D");
//        int x = faces.indexOf("Y");
        switch (x) {
            case 0:
                rotateZ();
                rotateZ();
                break;
            case 1:
                rotateZ();
                break;
            case 2:
                rotateY(CW, 1);
                rotateZ();
                break;
            case 3:
                rotateY(CW, 2);
                rotateZ();
                break;
            case 4:
                rotateY(CCW, 1);
                rotateZ();
                break;
        }

        x = faces.indexOf("F");
//        x = faces.indexOf("B");
        switch (x) {
            case 1:
                rotateY(CCW, 1);
                break;
            case 3:
                rotateY(CW, 1);
                break;
            case 4:
                rotateY(CW, 2);
                break;
        }

    }

    private void rotateY(int dir, int iterations) {

        StringBuilder stringBuilder = new StringBuilder(display);

        for(int j=0; j<iterations; j++) {
            StringBuilder facesBuilder = new StringBuilder(faces);
            if (dir == 0) {
                facesBuilder.setCharAt(1, faces.charAt(2));
                facesBuilder.setCharAt(2, faces.charAt(3));
                facesBuilder.setCharAt(3, faces.charAt(4));
                facesBuilder.setCharAt(4, faces.charAt(1));
            } else {
                facesBuilder.setCharAt(2, faces.charAt(1));
                facesBuilder.setCharAt(3, faces.charAt(2));
                facesBuilder.setCharAt(4, faces.charAt(3));
                facesBuilder.setCharAt(1, faces.charAt(4));
            }
            faces = facesBuilder.toString();

            //if(!isScanning) {

            if (dir == 0) {
                stringBuilder.setCharAt(0, display.charAt(2));
                stringBuilder.setCharAt(1, display.charAt(5));
                stringBuilder.setCharAt(2, display.charAt(8));
                stringBuilder.setCharAt(3, display.charAt(1));
                stringBuilder.setCharAt(5, display.charAt(7));
                stringBuilder.setCharAt(6, display.charAt(0));
                stringBuilder.setCharAt(7, display.charAt(3));
                stringBuilder.setCharAt(8, display.charAt(6));

                stringBuilder.setCharAt(3 * 9 + 2, display.charAt(3 * 9 + 0));
                stringBuilder.setCharAt(3 * 9 + 5, display.charAt(3 * 9 + 1));
                stringBuilder.setCharAt(3 * 9 + 8, display.charAt(3 * 9 + 2));
                stringBuilder.setCharAt(3 * 9 + 1, display.charAt(3 * 9 + 3));
                stringBuilder.setCharAt(3 * 9 + 7, display.charAt(3 * 9 + 5));
                stringBuilder.setCharAt(3 * 9 + 0, display.charAt(3 * 9 + 6));
                stringBuilder.setCharAt(3 * 9 + 3, display.charAt(3 * 9 + 7));
                stringBuilder.setCharAt(3 * 9 + 6, display.charAt(3 * 9 + 8));

                for (int i = 0; i < 9; i++) {
                    stringBuilder.setCharAt(2 * 9 + i, display.charAt(1 * 9 + i));
                    stringBuilder.setCharAt(1 * 9 + i, display.charAt(5 * 9 + i));
                    stringBuilder.setCharAt(5 * 9 + i, display.charAt(4 * 9 + i));
                    stringBuilder.setCharAt(4 * 9 + i, display.charAt(2 * 9 + i));
                }

            } else {
                stringBuilder.setCharAt(2, display.charAt(0));
                stringBuilder.setCharAt(5, display.charAt(1));
                stringBuilder.setCharAt(8, display.charAt(2));
                stringBuilder.setCharAt(1, display.charAt(3));
                stringBuilder.setCharAt(7, display.charAt(5));
                stringBuilder.setCharAt(0, display.charAt(6));
                stringBuilder.setCharAt(3, display.charAt(7));
                stringBuilder.setCharAt(6, display.charAt(8));

                stringBuilder.setCharAt(3 * 9 + 0, display.charAt(3 * 9 + 2));
                stringBuilder.setCharAt(3 * 9 + 1, display.charAt(3 * 9 + 5));
                stringBuilder.setCharAt(3 * 9 + 2, display.charAt(3 * 9 + 8));
                stringBuilder.setCharAt(3 * 9 + 3, display.charAt(3 * 9 + 1));
                stringBuilder.setCharAt(3 * 9 + 5, display.charAt(3 * 9 + 7));
                stringBuilder.setCharAt(3 * 9 + 6, display.charAt(3 * 9 + 0));
                stringBuilder.setCharAt(3 * 9 + 7, display.charAt(3 * 9 + 3));
                stringBuilder.setCharAt(3 * 9 + 8, display.charAt(3 * 9 + 6));

                for (int i = 0; i < 9; i++) {
                    stringBuilder.setCharAt(1 * 9 + i, display.charAt(2 * 9 + i));
                    stringBuilder.setCharAt(5 * 9 + i, display.charAt(1 * 9 + i));
                    stringBuilder.setCharAt(4 * 9 + i, display.charAt(5 * 9 + i));
                    stringBuilder.setCharAt(2 * 9 + i, display.charAt(4 * 9 + i));
                }
            }
            //}
            display = stringBuilder.toString();
        }
    }

    private void rotateZ() {

        StringBuilder  facesBuilder = new StringBuilder(faces);
        facesBuilder.setCharAt(0, faces.charAt(3));
        facesBuilder.setCharAt(1, faces.charAt(0));
        facesBuilder.setCharAt(3, faces.charAt(5));
        facesBuilder.setCharAt(5, faces.charAt(1));

        faces = facesBuilder.toString();
        System.out.println(faces);

        // if(!isScanning) {
        StringBuilder stringBuilder = new StringBuilder(display);

        stringBuilder.setCharAt(2*9 + 2, display.charAt(2*9 + 0));
        stringBuilder.setCharAt(2*9 + 5, display.charAt(2*9 + 1));
        stringBuilder.setCharAt(2*9 + 8, display.charAt(2*9 + 2));
        stringBuilder.setCharAt(2*9 + 1, display.charAt(2*9 + 3));
        stringBuilder.setCharAt(2*9 + 7, display.charAt(2*9 + 5));
        stringBuilder.setCharAt(2*9 + 0, display.charAt(2*9 + 6));
        stringBuilder.setCharAt(2*9 + 3, display.charAt(2*9 + 7));
        stringBuilder.setCharAt(2*9 + 6, display.charAt(2*9 + 8));

        stringBuilder.setCharAt(5*9 + 0, display.charAt(5*9 + 2));
        stringBuilder.setCharAt(5*9 + 1, display.charAt(5*9 + 5));
        stringBuilder.setCharAt(5*9 + 2, display.charAt(5*9 + 8));
        stringBuilder.setCharAt(5*9 + 3, display.charAt(5*9 + 1));
        stringBuilder.setCharAt(5*9 + 5, display.charAt(5*9 + 7));
        stringBuilder.setCharAt(5*9 + 6, display.charAt(5*9 + 0));
        stringBuilder.setCharAt(5*9 + 7, display.charAt(5*9 + 3));
        stringBuilder.setCharAt(5*9 + 8, display.charAt(5*9 + 6));

        stringBuilder.setCharAt( 0, display.charAt(9 + 6));
        stringBuilder.setCharAt( 1, display.charAt(9 + 3));
        stringBuilder.setCharAt( 2, display.charAt(9 + 0));
        stringBuilder.setCharAt( 3, display.charAt(9 + 7));
        stringBuilder.setCharAt( 4, display.charAt(9 + 4));
        stringBuilder.setCharAt( 5, display.charAt(9 + 1));
        stringBuilder.setCharAt( 6, display.charAt(9 + 8));
        stringBuilder.setCharAt( 7, display.charAt(9 + 5));
        stringBuilder.setCharAt( 8, display.charAt(9 + 2));

        stringBuilder.setCharAt( 9 + 0, display.charAt(3*9 + 6));
        stringBuilder.setCharAt( 9 + 1, display.charAt(3*9 + 3));
        stringBuilder.setCharAt( 9 + 2, display.charAt(3*9 + 0));
        stringBuilder.setCharAt( 9 + 3, display.charAt(3*9 + 7));
        stringBuilder.setCharAt( 9 + 4, display.charAt(3*9 + 4));
        stringBuilder.setCharAt( 9 + 5, display.charAt(3*9 + 1));
        stringBuilder.setCharAt( 9 + 6, display.charAt(3*9 + 8));
        stringBuilder.setCharAt( 9 + 7, display.charAt(3*9 + 5));
        stringBuilder.setCharAt( 9 + 8, display.charAt(3*9 + 2));

        stringBuilder.setCharAt( 3*9 + 0, display.charAt(4*9 + 6));
        stringBuilder.setCharAt( 3*9 + 1, display.charAt(4*9 + 3));
        stringBuilder.setCharAt( 3*9 + 2, display.charAt(4*9 + 0));
        stringBuilder.setCharAt( 3*9 + 3, display.charAt(4*9 + 7));
        stringBuilder.setCharAt( 3*9 + 4, display.charAt(4*9 + 4));
        stringBuilder.setCharAt( 3*9 + 5, display.charAt(4*9 + 1));
        stringBuilder.setCharAt( 3*9 + 6, display.charAt(4*9 + 8));
        stringBuilder.setCharAt( 3*9 + 7, display.charAt(4*9 + 5));
        stringBuilder.setCharAt( 3*9 + 8, display.charAt(4*9 + 2));

        stringBuilder.setCharAt( 4*9 + 0, display.charAt(6));
        stringBuilder.setCharAt( 4*9 + 1, display.charAt(3));
        stringBuilder.setCharAt( 4*9 + 2, display.charAt(0));
        stringBuilder.setCharAt( 4*9 + 3, display.charAt(7));
        stringBuilder.setCharAt( 4*9 + 4, display.charAt(4));
        stringBuilder.setCharAt( 4*9 + 5, display.charAt(1));
        stringBuilder.setCharAt( 4*9 + 6, display.charAt(8));
        stringBuilder.setCharAt( 4*9 + 7, display.charAt(5));
        stringBuilder.setCharAt( 4*9 + 8, display.charAt(2));

        display = stringBuilder.toString();

//            for (int i = 0; i < 9; i++) {
//                stringBuilder.setCharAt(0 * 9 + i, display.charAt(1 * 9 + i));
//                stringBuilder.setCharAt(1 * 9 + i, display.charAt(3 * 9 + i));
//                stringBuilder.setCharAt(3 * 9 + i, display.charAt(4 * 9 + i));
//                stringBuilder.setCharAt(4 * 9 + i, display.charAt(0 * 9 + i));
//            }
    }
}