package com.bugbusters.lajarus.util;

import android.util.Log;

import com.bugbusters.lajarus.entity.Pair;

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

    public static JSONObject performPOST(String url, JSONObject body) {
        return performPOST(url, body, null);
    }

    public static JSONObject performPOST(String url, JSONObject body, String token) {
        JSONObject response = null;

        response = performHttpRequest(url, "POST", token, body);

        return response;
    }

    /*private static JSONObject makeHttpPost(String urlStr, JSONObject body)
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

        //if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
        //    throw new RuntimeException("Failed : HTTP error code : "
        //           + conn.getResponseCode());
        //}

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
    }*/

    public static JSONObject performGET(final String urlStr, List<Pair> params, String token) {
        JSONObject response = null;

        try {
            String url = urlStr.concat(getQuery(params));
            response = performHttpRequest(url, "GET", token, null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static JSONObject performHttpRequest(
            String urlStr, String method, String token, JSONObject body) {
        InputStream is = null;
        String json = "";
        JSONObject jsonObj = null;

        // Making HTTP request
        try {
            URL url = new URL(urlStr);

            Log.d("URL Request", url.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            if(token != null) {
                conn.setRequestProperty("Authorization", token);
            }
            conn.setRequestMethod(method);
            conn.connect();

            if(body != null) {
                OutputStream os = conn.getOutputStream();
                os.write(body.toString().getBytes("UTF-8"));
                os.flush();
            }

            Log.d("Response Code: ", Integer.toString(conn.getResponseCode()));

            is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            Log.e("HTTP Request", e.toString());
        }

        // Getting JSON Response
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jsonObj;
    }

    private static String getQuery(List<Pair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Pair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
