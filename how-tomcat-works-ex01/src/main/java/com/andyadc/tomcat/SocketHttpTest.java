package com.andyadc.tomcat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket 代表客户端套接字
 *
 * @author andy.an
 * @since 2018/2/5
 */
public class SocketHttpTest {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();


        // Send a HTTP request to the web server
        PrintWriter writer = new PrintWriter(outputStream, true);
        writer.println("GET / HTTP/1.1");
        writer.println("Host: localhost:8080");
        writer.println("Connection: Close");
        writer.println();

        // read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuffer buffer = new StringBuffer(8096);
        boolean loop = true;
        while (loop) {
            if (reader.ready()) {
                int i = 0;
                while (i != -1) {
                    i = reader.read();
                    buffer.append((char) i);
                }
                loop = false;
            }
            Thread.sleep(50);
        }

        System.out.println(buffer.toString());
        socket.close();
    }
}
