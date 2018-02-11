package com.andyadc.tomcat.connect;

import com.andyadc.tomcat.connect.http.HttpRequest;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author andy.an
 * @since 2018/2/9
 */
public class RequestStream extends ServletInputStream {

    /**
     * Has this stream been closed?
     */
    protected boolean closed = false;


    /**
     * The number of bytes which have already been returned by this stream.
     */
    protected int count = 0;


    /**
     * The content length past which we will not read, or -1 if there is
     * no defined content length.
     */
    protected int length = -1;

    /**
     * The underlying input stream from which we should read data.
     */
    protected InputStream stream = null;

    /**
     * Construct a servlet input stream associated with the specified Request.
     *
     * @param request The associated request
     */
    public RequestStream(HttpRequest request) {
        super();
        closed = false;
        count = 0;


    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
