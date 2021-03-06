package com.andyadc.tomcat;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author andy.an
 * @since 2018/2/5
 */
public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "files";

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    private volatile boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket;
            InputStream input;
            OutputStream output;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // Close the socket
                socket.close();

                shutdown = request.getUri().endsWith(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
