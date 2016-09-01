package com.veontomo;

import java.io.IOException;
import java.net.*;
import java.util.Random;

/**
 * Retrieves data from given url
 */
class UrlRequest implements Action {
    private final String url;

    private final String[] userAgents = BrowserData.userAgents;

    private final String[] encodings = BrowserData.encodings;

    public UrlRequest(String host, String path) {
        this.url = host + path;
    }


    @Override
    public Summary execute() {
        Summary summary = new DetailedSummary();
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
            responseCode = connection.getResponseCode();

            summary.store(String.valueOf(responseCode));
        } catch (IOException ex) {
            System.out.println("Attention: exception when connecting to " + url + ": " + ex.getMessage());
            ex.printStackTrace();
            summary.store(ex.getMessage());
        }
        return summary;
    }


}
