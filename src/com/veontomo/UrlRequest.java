package com.veontomo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
        System.out.println("url request action");

        URLConnection connection;
        InputStream response;
        String header;
        Random generator = new Random();
        final int userAgentSize = userAgents.length;
        final int encodingSize = encodings.length;
        for (int i = 0; i < 1; i++) {
            try {
                connection = new URL(url).openConnection();
                connection.setRequestProperty("Accept-Charset", encodings[generator.nextInt(encodingSize)]);
                connection.addRequestProperty("User-Agent", userAgents[generator.nextInt(userAgentSize)] );
                response = connection.getInputStream();
                if (connection == null) {
                    System.out.println("no responce!");
                }
            } catch (MalformedURLException ex) {
                System.out.println("url " + url + " is not a valid one.");
            } catch (IOException ex) {
                System.out.println("can not connect to " + url);
                ex.printStackTrace();
            }

        }
    }
}
