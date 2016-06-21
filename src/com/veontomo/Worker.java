package com.veontomo;


/**
 * Created by Andrey on 21/06/2016.
 */

/**
 * Demonstration of some concurrency-specific troubles.
 * http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 */
public class Worker {

    private final int iterations;

    private int accum = 0;

    public Worker(int iterations) {
        this.iterations = iterations;
    }

    public int getAccum() {
        return accum;
    }


    private void increment() {
        this.accum++;
    }

    public void run() {

        for (int counter = 0; counter < iterations; counter++) {
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    increment();
                }
            }
            )).start();
        }

    }
}
