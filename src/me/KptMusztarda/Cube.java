package me.KptMusztarda;

import me.KptMusztarda.GUI.Renderer;

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

    public static Color[][][] cube;
    int counter;

    me.KptMusztarda.GUI.Renderer renderer;

    private int[] colors = new int[6];

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

    //URDLFB
    void setColor(String s) {
        switch (s.charAt(0)) {
            case 'U': colors[0] = Integer.parseInt(Character.toString(s.charAt(1))); break;
            case 'R': colors[1] = Integer.parseInt(Character.toString(s.charAt(1))); break;
            case 'D': colors[2] = Integer.parseInt(Character.toString(s.charAt(1))); break;
            case 'L': colors[3] = Integer.parseInt(Character.toString(s.charAt(1))); break;
            case 'F': colors[4] = Integer.parseInt(Character.toString(s.charAt(1))); break;
            case 'B': colors[5] = Integer.parseInt(Character.toString(s.charAt(1))); break;
        }
    }

    Color getColor(Character c) {
        switch (c) {
            case 'U': return StickerColor.values()[colors[0]].getStickerColor();
            case 'R': return StickerColor.values()[colors[1]].getStickerColor();
            case 'D': return StickerColor.values()[colors[2]].getStickerColor();
            case 'L': return StickerColor.values()[colors[3]].getStickerColor();
            case 'F': return StickerColor.values()[colors[4]].getStickerColor();
            case 'B': return StickerColor.values()[colors[5]].getStickerColor();
            default: return Color.GRAY;
        }

    }

    void update(String str) {



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


//    public void temp(JTextArea t) {
//        String s;
//        for (int i=0;i<6;i++) {
//            for (int j=0;j<3;j++) {
//                for (int k = 0; k < 3; k++) {
//                    if (cube[i][j][k].equals(new Color(255,255,255))) s = "white";
//                    else if (cube[i][j][k].equals(new Color(255,255,0))) s = "yellow";
//                    else if (cube[i][j][k].equals(new Color(0,0,255))) s = "blue";
//                    else if (cube[i][j][k].equals(new Color(255,0,0))) s = "red";
//                    else if (cube[i][j][k].equals(new Color(0,255,0))) s = "green";
//                    else s = "orange";
//                    t.append("cube["+i+"]["+j+"]["+k+"] = "+s+"\n");
//                }
//            }
//        }
//    }

//    private void rotateTop(int side, boolean prim) {
//        Color buffer[] = new Color[2];
//        if (prim==false) {
//            buffer[0] = cube[side][0][1];
//            buffer[1] = cube[side][0][2];
//            cube[side][0][1] = cube[side][1][2];
//            cube[side][0][2] = cube[side][2][2];
//            cube[side][1][2] = cube[side][2][1];
//            cube[side][2][2] = cube[side][2][0];
//            cube[side][2][1] = cube[side][1][0];
//            cube[side][2][0] = cube[side][0][0];
//            cube[side][1][0] = buffer[0];
//            cube[side][0][0] = buffer[1];
//        }
//        else {
//            buffer[0] = cube[side][0][0];
//            buffer[1] = cube[side][1][0];
//            cube[side][0][0] = cube[side][2][0];
//            cube[side][1][0] = cube[side][2][1];
//            cube[side][2][0] = cube[side][2][2];
//            cube[side][2][1] = cube[side][1][2];
//            cube[side][2][2] = cube[side][0][2];
//            cube[side][1][2] = cube[side][0][1];
//            cube[side][0][2] = buffer[0];
//            cube[side][0][1] = buffer[1];
//        }
//    }
//
//    private void rotateSide(int side, boolean prim) {
//        Color buffer[] = new Color[3];
//        int sideindex[], x[] = new int[3], y[] = new int[3];
//        int sidepartx[][] = new int[4][3];
//        int sideparty[][] = new int[4][3];
//        if (prim==false) {
//            if (side==1) {
//                sideindex = new int[]{5,4,3,2};
//                for (int i=0;i<4;i++) {
//                    sidepartx[i] = new int[]{0, 1, 2};
//                    sideparty[i] = new int[]{0, 0, 0};
//                }
//            }
//            else if (side==0) {
//                sideindex = new int[]{2,3,4,5};
//                for (int i=0;i<4;i++) {
//                    sidepartx[i] = new int[]{0, 1, 2};
//                    sideparty[i] = new int[]{2, 2, 2};
//                }
//            }
//            else if (side==2) {
//                sideindex = new int[]{5,1,3,0};
//                sidepartx[0] = new int[]{2, 2, 2};
//                sideparty[0] = new int[]{0, 1, 2};
//                sidepartx[1] = new int[]{2, 1, 0};
//                sideparty[1] = new int[]{2, 2, 2};
//                sidepartx[2] = new int[]{0, 0, 0};
//                sideparty[2] = new int[]{2, 1, 0};
//                sidepartx[3] = new int[]{0, 1, 2};
//                sideparty[3] = new int[]{0, 0, 0};
//            }
//            else if (side==3) {
//                sideindex = new int[]{2,1,4,0};
//                sidepartx[0] = new int[]{2, 2, 2};
//                sideparty[0] = new int[]{0, 1, 2};
//                sidepartx[1] = new int[]{2, 2, 2};
//                sideparty[1] = new int[]{0, 1, 2};
//                sidepartx[2] = new int[]{0, 0, 0};
//                sideparty[2] = new int[]{2, 1, 0};
//                sidepartx[3] = new int[]{2, 2, 2};
//                sideparty[3] = new int[]{0, 1, 2};
//            }
//            else if (side==4) {
//                sideindex = new int[]{3,1,5,0};
//                sidepartx[0] = new int[]{2, 2, 2};
//                sideparty[0] = new int[]{0, 1, 2};
//                sidepartx[1] = new int[]{0, 1, 2};
//                sideparty[1] = new int[]{0, 0, 0};
//                sidepartx[2] = new int[]{0, 0, 0};
//                sideparty[2] = new int[]{2, 1, 0};
//                sidepartx[3] = new int[]{2, 1, 0};
//                sideparty[3] = new int[]{2, 2, 2};
//            }
//            else {
//                sideindex = new int[]{4,1,2,0};
//                sidepartx[0] = new int[]{2, 2, 2};
//                sideparty[0] = new int[]{0, 1, 2};
//                sidepartx[1] = new int[]{0, 0, 0};
//                sideparty[1] = new int[]{2, 1, 0};
//                sidepartx[2] = new int[]{0, 0, 0};
//                sideparty[2] = new int[]{2, 1, 0};
//                sidepartx[3] = new int[]{0, 0, 0};
//                sideparty[3] = new int[]{2, 1, 0};
//            }
//        }
//        else {
//            if (side == 1) {
//                sideindex = new int[]{2,3,4,5};
//                for (int i=0;i<4;i++) {
//                    sidepartx[i] = new int[]{0, 1, 2};
//                    sideparty[i] = new int[]{0, 0, 0};
//                }
//            }
//            else if (side==0) {
//                sideindex = new int[]{5,4,3,2};
//                for (int i=0;i<4;i++) {
//                    sidepartx[i] = new int[]{0, 1, 2};
//                    sideparty[i] = new int[]{2, 2, 2};
//                }
//            }
//            else if (side==2) {
//                sideindex = new int[]{0, 3, 1, 5};
//                sidepartx[0] = new int[]{2, 1, 0};
//                sideparty[0] = new int[]{0, 0, 0};
//                sidepartx[1] = new int[]{0, 0, 0};
//                sideparty[1] = new int[]{0, 1, 2};
//                sidepartx[2] = new int[]{0, 1, 2};
//                sideparty[2] = new int[]{2, 2, 2};
//                sidepartx[3] = new int[]{2, 2, 2};
//                sideparty[3] = new int[]{2, 1, 0};
//            }
//            else if (side==3) {
//                sideindex = new int[]{0,4,1,2};
//                sidepartx[0] = new int[]{2, 2, 2};
//                sideparty[0] = new int[]{0, 1, 2};
//                sidepartx[1] = new int[]{0, 0, 0};
//                sideparty[1] = new int[]{2, 1, 0};
//                sidepartx[2] = new int[]{2, 2, 2};
//                sideparty[2] = new int[]{0, 1, 2};
//                sidepartx[3] = new int[]{2, 2, 2};
//                sideparty[3] = new int[]{0, 1, 2};
//            }
//            else if (side==4) {
//                sideindex = new int[]{0,5,1,3};
//                sidepartx[0] = new int[]{0, 1, 2};
//                sideparty[0] = new int[]{2, 2, 2};
//                sidepartx[1] = new int[]{0, 0, 0};
//                sideparty[1] = new int[]{0, 1, 2};
//                sidepartx[2] = new int[]{2, 1, 0};
//                sideparty[2] = new int[]{0, 0, 0};
//                sidepartx[3] = new int[]{2, 2, 2};
//                sideparty[3] = new int[]{2, 1, 0};
//            }
//            else {
//                sideindex = new int[]{0,2,1,4};
//                sidepartx[0] = new int[]{0, 0, 0};
//                sideparty[0] = new int[]{0, 1, 2};
//                sidepartx[1] = new int[]{0, 0, 0};
//                sideparty[1] = new int[]{0, 1, 2};
//                sidepartx[2] = new int[]{0, 0, 0};
//                sideparty[2] = new int[]{0, 1, 2};
//                sidepartx[3] = new int[]{2, 2, 2};
//                sideparty[3] = new int[]{2, 1, 0};
//            }
//        }
//
//        for (int l=0; l<3; l++) {
//            buffer[l] = cube[sideindex[3]][sidepartx[3][l]][sideparty[3][l]];
//        }
//        for (int i=3; i>=0; i--) {
//            for (int j=0; j<3; j++) {
//                if (i > 0) {
//                    cube[sideindex[i]][sidepartx[i][j]][sideparty[i][j]] = cube[sideindex[i - 1]][sidepartx[i-1][j]][sideparty[i-1][j]];
//                } else {
//                    cube[sideindex[i]][sidepartx[i][j]][sideparty[i][j]] = buffer[j];
//                }
//            }
//        }
//    }
//
//    public void u() {
//        rotateTop(1, false);
////        rotateSide(1, false);
////        temp(w.tArea2);
//        Color buffer[] = new Color[3];
//        buffer[0] = cube[2][0][0];
//        buffer[1] = cube[2][1][0];
//        buffer[2] = cube[2][2][0];
//        cube[2][0][0] = cube[3][0][0];
//        cube[2][1][0] = cube[3][1][0];
//        cube[2][2][0] = cube[3][2][0];
//        cube[3][0][0] = cube[4][0][0];
//        cube[3][1][0] = cube[4][1][0];
//        cube[3][2][0] = cube[4][2][0];
//        cube[4][0][0] = cube[5][0][0];
//        cube[4][1][0] = cube[5][1][0];
//        cube[4][2][0] = cube[5][2][0];
//        cube[5][0][0] = buffer[0];
//        cube[5][1][0] = buffer[1];
//        cube[5][2][0] = buffer[2];
//
//        w.tArea.append("U, ");
////        temp(w.tArea3);
//    }
//    public void uP() {
//        rotateTop(1, true);
//        rotateSide(1, true);
//        w.tArea.append("U', ");
//    }
//    public void r() {
//        rotateTop(3, false);
//        rotateSide(3, false);
//        w.tArea.append("R, ");
//    }
//    public void rP() {
//        rotateTop(3, true);
//        rotateSide(3, true);
//        w.tArea.append("R', ");
//    }
//    public void l() {
//        rotateTop(5, false);
//        rotateSide(5, false);
//        w.tArea.append("L, ");
//    }
//    public void lP() {
//        rotateTop(5, true);
//        rotateSide(5, true);
//        w.tArea.append("L', ");
//    }
//    public void d() {
//        rotateTop(0, false);
//        rotateSide(0, false);
//        w.tArea.append("D, ");
//    }
//    public void dP() {
//        rotateTop(0, true);
//        rotateSide(0, true);
//        w.tArea.append("D', ");
//    }
//    public void f() {
//        rotateTop(2, false);
//        rotateSide(2, false);
//        w.tArea.append("F, ");
//    }
//    public void fP() {
//        rotateTop(2, true);
//        rotateSide(2, true);
//        w.tArea.append("F', ");
//    }
//    public void b() {
//        rotateTop(4, false);
//        rotateSide(4, false);
//        w.tArea.append("B, ");
//    }
//    public void bP() {
//        rotateTop(4, true);
//        rotateSide(4, true);
//        w.tArea.append("B', ");
//    }
//    public void x() {
//        rotateTop(3, false);
//        rotateTop(5, true);
//        Color buffer[] = new Color[9];
//        int i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                buffer[i] = cube[0][j][k];
//                i++;
//            }
//        }
//        cube[0][0][0] = cube[4][2][2];
//        cube[0][0][1] = cube[4][2][1];
//        cube[0][0][2] = cube[4][2][0];
//        cube[0][1][0] = cube[4][1][2];
//        cube[0][1][1] = cube[4][1][1];
//        cube[0][1][2] = cube[4][1][0];
//        cube[0][2][0] = cube[4][0][2];
//        cube[0][2][1] = cube[4][0][1];
//        cube[0][2][2] = cube[4][0][0];
//        cube[4][0][0] = cube[1][2][2];
//        cube[4][0][1] = cube[1][2][1];
//        cube[4][0][2] = cube[1][2][0];
//        cube[4][1][0] = cube[1][1][2];
//        cube[4][1][1] = cube[1][1][1];
//        cube[4][1][2] = cube[1][1][0];
//        cube[4][2][0] = cube[1][0][2];
//        cube[4][2][1] = cube[1][0][1];
//        cube[4][2][2] = cube[1][0][0];
//        i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                cube[1][j][k] = cube[2][j][k];
//                cube[2][j][k] = buffer[i];
//                i++;
//            }
//        }
//    }
//    public void xP() {
//        rotateTop(3, true);
//        rotateTop(5, false);
//        Color buffer[] = new Color[9];
//        int i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                buffer[i] = cube[0][j][k];
//                cube[0][j][k] = cube[2][j][k];
//                cube[2][j][k] = cube[1][j][k];
//                i++;
//            }
//        }
//        cube[1][0][0] = cube[4][2][2];
//        cube[1][0][1] = cube[4][2][1];
//        cube[1][0][2] = cube[4][2][0];
//        cube[1][1][0] = cube[4][1][2];
//        cube[1][1][1] = cube[4][1][1];
//        cube[1][1][2] = cube[4][1][0];
//        cube[1][2][0] = cube[4][0][2];
//        cube[1][2][1] = cube[4][0][1];
//        cube[1][2][2] = cube[4][0][0];
//        cube[4][0][0] = buffer[8];
//        cube[4][0][1] = buffer[7];
//        cube[4][0][2] = buffer[6];
//        cube[4][1][0] = buffer[5];
//        cube[4][1][1] = buffer[4];
//        cube[4][1][2] = buffer[3];
//        cube[4][2][0] = buffer[2];
//        cube[4][2][1] = buffer[1];
//        cube[4][2][2] = buffer[0];
//    }
//    public void y() {
//        rotateTop(1, false);
//        rotateTop(0, true);
//        Color buffer[] = new Color[9];
//        int i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                buffer[i] = cube[2][j][k];
//                cube[2][j][k] = cube[3][j][k];
//                cube[3][j][k] = cube[4][j][k];
//                cube[4][j][k] = cube[5][j][k];
//                cube[5][j][k] = buffer[i];
//                i++;
//            }
//        }
//    }
//    public void yP() {
//        rotateTop(1, true);
//        rotateTop(0, false);
//        Color buffer[] = new Color[9];
//        int i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                buffer[i] = cube[2][j][k];
//                cube[2][j][k] = cube[5][j][k];
//                cube[5][j][k] = cube[4][j][k];
//                cube[4][j][k] = cube[3][j][k];
//                cube[3][j][k] = buffer[i];
//                i++;
//            }
//        }
//    }
//    public void z() {
//        rotateTop(2, false);
//        rotateTop(4, true);
//        Color buffer[] = new Color[9];
//        int i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                buffer[i] = cube[0][j][k];
//                i++;
//            }
//        }
//        cube[0][0][0] = cube[3][0][2];
//        cube[0][0][1] = cube[3][1][2];
//        cube[0][0][2] = cube[3][2][2];
//        cube[0][1][0] = cube[3][0][1];
//        cube[0][1][1] = cube[3][1][1];
//        cube[0][1][2] = cube[3][2][1];
//        cube[0][2][0] = cube[3][0][0];
//        cube[0][2][1] = cube[3][1][0];
//        cube[0][2][2] = cube[3][2][0];
//        cube[3][0][0] = cube[1][0][2];
//        cube[3][0][1] = cube[1][1][2];
//        cube[3][0][2] = cube[1][2][2];
//        cube[3][1][0] = cube[1][0][1];
//        cube[3][1][1] = cube[1][1][1];
//        cube[3][1][2] = cube[1][2][1];
//        cube[3][2][0] = cube[1][0][0];
//        cube[3][2][1] = cube[1][1][0];
//        cube[3][2][2] = cube[1][2][0];
//        cube[1][0][0] = cube[5][0][2];
//        cube[1][0][1] = cube[5][1][2];
//        cube[1][0][2] = cube[5][2][2];
//        cube[1][1][0] = cube[5][0][1];
//        cube[1][1][1] = cube[5][1][1];
//        cube[1][1][2] = cube[5][2][1];
//        cube[1][2][0] = cube[5][0][0];
//        cube[1][2][1] = cube[5][1][0];
//        cube[1][2][2] = cube[5][2][0];
//        cube[5][0][0] = buffer[2];
//        cube[5][0][1] = buffer[5];
//        cube[5][0][2] = buffer[8];
//        cube[5][1][0] = buffer[1];
//        cube[5][1][1] = buffer[4];
//        cube[5][1][2] = buffer[7];
//        cube[5][2][0] = buffer[0];
//        cube[5][2][1] = buffer[3];
//        cube[5][2][2] = buffer[6];
//    }
//    public void zP() {
//        rotateTop(2, true);
//        rotateTop(4, false);
//        Color buffer[] = new Color[9];
//        int i = 0;
//        for (int j=0;j<3;j++) {
//            for (int k = 0; k < 3; k++) {
//                buffer[i] = cube[0][j][k];
//                i++;
//            }
//        }
//        cube[0][0][0] = cube[5][2][0];
//        cube[0][0][1] = cube[5][1][0];
//        cube[0][0][2] = cube[5][0][0];
//        cube[0][1][0] = cube[5][2][1];
//        cube[0][1][1] = cube[5][1][1];
//        cube[0][1][2] = cube[5][0][1];
//        cube[0][2][0] = cube[5][2][2];
//        cube[0][2][1] = cube[5][1][2];
//        cube[0][2][2] = cube[5][1][2];
//        cube[5][0][0] = cube[1][2][0];
//        cube[5][0][1] = cube[1][1][0];
//        cube[5][0][2] = cube[1][0][0];
//        cube[5][1][0] = cube[1][2][1];
//        cube[5][1][1] = cube[1][1][1];
//        cube[5][1][2] = cube[1][0][1];
//        cube[5][2][0] = cube[1][2][2];
//        cube[5][2][1] = cube[1][1][2];
//        cube[5][2][2] = cube[1][1][2];
//        cube[1][0][0] = cube[3][2][0];
//        cube[1][0][1] = cube[3][1][0];
//        cube[1][0][2] = cube[3][0][0];
//        cube[1][1][0] = cube[3][2][1];
//        cube[1][1][1] = cube[3][1][1];
//        cube[1][1][2] = cube[3][0][1];
//        cube[1][2][0] = cube[3][2][2];
//        cube[1][2][1] = cube[3][1][2];
//        cube[1][2][2] = cube[3][0][2];
//        cube[3][0][0] = buffer[6];
//        cube[3][0][1] = buffer[3];
//        cube[3][0][2] = buffer[0];
//        cube[3][1][0] = buffer[7];
//        cube[3][1][1] = buffer[4];
//        cube[3][1][2] = buffer[1];
//        cube[3][2][0] = buffer[8];
//        cube[3][2][1] = buffer[5];
//        cube[3][2][2] = buffer[2];
//    }
//
//    public void reset() {
//        for (int i=0;i<6;i++) {
//            for (int j=0;j<3;j++) {
//                for (int k = 0; k < 3; k++) {
//                    cube[i][j][k] = StickerColor.values()[i].getStickerColor();
//                    solvedCube[i][j][k] = StickerColor.values()[i].getStickerColor();
//                }
//            }
//        }
//        w.tArea.setText("");
//        w.tArea2.setText("");
//        w.tArea3.setText("");
//    }
//
//    private void randomMove() {
//        Random ran = new Random();
//        int x = ran.nextInt(11);
//        switch (x) {
//            case 0: u();break;
//            case 1: uP();break;
//            case 2: r();break;
//            case 3: rP();break;
//            case 4: l();break;
//            case 5: lP();break;
//            case 6: d();break;
//            case 7: dP();break;
//            case 8: f();break;
//            case 9: fP();break;
//            case 10: b();break;
//            case 11: bP();break;
//
//        }
//    }
//    private boolean isSolved() {
//        return Arrays.deepEquals(solvedCube, cube);
//    }
//    private int isSolvedCross() {
//        int solvedCross = -1;
//        if (cube[1][1][0] == cube[1][1][1] &&
//            cube[1][0][1] == cube[1][1][1] &&
//            cube[1][2][1] == cube[1][1][1] &&
//            cube[1][1][2] == cube[1][1][1] &&
//            cube[2][1][0] == cube[2][1][1] &&
//            cube[3][1][0] == cube[3][1][1] &&
//            cube[4][1][0] == cube[4][1][1] &&
//            cube[5][1][0] == cube[5][1][1]) solvedCross = 1;
//        if (cube[0][1][0] == cube[0][1][1] &&
//            cube[0][0][1] == cube[0][1][1] &&
//            cube[0][2][1] == cube[0][1][1] &&
//            cube[0][1][2] == cube[0][1][1] &&
//            cube[2][1][2] == cube[2][1][1] &&
//            cube[3][1][2] == cube[3][1][1] &&
//            cube[4][1][2] == cube[4][1][1] &&
//            cube[5][1][2] == cube[5][1][1]) solvedCross = 0;
//        if (cube[2][1][0] == cube[2][1][1] &&
//            cube[2][0][1] == cube[2][1][1] &&
//            cube[2][2][1] == cube[2][1][1] &&
//            cube[2][1][2] == cube[2][1][1] &&
//            cube[0][1][2] == cube[0][1][1] &&
//            cube[1][1][0] == cube[1][1][1] &&
//            cube[3][0][1] == cube[3][1][1] &&
//            cube[5][2][1] == cube[5][1][1]) solvedCross = 2;
//        if (cube[3][1][0] == cube[3][1][1] &&
//            cube[3][0][1] == cube[3][1][1] &&
//            cube[3][2][1] == cube[3][1][1] &&
//            cube[3][1][2] == cube[3][1][1] &&
//            cube[0][2][1] == cube[0][1][1] &&
//            cube[1][2][1] == cube[1][1][1] &&
//            cube[2][2][1] == cube[2][1][1] &&
//            cube[4][0][1] == cube[4][1][1]) solvedCross = 3;
//        if (cube[4][1][0] == cube[4][1][1] &&
//            cube[4][0][1] == cube[4][1][1] &&
//            cube[4][2][1] == cube[4][1][1] &&
//            cube[4][1][2] == cube[4][1][1] &&
//            cube[0][1][2] == cube[0][1][1] &&
//            cube[1][1][0] == cube[1][1][1] &&
//            cube[3][2][1] == cube[3][1][1] &&
//            cube[5][0][1] == cube[5][1][1]) solvedCross = 4;
//        if (cube[5][1][0] == cube[5][1][1] &&
//            cube[5][0][1] == cube[5][1][1] &&
//            cube[5][2][1] == cube[5][1][1] &&
//            cube[5][1][2] == cube[5][1][1] &&
//            cube[0][0][1] == cube[0][1][1] &&
//            cube[1][0][1] == cube[1][1][1] &&
//            cube[2][0][1] == cube[2][1][1] &&
//            cube[4][2][1] == cube[4][1][1]) solvedCross = 5;
//        return solvedCross;
//    }
//
//    public boolean isInG1() {
//        boolean isInG1 = false;
//        if ((((cube[1][0][0] == cube[1][1][1]) || (cube[1][0][0] == cube[0][1][1])) &&
//             ((cube[1][0][1] == cube[1][1][1]) || (cube[1][0][1] == cube[0][1][1])) &&
//             ((cube[1][0][2] == cube[1][1][1]) || (cube[1][0][2] == cube[0][1][1])) &&
//             ((cube[1][1][0] == cube[1][1][1]) || (cube[1][1][0] == cube[0][1][1])) &&
//             ((cube[1][1][1] == cube[1][1][1]) || (cube[1][1][1] == cube[0][1][1])) &&
//             ((cube[1][1][2] == cube[1][1][1]) || (cube[1][1][2] == cube[0][1][1])) &&
//             ((cube[1][2][0] == cube[1][1][1]) || (cube[1][2][0] == cube[0][1][1])) &&
//             ((cube[1][2][1] == cube[1][1][1]) || (cube[1][2][1] == cube[0][1][1])) &&
//             ((cube[1][2][2] == cube[1][1][1]) || (cube[1][2][2] == cube[0][1][1])) &&
//        ((cube[0][0][0] == cube[1][1][1]) || (cube[0][0][0] == cube[0][1][1])) &&
//        ((cube[0][0][1] == cube[1][1][1]) || (cube[0][0][1] == cube[0][1][1])) &&
//        ((cube[0][0][2] == cube[1][1][1]) || (cube[0][0][2] == cube[0][1][1])) &&
//        ((cube[0][1][0] == cube[1][1][1]) || (cube[0][1][0] == cube[0][1][1])) &&
//        ((cube[0][1][1] == cube[1][1][1]) || (cube[0][1][1] == cube[0][1][1])) &&
//        ((cube[0][1][2] == cube[1][1][1]) || (cube[0][1][2] == cube[0][1][1])) &&
//        ((cube[0][2][0] == cube[1][1][1]) || (cube[0][2][0] == cube[0][1][1])) &&
//        ((cube[0][2][1] == cube[1][1][1]) || (cube[0][2][1] == cube[0][1][1])) &&
//        ((cube[0][2][2] == cube[1][1][1]) || (cube[0][2][2] == cube[0][1][1]))) && (
//                ((cube[2][2][1] != cube[1][1][1]) && (cube[2][2][1] != cube[0][1][1])) &&
//                ((cube[2][2][1] != cube[1][1][1]) && (cube[2][2][1] != cube[0][1][1])) &&
//                ((cube[2][0][1] != cube[1][1][1]) && (cube[2][0][1] != cube[0][1][1])) &&
//                ((cube[2][0][1] != cube[1][1][1]) && (cube[2][0][1] != cube[0][1][1])) &&
//                ((cube[5][2][1] != cube[1][1][1]) && (cube[2][2][1] != cube[0][1][1])) &&
//                ((cube[5][2][1] != cube[1][1][1]) && (cube[5][2][1] != cube[0][1][1])) &&
//                ((cube[5][0][1] != cube[1][1][1]) && (cube[5][0][1] != cube[0][1][1])) &&
//                ((cube[5][0][1] != cube[1][1][1]) && (cube[5][0][1] != cube[0][1][1])) &&
//                        ((cube[3][2][1] != cube[1][1][1]) && (cube[3][2][1] != cube[0][1][1])) &&
//                        ((cube[3][2][1] != cube[1][1][1]) && (cube[3][2][1] != cube[0][1][1])) &&
//                        ((cube[3][0][1] != cube[1][1][1]) && (cube[3][0][1] != cube[0][1][1])) &&
//                        ((cube[3][0][1] != cube[1][1][1]) && (cube[3][0][1] != cube[0][1][1])) &&
//                        ((cube[4][2][1] != cube[1][1][1]) && (cube[4][2][1] != cube[0][1][1])) &&
//                        ((cube[4][2][1] != cube[1][1][1]) && (cube[4][2][1] != cube[0][1][1])) &&
//                        ((cube[4][0][1] != cube[1][1][1]) && (cube[4][0][1] != cube[0][1][1])) &&
//                        ((cube[4][0][1] != cube[1][1][1]) && (cube[4][0][1] != cube[0][1][1])))) isInG1 = true;
//        return isInG1;
//
//    }
//
//
//    public void scramble(int l) {
//        long n = System.currentTimeMillis();
//        for (int i=0; i<l;i++) {
//            randomMove();
//        }
//        System.out.println(System.currentTimeMillis() - n);
//    }
//
//
//    public void solve() {
//        w.tArea.setText("");
//        counter = 0;
//        long t = System.currentTimeMillis();
//        tempCopy = new Color[6][3][3];
//        for (int i=0;i<6;i++) {
//            for (int j=0;j<3;j++) {
//                for (int k = 0; k < 3; k++) {
//                    tempCopy[i][j][k] = cube[i][j][k];
//                }
//            }
//        }
//        while (!isInG1()) {
//            for (int i=0;i<6;i++) {
//                for (int j=0;j<3;j++) {
//                    for (int k = 0; k < 3; k++) {
//                        cube[i][j][k] = tempCopy[i][j][k];
//                    }
//                }
//            }
//            w.tArea = new JTextArea();
//            for (int i = 0; i < 26; i++) {
//                randomMove();
//                if (isInG1()) break;
//                else;
//            }
//            counter++;
//        }
//        w.tArea.append("Done in " + (System.currentTimeMillis()-t) + "ms and " + counter + " tries!");
//        System.out.println(w.tArea.getText());
//
//    }
//
////    public void solve() {
////        w.tArea.setText("");
////        counter = 0;
////        long t = System.currentTimeMillis();
////        tempCopy = new Color[6][3][3];
////        for (int i=0;i<6;i++) {
////            for (int j=0;j<3;j++) {
////                for (int k = 0; k < 3; k++) {
////                    tempCopy[i][j][k] = cube[i][j][k];
////                }
////            }
////        }
////        while (isSolvedCross()==-1) {
////            for (int i=0;i<6;i++) {
////                for (int j=0;j<3;j++) {
////                    for (int k = 0; k < 3; k++) {
////                        cube[i][j][k] = tempCopy[i][j][k];
////                    }
////                }
////            }
////            w.tArea.setText("");
////            for (int i = 0; i < 8; i++) {
////                randomMove();
////                if (isSolvedCross()>-1) break;
////                else;
////            }
////            counter++;
////        }
////        w.tArea.append("Done in " + (System.currentTimeMillis()-t) + "ms and " + counter + " tries!");
////        System.out.println(w.tArea.getText());
////
////    }
//
//
//
//
//
//
////
////    public void solve() {
////        w.tArea.setText("");
////        counter = 0;
////        long t = System.currentTimeMillis();
////
////
////
////
////
////
////
////
////
////
////        w.tArea.append("Done in " + (System.currentTimeMillis()-t) + "ms and " + counter + " tries!");
////        System.out.println(w.tArea.getText());
////    }
////
////
////
}