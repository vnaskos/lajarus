package com.bugbusters.lajarus.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public final class HttpRequestDispatcher {

    private HttpRequestDispatcher() {
    }

    public static JSONObject performPOST(final String urlStr, final JSONObject body) {
        JSONObject response = null;

        try {
            response = makeHttpPost(urlStr, body);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static JSONObject makeHttpPost(String urlStr, JSONObject body)
            throws IOException, JSONException {
        URL url = new URL(urlStr);

        Log.d("URL Request", url.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        os.write(body.toString().getBytes("UTF-8"));
        os.flush();

        /*if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }*/

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String responseString = "";
        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            responseString += output;
            System.out.println(output);
        }
        conn.disconnect();

        return new JSONObject(responseString);
    }
}
