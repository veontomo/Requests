package com.veontomo;

import java.io.IOException;
import java.net.*;
import java.util.Random;

/**
 * Created by Andrey on 22/06/2016.
 */
public class UrlRequest implements Action {
    private final String url;

    private final String[] userAgents = BrowserData.userAgents;

    private final String[] encodings = BrowserData.encodings;

    public UrlRequest(String url) {
        this.url = url;
    }

    @Override
    public void execute() {
        // See
        // http://www.journaldev.com/7148/java-httpurlconnection-example-java-http-request-get-post
        // for details
//        System.out.println("request url:" + url);
        HttpURLConnection connection;
        Random generator = new Random();
        final int userAgentSize = userAgents.length;
        final int encodingSize = encodings.length;
        int responseCode;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", encodings[generator.nextInt(encodingSize)]);
            connection.addRequestProperty("User-Agent", userAgents[generator.nextInt(userAgentSize)]);
            connection.getResponseCode();
            responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("unexpected response code: " + responseCode + " when getting an url " + url);
            }
        } catch (MalformedURLException ex) {
            System.out.println("error for url " + url + ": " + ex.getMessage());
        } catch (ConnectException ex) {
            System.out.println("connecting to " + url + ": " + ex.getMessage());
        } catch (SocketException ex) {
            System.out.println("can not connect to " + url + ": " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("can not connect to " + url + ": " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
