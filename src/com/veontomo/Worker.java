package com.veontomo;

import java.util.ArrayList;

/**
 * Created by Andrey on 22/06/2016.
 */
public class Worker extends Counterable {
    private static int instanceCounter = 0;
    private final int marker;
    private Counter host;
    private final ArrayList<Action> actions;

    public Worker(final ArrayList<Action> actions) {
        this.actions = actions;
        instanceCounter++;
        this.marker = instanceCounter;
    }


    @Override
    public void onStart() {
        System.out.println("worker " + marker + " has started.");
        actions.forEach(x -> x.execute());

    }

    public void bind(Counter c) {
        this.host = c;
    }

    @Override
    public void onFinish() {
        System.out.println("worker " + marker + " has finished.");
        host.free();
    }
}
