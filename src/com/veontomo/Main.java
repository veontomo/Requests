package com.veontomo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0){
            System.out.println("Please, provide the hostname as the input argument");
        }

        System.out.println("Hostname is " + args[0]);
        ServerConfigurator config = new ServerConfigurator(args[0]);

        HashMap<String, String> routes = new HashMap<>();
        routes.put("venditori/venditori", "http://www.venditori.it");
        routes.put("venditori/offerta", "http://www.venditori.it/fiera_degli_agenti_di_commercio.php");

        HashMap<String, String> dbSettings = new HashMap<>();
        dbSettings.put("User_Name", "advlite_dev");

        config.loadRoutes(routes);

        final String[] clickPool = new String[]{
                "/news/venditori/venditori",
                "/news/venditori/rappresentanti",
                "/news/no-such-route/no",
                "/news/rappresentanti/start?annuncio=00000001819",
                "/news/images/venditori/TRACK-QUERY/logo_natale_2015.jpg"
        };


        final int size = clickPool.length;
        final int ACTIONS_PER_WORKER = 2;
        final int WORKER_NUM = 20;
        final int SLOT_NUM = 10;
        int actionsHttp = 0;
        ArrayList<Action> actions;
        Counter c = new Counter(SLOT_NUM);
        for (int i = 0; i < WORKER_NUM; i++) {
            actions = new ArrayList<>();
            for (int j = 0; j < ACTIONS_PER_WORKER; j++) {
                actions.add(new UrlRequest(clickPool[actionsHttp % size]));
                actionsHttp++;

            }
            Worker v = new Worker(actions);
            c.enqueue(v);
        }


        JSONObject routes = new JSONObject();
        String url = "/news/routes/add";
        try {
            HttpURLConnection connection;
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", BrowserData.encodings[0]);
            connection.addRequestProperty("User-Agent", BrowserData.userAgents[0]);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream out = connection.getOutputStream();
            out.write(routes.toString().getBytes("UTF-8"));


            out.close();
            connection.getInputStream();
            int responseCode = connection.getResponseCode();

        } catch (IOException ex) {
            System.out.println("Attention: exception when connecting to " + url + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("Number of the actions: " + actionsHttp);
        System.out.println("Starting the game.");
//        c.start();
    }


}
