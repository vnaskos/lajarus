package com.bugbusters.lajarus.manager;

import android.util.Log;

import com.bugbusters.lajarus.entity.Pair;
import com.bugbusters.lajarus.entity.Session;
import com.bugbusters.lajarus.entity.User;
import com.bugbusters.lajarus.util.Config;
import com.bugbusters.lajarus.util.HttpRequestDispatcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilis on 11/28/16.
 */

public class SessionManager {

    private static SessionManager instance;
    private Session activeSession;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if(instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setActiveSession(Session activeSession) {
        this.activeSession = activeSession;
    }

    public Session getActiveSession() {
        return activeSession;
    }

    public Session createSession(String token) {
        User user = null;

        try {
            user = getUserByToken(token);
        } catch (JSONException e) {
            Log.e("SessionManager", "error creating user from token");
            e.printStackTrace();
            return null;
        }

        Session s = new Session(user, token);
        s.setActivePlayerId(0);

        return s;
    }

    private User getUserByToken(String token) throws JSONException {
        List<Pair> params = new ArrayList<>();
        String url = Config.getHttpURL().concat("user");
        JSONObject response = HttpRequestDispatcher.performGET(url, params, token);

        Log.d("SessionManager", response.toString());

        return User.createFromJson(response);
    }
}
