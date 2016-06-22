package com.veontomo;


/**
 * Created by Andrey on 21/06/2016.
 */

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demonstration of some concurrency-specific troubles.
 * http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 */
public class Worker {

    private final int threadNum;

    private int accum = 0;

    public Worker(int iterations) {
        this.threadNum = iterations;
    }

    public int getAccum() {
        return accum;
    }


    synchronized private void increment() {
        this.accum++;
    }

    public void run() throws InterruptedException {

        Thread[] threads = new Thread[threadNum];
        for (int counter = 0; counter < threadNum; counter++) {
            threads[counter] = new Thread(new Runnable() {
                @Override
                public void run() {
                    increment();
                }
            });
            threads[counter].start();
//            threads[counter].join();
        }

    }
}
