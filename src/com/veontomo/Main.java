package com.veontomo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length != 4) {
            System.out.println("Please, provide three arguments: hostname, entry point, ip of database server and the password to access the database.");
            System.out.println("Example: http://127.0.0.1 /repo 127.0.0.2 123456789");
            return;
        }
        final String host = args[0];
        final String entryPoint = args[1];
        final String serverIP = args[2];
        final String password = args[3];

        System.out.println("Hostname is " + host + ", DB Server ip: " + serverIP + ", password: " + password);

        ServerConfigurator config = new ServerConfigurator(args[0]);

        HashMap<String, String> routes = new HashMap<>();
        routes.put("venditori/venditori", "http://www.venditori.it");
        routes.put("venditori/offerta", "http://www.venditori.it/fiera_degli_agenti_di_commercio.php");

        HashMap<String, String> dbSettings = new HashMap<>();
        dbSettings.put("User_Name", "advlite_dev");
        dbSettings.put("Database", "advlite_dev");
        dbSettings.put("DriverID", "MySQL");
        dbSettings.put("Password", password);
        dbSettings.put("Server", serverIP);

        config.putData("/news/routes/add", routes);
        config.putData("/news/storage/set", dbSettings);

        final String[] clickPool = routes.keySet().toArray(new String[]{});
        final int size = clickPool.length;
        final int ACTIONS_PER_WORKER = 10;
        final int WORKER_NUM = 20;
        final int SLOT_NUM = 20;
        int actionsHttp = 0;
        ArrayList<Action> actions;
        Counter c = new Counter(SLOT_NUM);
        for (int i = 0; i < WORKER_NUM; i++) {
            actions = new ArrayList<>();
            for (int j = 0; j < ACTIONS_PER_WORKER; j++) {
                actions.add(new UrlRequest(host + entryPoint + "/", clickPool[actionsHttp % size]));
                actionsHttp++;

            }
            Worker v = new Worker(actions);
            c.enqueue(v);
        }


        System.out.println("Number of the actions: " + actionsHttp);
        System.out.println("Starting the game.");
        c.start();
    }


}
