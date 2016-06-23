package com.veontomo;

/**
 * Created by Andrey on 23/06/2016.
 */
public class Halt implements Action {

    private final long time;

    public Halt(long time) {
        this.time = time;
    }

    public void execute(){
        try {
            System.out.println("halter started " + this.time);
            Thread.sleep(this.time);
            System.out.println("halter is over...");
        } catch (InterruptedException e) {
            System.out.println("Could not sleep for " + this.time + " ms.");
        }
    }
}
