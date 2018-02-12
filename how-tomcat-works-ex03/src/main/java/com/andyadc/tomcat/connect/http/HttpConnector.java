package com.andyadc.tomcat.connect.http;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author andy.an
 * @since 2018/2/9
 */
public class HttpConnector implements Runnable {

    volatile boolean stopped;
    private static final String SCHEME = "http";

    public String getScheme() {
        return SCHEME;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped) {
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            //TODO
            HttpProcessor processor = new HttpProcessor();


        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
