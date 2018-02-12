package com.andyadc.tomcat.startup;

import com.andyadc.tomcat.connect.http.HttpConnector;

/**
 * @author andy.an
 * @since 2018/2/9
 */
public final class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
