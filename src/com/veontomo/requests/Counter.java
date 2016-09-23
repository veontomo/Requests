package com.veontomo.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Queue counter: dispatch the queue elements among available slots of the counter.
 * Once the queue element reaches the counter, its methods responsible for start and end
 * of the execution are called.
 */
class Counter {
    /**
     * the maximal number of elements that simultaneously can be processed at a time.
     */
    private final int capacity;
    /**
     * a list of elements that would like to have access to the counter
     */
    private final List<Counterable> queue;

    private int actions = 0;

    public int getActions(){
        return actions;
    }


    public Counter(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>();
    }

    public void enqueue(Counterable elem) {
        this.queue.add(elem);
    }

    public void start() throws InterruptedException {
        final long startTime = System.currentTimeMillis();

        Executor ex = Executors.newFixedThreadPool(capacity);
        ExecutorCompletionService<Summary> ecs = new ExecutorCompletionService<>(ex);
        queue.forEach(ecs::submit);
        Summary result = new DetailedSummary();
        for (int i = 0; i < queue.size(); i++) {
            try {
                result.merge(ecs.take().get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All workers have finished their work in " + (System.currentTimeMillis() - startTime) + " ms.");
        System.out.println(result.toString());

    }



}
