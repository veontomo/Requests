package com.veontomo;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter(10);
        for (int i = 0; i < 100; i++) {
            c.enqueue(new SiteVisitor("http://www.venditori.it", i));
        }
        c.start();
    }


}