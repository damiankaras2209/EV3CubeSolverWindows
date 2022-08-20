package me.damiankaras.ev3cubesolver.windows;

import me.damiankaras.ev3cubesolver.windows.GUI.Renderer;
import java.awt.*;

public class Cube {

    private static Cube instance = new Cube();
    public static Cube getInstance() {
        return instance;
    }

    private static final String colorChars = "WOYRGB-";
    private static final short[] rotateDisplayZ = {15,12,9,16,13,10,17,14,11,33,30,27,34,31,28,35,32,29,24,21,18,25,22,19,26,23,20,42,39,36,43,40,37,44,41,38,6,3,0,7,4,1,8,5,2,47,50,53,46,49,52,45,48,51};
    private static final short[] rotateDisplayY_CW = {2,5,8,1,4,7,0,3,6,45,46,47,48,49,50,51,52,53,9,10,11,12,13,14,15,16,17,33,30,27,34,31,28,35,32,29,18,19,20,21,22,23,24,25,26,36,37,38,39,40,41,42,43,44};
    private static final short[] rotateDisplayY_CCW = {6,3,0,7,4,1,8,5,2,18,19,20,21,22,23,24,25,26,36,37,38,39,40,41,42,43,44,29,32,35,28,31,34,27,30,33,45,46,47,48,49,50,51,52,53,9,10,11,12,13,14,15,16,17};
    private static final short[] rotateFacesDisplayZ = {3,0,2,5,4,1};
    private static final short[] rotateFacesDisplayY_CW = {0,2,3,4,1,5};
    private static final short[] rotateFacesDisplayY_CCW = {0,4,1,2,3,5};

    private enum StickerColor {
        white(255,255,255), orange(255,128,0),  yellow(255,255,0), red(255,0,0), green(0,255,0),  blue(0,0,255), gray(128, 128, 128);

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

    public Color[][][] cube;
    private volatile boolean staticOrientation = true;
    private volatile boolean drawIndexes = false;
    private String display = "";
    private String faces = "ULFRBD";
    private String fixedDisplay = "";
    private String fixedFaces = "";

    private me.damiankaras.ev3cubesolver.windows.GUI.Renderer renderer;

    Cube() {
        StringBuilder builder = new StringBuilder(54);
        for(int i=0; i<54; i++)
            builder.append("-");
        display = builder.toString();
        faces = "ULFRBD";
        cube = new Color[6][3][3];
        for (int i=0;i<6;i++) {
            for (int j=0;j<3;j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = StickerColor.gray.getStickerColor();
                }
            }
        }
    }


    void update(String str) {

        display = str.substring(0, 54);
        fixedDisplay = display;
        faces = str.substring(55);
        fixedFaces = faces;

        if(staticOrientation) {
            fixOrientation();
            str = fixedDisplay;
        }

        for (int i=0; i<6; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
                    cube[i][j][k] = StickerColor.values()[colorChars.indexOf(str.charAt(i*9 + j*3 + k))].getStickerColor();
                }
            }
        }

        renderer.repaint();
    }

    void setRenderer(Renderer r) {
        renderer = r;
    }

    public void setStaticOrientation(boolean b) {
        staticOrientation = b;
        update(display + "," + faces);
    }

    public void setDrawIndexes(boolean b) {
        renderer.setDrawIndexes(b);
    }

    private void fixOrientation() {
        
        int x = fixedFaces.indexOf("D");
        switch (x) {
            case 0: rotateZ(2); break;
            case 1: rotateZ(1); break;
            case 2: rotateY(CW, 1); rotateZ(1); break;
            case 3: rotateY(CW, 2); rotateZ(1); break;
            case 4: rotateY(CCW, 1); rotateZ(1); break;
        }

        x = fixedFaces.indexOf("F");
        switch (x) {
            case 1: rotateY(CCW, 1); break;
            case 3: rotateY(CW, 1); break;
            case 4: rotateY(CW, 2); break;
        }

    }

    private void rotateY(int dir, int iterations) {

        for(int j=0; j<iterations; j++) {
            StringBuilder facesBuilder = new StringBuilder(6);
            for(int i=0; i<6; i++)
                facesBuilder.append(fixedFaces.charAt(dir == CW ? rotateFacesDisplayY_CW[i] : rotateFacesDisplayY_CCW[i]));
            fixedFaces = facesBuilder.toString();

            StringBuilder displayBuilder = new StringBuilder(54);
            for(int i=0; i<54; i++)
                displayBuilder.append(fixedDisplay.charAt(dir == CW ? rotateDisplayY_CW[i] : rotateDisplayY_CCW[i]));
            fixedDisplay = displayBuilder.toString();
        }
    }

    private void rotateZ(int iterations) {
        for(int j=0; j<iterations; j++) {
            StringBuilder facesBuilder = new StringBuilder(6);
            for(int i=0; i<6; i++)
                facesBuilder.append(fixedFaces.charAt(rotateFacesDisplayZ[i]));
            fixedFaces = facesBuilder.toString();

            StringBuilder stringBuilder = new StringBuilder(54);
            for (int i = 0; i < 54; i++)
                stringBuilder.append(fixedDisplay.charAt(rotateDisplayZ[i]));
            fixedDisplay = stringBuilder.toString();
        }

    }
}