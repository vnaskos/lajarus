package com.bugbusters.lajarus.ws;

import android.util.Log;

import com.bugbusters.lajarus.manager.WebSocketManager;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilis on 11/29/16.
 */

public class WebSocketMessageFetcher implements Runnable {

    private List<WebSocketMessageListener> listeners;

    public WebSocketMessageFetcher() {
        listeners = new ArrayList<>();
    }

    public void addListener(WebSocketMessageListener listener) {
        listeners.add(listener);
    }

    public void removeListener(WebSocketMessageListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        WebSocketManager wsManager = WebSocketManager.getWebSocketManager();
        wsManager.initWebSocket();
        wsManager.connect();

        wsManager.getWebSocket().addListener(new WebSocketAdapter(){
            @Override
            public void onTextMessage(WebSocket websocket, String text) throws Exception {
                Log.d("OnTextMessage", text);
                JSONObject msg = new JSONObject(text);
                updateListenersOnMessage(msg);
            }
        });
    }

    public final void updateListenersOnMessage(JSONObject msg) {
        for(WebSocketMessageListener listener : listeners) {
            try {
                listener.onMessage(msg);
            } catch (JSONException ex) {
                Log.e("wsMsgFetch", ex.toString());
            }
        }
    }
}
