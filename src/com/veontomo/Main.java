package com.veontomo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] pool = new String[]{"http://www.venditori.it", "http://www.rappresentanti.it"};
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new UrlRequest(pool[0]));

        actions.add(new UrlRequest(pool[1]));
        Counter c = new Counter(2);
        for (int i = 0; i < 10; i++) {
            Worker v = new Worker(actions);
            c.enqueue(v);
        }
        c.start();
    }


}
