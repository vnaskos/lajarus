package com.bugbusters.lajarus.util;

/**
 * Created by vasilis on 11/27/16.
 */

public class Config {

    private static final String BASE_URL = "155.254.33.123";
    private static final String APPLICATION_PATH = "/";
    private static final String WEBSOCKET_PORT = ":8081";
    private static final String HTTP_PORT = ":8081";

    public static String getHttpURL() {
        return "http://"
                .concat(BASE_URL)
                .concat(HTTP_PORT)
                .concat(APPLICATION_PATH);
    }

    public static String getWebSocketURL() {
        return "ws://"
                .concat(BASE_URL)
                .concat(WEBSOCKET_PORT)
                .concat(APPLICATION_PATH);
    }

}
