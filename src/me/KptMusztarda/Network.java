package me.KptMusztarda;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

enum Status {

    IMPROPER_NETWORK("Improper network"),
    DISCONNECTED("Disconnected"),
    CONNECTING("Connecting..."),
    CONNECTED("Connected");

    private String str;

    private Status(String str) {
        this.str = str;
    }

    public String getString() {
        return str;
    }
}

public class Network {


    private String ip = "192.168.0.115";
    private int port = 1337;

    private static final int MAX_CONNECTION_ATTEMPTS = 60;
    private static final int SERVER_ANSWER_LENGTH = 60;


    private static Network instance = new Network();

    public static Network getInstance() {
        return instance;
    }

    private Status status = Status.DISCONNECTED;
    private Socket sock;
    private SocketAddress socketAddress;

    private boolean allowReconnecting = true;
    private final int timeout = 1; // delay between connecting attempts in seconds
    private int connectionAttempt = 0;

    private Cube cube;

    Network() {

        socketAddress = new InetSocketAddress(ip, port);





    }

    void setCube(Cube cube) {
        this.cube = cube;
    }

    public void connect() {
//        System.out.println("connect()");
        if(!isConnectedOrConnecting()) {

            if (status.equals(Status.DISCONNECTED)) {
                new Thread(() -> {
                    allowReconnecting = true;
                    connectionAttempt = 0;
                    setStatus(Status.CONNECTING);
                    //System.out.println("Connect thread started");
                    while (status.equals(Status.CONNECTING) && allowReconnecting && connectionAttempt <= MAX_CONNECTION_ATTEMPTS) {
                        try {
                            connectionAttempt++;
                            System.out.println("Connecting... attempt: " + connectionAttempt);
                            sock = new Socket();
                            sock.connect(socketAddress, 1000);
//                            System.out.println("Connected");
                            setStatus(Status.CONNECTED);
                            listen();
                        } catch (IOException e) {
                            System.out.println("Connection failed (" + e.getMessage() + "). Retrying in " + timeout + " seconds");
//                            System.out.println("Status: " + status.getString() + "; allowReconnecting: " + (allowReconnecting ? "true" : "false") + "; connectionAttempt: " + connectionAttempt);
                            try {
                                Thread.sleep(timeout * 1000);
                            } catch (InterruptedException e1) {
                                System.out.println(e1);
                            }
                        }
                    }
//                    System.out.println("Connect thread finished");
                }).start();
            }
        } else {
            System.out.println("Already connected or connecting");
        }
    }

    public void send(final String string) {
        new Thread(() -> {
            try {
                if(status.equals(Status.CONNECTED)) {

//                    OutputStreamWriter out = new OutputStreamWriter(sock.getOutputStream());
                    PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);


//                    pw.print(string);
//                    pw.flush();

                    pw.println(string);


                } else {
                    System.out.println("Sending failed. Not connected");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }).start();
    }

    private void listen() {
        new Thread(() -> {
            try {
                InputStreamReader in = new InputStreamReader(sock.getInputStream());
                int n;
                char cbuf[] = new char[SERVER_ANSWER_LENGTH];
                while (true) {
                    n = in.read(cbuf, 0, cbuf.length);
                    if(n > 0) {
                        String receivedData = new String(cbuf).substring(0, n).trim();
                        System.out.println("Received: " + receivedData);

                        switch(Integer.parseInt(Character.toString(receivedData.charAt(0)))) {
                            case 0:
                                cube.update(receivedData.substring(2));
                                break;
                            case 1:
                                cube.setColor(receivedData.substring(2));
                                break;
                        }

                    } else if(n == -1){
                        setStatus(Status.DISCONNECTED);
                        System.out.println("Disconnected");
                        if(!status.equals(Status.CONNECTING) && allowReconnecting) {
                            System.out.println("Reconnecting from listen loop");
                            connect();
                        }
                        throw new IOException("Connection closed");
                    }
                    cbuf = new char[SERVER_ANSWER_LENGTH];
                }
            } catch (IOException e) {
                System.out.println("Stopping listening loop (" + e.getMessage() + ")");
                closeSocket();
            }
        }).start();
    }

    protected boolean isConnectedOrConnecting() {
        return status.equals(Status.CONNECTING) || status.equals(Status.CONNECTED);
    }

    private void setStatus(Status status) {
        if(this.status != status) {
            this.status = status;
            System.out.println("New status: " + status);
        }
    }

    void closeSocket() {
        if (status.equals(Status.CONNECTED)) {
            System.out.println("Closing socket");
//            allowReconnecting = false;
            if (sock != null) {
                try {
                    sock.shutdownOutput();
                } catch (IOException e) {
                    //System.out.println(e);
                }
                try {
                    sock.close();
                } catch (IOException e) {
                    //System.out.println(e);
                }
            }
        } else if (status.equals(Status.CONNECTING)) {
//            allowReconnecting = false;
        }
    }

}