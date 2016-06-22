package com.veontomo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Andrey on 22/06/2016.
 */
public class SiteVisitor extends Counterable {
    private int marker;
    private Counter host;
    private final String url;
    private final String charset = "UTF-8";

    public SiteVisitor(String url, int marker) {
        this.url = url;
        this.marker = marker;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    @Override
    public void execute() throws InterruptedException {
        System.out.println("visitor " + marker + " has started.");
        Thread.sleep(1000);
//        URLConnection connection;
//        InputStream response;
//        String header;
//        for (int i = 0; i < 1; i++) {
//            try {
//                connection = new URL(url).openConnection();
//                connection.setRequestProperty("Accept-Charset", charset);
//                connection.addRequestProperty("User-Agent", "Mozilla/4.76");
//                response = connection.getInputStream();
//                if (connection == null) {
//                    System.out.println("no responce!");
//                }
//            } catch (MalformedURLException ex) {
//                System.out.println("url " + url + " is not a valid one.");
//            } catch (IOException ex) {
//                System.out.println("sender " + marker + ", try: " + i + " can not connect to " + url);
//                ex.printStackTrace();
//            }
//
//        }
        System.out.println("visitor " + marker + " has finished.");

    }

    ;

    @Override
    public void bind(Counter c) {
        this.host = c;
    }

    @Override
    public void onFinish() {
        host.free();
    }
}
