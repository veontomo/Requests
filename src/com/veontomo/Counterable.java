package com.veontomo;

/**
 * Implements the Template Method design pattern.
 * Created by Andrey on 22/06/2016.
 */
public abstract class Counterable implements Runnable {
    @Override
    public void run() {
        onStart();
        onFinish();
    }

    public abstract void onStart();

    abstract public void onFinish();

    abstract public void bind(Counter c);
}
