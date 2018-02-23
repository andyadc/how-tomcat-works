package com.andyadc.tomcat;

import com.andyadc.tomcat.connect.http.Constants;
import com.andyadc.tomcat.connect.http.HttpRequest;
import com.andyadc.tomcat.connect.http.HttpRequestFacade;
import com.andyadc.tomcat.connect.http.HttpResponse;
import com.andyadc.tomcat.connect.http.HttpResponseFacade;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author andy.an
 * @since 2018/2/9
 */
public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classpath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classpath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Class clazz = null;
        try {
            clazz = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet) clazz.newInstance();

            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);
            servlet.service(requestFacade, responseFacade);

            response.finishResponse();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
