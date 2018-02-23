package com.andyadc.tomcat.connect.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * @author andy.an
 * @since 2018/2/11
 */
public class HttpResponse implements HttpServletResponse {

    // the default buffer size
    private static final int BUFFER_SIZE = 1024;
    protected byte[] buffer = new byte[BUFFER_SIZE];
    protected int bufferCount = 0;
    /**
     * Has this response been committed yet?
     */
    protected boolean committed = false;
    /**
     * The actual number of bytes written to this Response.
     */
    protected int contentCount = 0;
    /**
     * The content length associated with this Response.
     */
    protected int contentLength = -1;
    /**
     * The content type associated with this Response.
     */
    protected String contentType = null;
    /**
     * The character encoding associated with this Response.
     */
    protected String encoding = null;
    HttpRequest request;
    OutputStream output;
    PrintWriter writer;

    public HttpResponse(OutputStream output) {
        this.output = output;
    }

    public OutputStream getOutput() {
        return this.output;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public void finishResponse() {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String name) {
        return false;
    }

    @Override
    public String encodeURL(String url) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String url) {
        return null;
    }

    @Override
    public String encodeUrl(String url) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return null;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {

    }

    @Override
    public void sendError(int sc) throws IOException {

    }

    @Override
    public void sendRedirect(String location) throws IOException {

    }

    @Override
    public void setDateHeader(String name, long date) {

    }

    @Override
    public void addDateHeader(String name, long date) {

    }

    @Override
    public void setHeader(String name, String value) {

    }

    @Override
    public void addHeader(String name, String value) {

    }

    @Override
    public void setIntHeader(String name, int value) {

    }

    @Override
    public void addIntHeader(String name, int value) {

    }

    @Override
    public void setStatus(int sc) {

    }

    @Override
    public void setStatus(int sc, String sm) {

    }
}
