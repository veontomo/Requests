package com.veontomo;

import java.util.ArrayList;
class Main {

    public static void main(String[] args) throws InterruptedException {
        final String[] clickPool = new String[]{
                "http://192.168.5.95/news/venditori/venditori",
                "http://192.168.5.95/news/venditori/rappresentanti",
                "http://192.168.5.95/news/no-such-route/no",
                "http://192.168.5.95/news/rappresentanti/start?annuncio=00000001819",
                "http://192.168.5.95/news/images/venditori/TRACK-QUERY/logo_natale_2015.jpg"
        };
//        final String[] viewPool = new String[]{
//                "http://192.168.5.95/news/images/venditori/logo_natale_2015.jpg",
//        };

        final int size = clickPool.length;
        final int ACTIONS_PER_WORKER = 5;
        final int WORKER_NUM = 1000;
        final int SLOT_NUM = 20;
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
        System.out.println("Number of the actions: " + actionsHttp);
        System.out.println("Starting the game.");
        c.start();
    }


}
