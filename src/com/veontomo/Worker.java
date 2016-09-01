package com.veontomo;

import java.util.ArrayList;

/**
 * A worker that performs actions consequently.
 */
class Worker extends Counterable {
    private static int instanceCounter = 0;
    private final int marker;
    private final ArrayList<Action> actions;

    Worker(final ArrayList<Action> actions) {
        this.actions = actions;
        instanceCounter++;
        this.marker = instanceCounter;
    }



    public Summary call() {
        final Summary summary = new DetailedSummary();
        final long time = System.currentTimeMillis();
        System.out.println("worker " + marker + " has started");
        for (Action action : actions) {
            summary.merge(action.execute());
        }
        System.out.println("worker " + marker + " has finished in " + (System.currentTimeMillis()-time) + " ms.");
        return summary;

    }
}

