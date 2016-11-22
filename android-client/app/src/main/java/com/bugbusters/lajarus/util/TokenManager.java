package com.bugbusters.lajarus.util;

import org.json.JSONObject;

/**
 * Created by vasilis on 11/22/16.
 */

public class TokenManager {

    private static TokenManager instance;

    private String token;

    private TokenManager() {

    }

    public static TokenManager getInstance() {
        if(instance == null) {
            instance = new TokenManager();
        }

        return instance;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String createToken(String username, String password) throws Exception {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        String url = "http://155.254.33.123:8081/auth/login";
        JSONObject response = HttpRequestDispatcher.performPOST(url, body);

        if (response == null) {
            throw new Exception("Token creation failed");
        }

        token = response.getString("token");

        return token;
    }
}
