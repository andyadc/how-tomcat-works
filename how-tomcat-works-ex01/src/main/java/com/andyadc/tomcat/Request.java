package com.andyadc.tomcat;

import java.io.InputStream;

/**
 * @author andy.an
 * @since 2018/2/5
 */
public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buffer = new byte[2048];

        try {
            i = input.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        System.out.print(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String reqStr) {
        int index1, index2;
        index1 = reqStr.indexOf(' ');
        if (index1 != -1) {
            index2 = reqStr.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return reqStr.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
