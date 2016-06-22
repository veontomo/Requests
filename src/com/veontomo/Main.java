package com.veontomo;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] pool = new String[]{
                "http://192.168.5.95/news/venditori/venditori",
                "http://192.168.5.95/news/venditori/rappresentanti",
                "http://192.168.5.95/news/car/agenti"
        };
        final int size = pool.length;
        final int MAX_ACTIONS = 5;
        int totalActions = 0;
        ArrayList<Action> actions;

        Counter c = new Counter(10);
        Random generator = new Random();
        int actionQuantity;
        for (int i = 0; i < 100; i++) {
            actionQuantity = generator.nextInt(MAX_ACTIONS);
            actions = new ArrayList<>();
            for(int j = 0; j < actionQuantity; j++){
                actions.add(new UrlRequest(pool[generator.nextInt(size)]));
                totalActions++;
            }
            Worker v = new Worker(actions);
            c.enqueue(v);
        }
        System.out.println("Total number of actions: " + totalActions);
        c.start();
    }


}
