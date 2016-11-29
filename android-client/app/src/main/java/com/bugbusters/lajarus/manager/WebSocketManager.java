package com.bugbusters.lajarus.manager;

import android.util.Log;

import com.bugbusters.lajarus.util.Config;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

/**
 * Created by vasilis on 11/27/16.
 */

public class WebSocketManager {

    private static WebSocketManager instance;

    private WebSocket ws;

    private WebSocketManager() {}

    public void initWebSocket() {
        try {
            String webSocketUri = Config.getWebSocketURL()
                    .concat("lajarus");

            Log.d("Websocket", webSocketUri);
            ws = new WebSocketFactory().createSocket(webSocketUri);
        } catch (Exception ex) {
            Log.e("Init WebSocket", ex.toString());
        }
    }

    public static WebSocketManager getWebSocketManager() {
        if(instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    public WebSocket getWebSocket() {
        return ws;
    }

    public boolean connect() {
        try {
            ws.connect();
            return true;
        } catch (Exception e) {
            Log.e("WebSocket Connect", e.toString());
            return false;
        }
    }

    public void disconnect() {
        try {
            ws.disconnect();
        } catch (Exception e) {
            Log.e("WebSocket Disconnect", e.toString());
        }
    }

    public void sendMessage(final String message) {
        if(ws == null) {
            Log.e("Websocket", "uninitialized websocket");
            return;
        }

        if(!ws.isOpen()) {
            Log.e("Websocket", "closed websocket");
            return;
        }

        ws.sendText(message);
    }
}
