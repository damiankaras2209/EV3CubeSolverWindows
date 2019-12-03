package me.KptMusztarda;

import me.KptMusztarda.GUI.Log;

import javax.swing.*;

public class NetworkData {

    public static final String DATATYPE_CUBE = "CB";
    public static final String DATATYPE_COMMAND = "CMD";
    public static final String DATATYPE_LOG = "L";

    private static NetworkData instance = new NetworkData();

    private Cube cube;
    private Log textArea;

    NetworkData() {
//        textArea = new Log();
    }

    static NetworkData getInstance() {
        return instance;
    }

    void setCube(Cube cube) {
        this.cube = cube;
    }

    void setLogTextArea(Log textArea) {
//        System.out.println("Text area set");
        this.textArea = textArea;

//        this.textArea.append("EWRHGSDEH");
    }

    Log getLog() {
        return textArea;
    }

    void interpret(String input) {

        int prefixEnd = input.indexOf("/");
        String prefix = input.substring(0, prefixEnd);
        String data = input.substring(prefixEnd + 1);

        System.out.println("Prefix: " + prefix + ", Data: " + data);

        switch(prefix) {
            case DATATYPE_CUBE:
                Main.queue.add(() -> Main.cube.update(data));
                break;
            case DATATYPE_LOG:
                Main.queue.add(() -> Main.log.append(data));
                break;
        }

    }
}
