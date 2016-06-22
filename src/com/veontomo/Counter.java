package com.veontomo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Queue counter: dispatch the queue elements among available slots of the counter.
 * Once the queue element reaches the counter, its methods responsible for start and end
 * of the execution are called.
 */
public class Counter {
    /**
     * the maximal number of elements that simultaneously can be processed at a time.
     */
    private final int capacity;
    /**
     * a list of elements that would like to have access to the counter
     */
    private final List<Counterable> queue;

    private final Semaphore semaphore;

    public Counter(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>();
        this.semaphore = new Semaphore(capacity, false);
    }

    public void enqueue(Counterable elem) {
        elem.bind(this);
        this.queue.add(elem);

    }

    public void start() throws InterruptedException {
        for (Counterable elem : queue) {
            semaphore.acquire();
            (new Thread(elem)).start();
        }

    }



    public void free() {
        System.out.println("Releasing the permit...");
        semaphore.release();
    }
}
