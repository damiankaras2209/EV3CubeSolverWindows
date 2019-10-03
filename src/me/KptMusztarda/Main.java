package me.KptMusztarda;

import me.KptMusztarda.GUI.GUI;
import me.KptMusztarda.GUI.Renderer;
import me.KptMusztarda.GUI.Window;

public class Main {

    public static void main(String[] args) {
        Window w = new Window();

        Cube cube = new Cube();

        Network.getInstance().setCube(cube);

        Renderer r = new Renderer(5, 5);
        cube.setRenderer(r);
        r.setCube(cube);

        w.add(r);

//        cube.setColor("U0");
//        cube.setColor("L1");
//        cube.setColor("D2");
//        cube.setColor("R3");
//        cube.setColor("F4");
//        cube.setColor("B5");

//        cube.update("LLLLLLLLL-UU-UU-UU------------------------------------");

        GUI gui = new GUI(500, 5);

        w.add(gui);

        w.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
        w.setVisible(true);
    }
}
