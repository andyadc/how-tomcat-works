package com.andyadc.tomcat.connect.http;

import java.io.File;

/**
 * @author andy.an
 * @since 2018/2/9
 */
public class Constants {

    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "files";
    public static final String Package = "com.andyadc.tomcat.connect.http";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
    public static final int PROCESSOR_IDLE = 0;
    public static final int PROCESSOR_ACTIVE = 1;

}
