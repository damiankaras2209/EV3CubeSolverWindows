package me.damiankaras.ev3cubesolver.windows;

import me.damiankaras.ev3cubesolver.windows.GUI.Log;

import cs.min2phase.Search;


public class NetworkData {

    public static final String DATATYPE_CUBE = "CB";
    public static final String DATATYPE_COMMAND = "CMD";
    public static final String DATATYPE_SOLVE = "SLV";
    public static final String DATATYPE_SOLUTION = "SOL";
    public static final String DATATYPE_LOG = "L";

    private static NetworkData instance = new NetworkData();

    private Log textArea;

    NetworkData() {
//        textArea = new Log();
    }

    static NetworkData getInstance() {
        return instance;
    }


    void interpret(String input) {

        int prefixEnd = input.indexOf("/");
        String prefix = input.substring(0, prefixEnd);
        String data = input.substring(prefixEnd + 1);

//        System.out.println("Prefix: " + prefix + ", Data: " + data);

        switch(prefix) {
            case DATATYPE_CUBE:
                Main.queue.add(() -> Cube.getInstance().update(data));
                break;
            case DATATYPE_SOLVE:
                Main.queue.add(() -> {
                    Search search = new Search();

                    int mask = 0;
                    mask |= false ? Search.USE_SEPARATOR : 0;
                    mask |= false ? Search.INVERSE_SOLUTION : 0;
                    mask |= true ? Search.APPEND_LENGTH : 0;

                    String out;

                    System.out.println("Solving locally... ");
                    out = search.solution(data, 21, 100, 0, mask);
                    if (out.contains("Error")) {
                        out = "error";
                    }
                    System.out.println("Solved ");
                    Network.getInstance().send(DATATYPE_SOLUTION, out);
                });
                break;
            case DATATYPE_LOG:
                Main.queue.add(() -> Main.log.append(data));
                break;
        }

    }
}
