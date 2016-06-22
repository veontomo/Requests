package com.veontomo;

/**
 * Created by Andrey on 22/06/2016.
 */
public abstract class Counterable implements Runnable {
    @Override
    public void run(){
        try {
            execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onFinish();
    }
    public abstract void execute() throws InterruptedException;

    abstract void bind(Counter c);

    abstract public void onFinish();
}
