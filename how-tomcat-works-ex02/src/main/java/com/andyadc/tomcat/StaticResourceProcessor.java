package com.andyadc.tomcat;

/**
 * @author andy.an
 * @since 2018/2/8
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
