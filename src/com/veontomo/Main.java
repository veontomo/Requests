package com.veontomo;

public class Main {
    int accum;

    public static void main(String[] args) {

        Worker w = new Worker(1000);
        w.run();
        System.out.println(w.getAccum());
    }



}
