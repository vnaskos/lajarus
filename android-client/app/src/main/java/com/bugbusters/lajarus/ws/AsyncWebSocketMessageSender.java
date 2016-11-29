package com.bugbusters.lajarus.ws;

import android.os.AsyncTask;
import android.util.Log;

import com.bugbusters.lajarus.manager.WebSocketManager;

/**
 * Created by vasilis on 11/29/16.
 */

public class AsyncWebSocketMessageSender extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... strings) {
        WebSocketManager wsManager = WebSocketManager.getWebSocketManager();
        if(wsManager.getWebSocket() != null && wsManager.getWebSocket().isOpen()) {
            wsManager.sendMessage(strings[0]);
            return Boolean.TRUE;
        }

        Log.e("WsMsgSender", "Message not sent!");
        return Boolean.FALSE;
    }

}
