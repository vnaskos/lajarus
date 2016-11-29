package com.bugbusters.lajarus.ws;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vasilis on 11/29/16.
 */

public interface WebSocketMessageListener {

    void onMessage(JSONObject msg) throws JSONException;

}
