package com.andyadc.tomcat;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author andy.an
 * @since 2018/2/7
 */
public class ServletProcessor1 {

    public void process(Request request, Response response) {

        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler handler = null;

            File classpath = new File("files");

            String repository = (new URL("file", null, classpath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null, repository, handler);

            loader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Class<?> clazz = null;

        try {
            clazz = loader.loadClass(servletName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet) clazz.newInstance();
            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
