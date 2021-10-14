package com.github.esdiac.callmonitoring;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;
import java.util.Map;

class Requester {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "https://victor.voip.ml/login";

    public String sendRequest(String username, String password) throws IOException {
        return sendPOST(username,password);
    }

    private static String sendPOST(String username, String password) throws IOException {
        final String POST_PARAMS = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        Map<String, String> parameters = new HashMap<>();

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return (response.toString());
        } else {
            System.out.println("POST request not worked");
            return null;
        }
    }
}
