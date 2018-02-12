package com.andyadc.tomcat.connect.http;

import com.andyadc.tomcat.ServletProcessor;
import com.andyadc.tomcat.StaticResourceProcessor;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author andy.an
 * @since 2018/2/9
 */
public class HttpProcessor {

    protected String method = null;
    protected String queryString = null;
    /**
     * The HttpConnector with which this processor is associated.
     */
    private HttpConnector connector = null;
    private HttpRequest request;
    private HttpResponse response;
    private HttpRequestLine requestLine = new HttpRequestLine();

    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;

        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            // create HttpRequest object and parse
            request = new HttpRequest(input);

            // create HttpResponse object
            response = new HttpResponse(output);
            response.setRequest(request);

            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();

            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();

            }

            // Close the socket
            socket.close();
            // no shutdown for this application
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
